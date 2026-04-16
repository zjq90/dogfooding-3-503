<template>
  <div class="order-manage">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>入住信息管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>办理入住
          </el-button>
        </div>
      </template>
      <el-table :data="orders" stripe>
        <el-table-column prop="roomId" label="房间号" width="100" />
        <el-table-column prop="userName" label="入住人" width="120" />
        <el-table-column prop="id" label="身份证号" width="180" />
        <el-table-column prop="startTime" label="入住时间" width="180" />
        <el-table-column prop="endTime" label="退房时间" width="180" />
        <el-table-column prop="prepay" label="预付款" width="100" />
        <el-table-column prop="payment" label="实付款" width="100" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleCheckout(row)" v-if="!row.endTime">退房</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="办理入住" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="房间号">
          <el-select v-model="form.roomId" placeholder="请选择房间" style="width: 100%">
            <el-option v-for="item in availableRooms" :key="item.roomId" :label="item.roomId" :value="item.roomId" />
          </el-select>
        </el-form-item>
        <el-form-item label="入住人">
          <el-input v-model="form.userName" placeholder="请输入入住人姓名" />
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="form.id" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="预付款">
          <el-input-number v-model="form.prepay" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="checkoutVisible" title="退房结算" width="400px">
      <el-form :model="checkoutForm" label-width="80px">
        <el-form-item label="房间号">
          <el-input :value="checkoutForm.roomId" disabled />
        </el-form-item>
        <el-form-item label="实付款">
          <el-input-number v-model="checkoutForm.payment" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="checkoutVisible = false">取消</el-button>
        <el-button type="primary" @click="submitCheckout">确认退房</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getOrderList, addOrder, updateOrder, deleteOrder } from '../../api/order'
import { getAvailableRooms } from '../../api/room'

const orders = ref([])
const availableRooms = ref([])
const dialogVisible = ref(false)
const checkoutVisible = ref(false)
const form = reactive({ roomId: '', userName: '', id: '', prepay: 0 })
const checkoutForm = reactive({ roomId: '', payment: 0 })

const loadData = async () => {
  const res = await getOrderList()
  orders.value = res.data || []
}

const loadAvailableRooms = async () => {
  const res = await getAvailableRooms()
  availableRooms.value = res.data || []
}

onMounted(() => {
  loadData()
  loadAvailableRooms()
})

const handleAdd = () => {
  Object.assign(form, { roomId: '', userName: '', id: '', prepay: 0 })
  loadAvailableRooms()
  dialogVisible.value = true
}

const submitForm = async () => {
  if (!form.roomId || !form.userName) {
    ElMessage.warning('请填写完整信息')
    return
  }
  await addOrder(form)
  ElMessage.success('入住成功')
  dialogVisible.value = false
  loadData()
}

const handleCheckout = (row) => {
  checkoutForm.roomId = row.roomId
  checkoutForm.payment = row.prepay
  checkoutVisible.value = true
}

const submitCheckout = async () => {
  await updateOrder({ roomId: checkoutForm.roomId, payment: checkoutForm.payment, endTime: new Date() })
  ElMessage.success('退房成功')
  checkoutVisible.value = false
  loadData()
}

const handleDelete = async (row) => {
  await ElMessageBox.confirm('确定删除该记录吗？', '提示', { type: 'warning' })
  await deleteOrder(row.roomId)
  ElMessage.success('删除成功')
  loadData()
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
