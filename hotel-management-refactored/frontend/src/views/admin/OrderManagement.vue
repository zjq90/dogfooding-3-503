<template>
  <div class="order-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>入住管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>办理入住
          </el-button>
        </div>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="在住订单" name="active">
          <el-table :data="activeOrders" v-loading="loading" border>
            <el-table-column prop="roomId" label="房间号" width="100" />
            <el-table-column prop="userName" label="入住人" width="120" />
            <el-table-column prop="id" label="身份证号" width="180" />
            <el-table-column prop="startTime" label="入住时间" width="160">
              <template #default="{ row }">
                {{ formatDate(row.startTime) }}
              </template>
            </el-table-column>
            <el-table-column prop="prepay" label="预付款" width="100">
              <template #default="{ row }">
                ¥{{ row.prepay }}
              </template>
            </el-table-column>
            <el-table-column label="操作" width="150" fixed="right">
              <template #default="{ row }">
                <el-button type="success" link @click="handleCheckout(row)">
                  <el-icon><Check /></el-icon>退房
                </el-button>
                <el-button type="danger" link @click="handleDelete(row)">
                  <el-icon><Delete /></el-icon>删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="历史订单" name="history">
          <el-table :data="historyOrders" v-loading="loading" border>
            <el-table-column prop="roomId" label="房间号" width="100" />
            <el-table-column prop="userName" label="入住人" width="120" />
            <el-table-column prop="id" label="身份证号" width="180" />
            <el-table-column prop="startTime" label="入住时间" width="160">
              <template #default="{ row }">
                {{ formatDate(row.startTime) }}
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="退房时间" width="160">
              <template #default="{ row }">
                {{ formatDate(row.endTime) }}
              </template>
            </el-table-column>
            <el-table-column prop="prepay" label="预付款" width="100">
              <template #default="{ row }">
                ¥{{ row.prepay }}
              </template>
            </el-table-column>
            <el-table-column prop="payment" label="实付款" width="100">
              <template #default="{ row }">
                ¥{{ row.payment }}
              </template>
            </el-table-column>
            <el-table-column prop="change" label="找零" width="100">
              <template #default="{ row }">
                ¥{{ row.change }}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 办理入住对话框 -->
    <el-dialog v-model="dialogVisible" title="办理入住" width="500px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="房间" prop="roomId">
          <el-select v-model="form.roomId" placeholder="请选择房间" style="width: 100%">
            <el-option
              v-for="room in availableRooms"
              :key="room.roomId"
              :label="`${room.roomId} - ${room.roomType} - ¥${room.price}`"
              :value="room.roomId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="入住人" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入入住人姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="id">
          <el-input v-model="form.id" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="预付款" prop="prepay">
          <el-input-number v-model="form.prepay" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit" :loading="submitting">确定</el-button>
      </template>
    </el-dialog>

    <!-- 退房对话框 -->
    <el-dialog v-model="checkoutVisible" title="退房结算" width="400px">
      <el-form ref="checkoutFormRef" :model="checkoutForm" :rules="checkoutRules" label-width="100px">
        <el-form-item label="房间号">
          <el-input v-model="checkoutForm.roomId" disabled />
        </el-form-item>
        <el-form-item label="预付款">
          <el-input v-model="checkoutForm.prepay" disabled />
        </el-form-item>
        <el-form-item label="实付款" prop="payment">
          <el-input-number v-model="checkoutForm.payment" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="checkoutVisible = false">取消</el-button>
        <el-button type="primary" @click="handleCheckoutSubmit" :loading="checkoutLoading">确认退房</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getOrderList, addOrder, deleteOrder, checkout } from '@/api/order'
import { getAvailableRooms } from '@/api/room'
import dayjs from 'dayjs'

const loading = ref(false)
const orderList = ref([])
const availableRooms = ref([])
const activeTab = ref('active')
const dialogVisible = ref(false)
const checkoutVisible = ref(false)
const submitting = ref(false)
const checkoutLoading = ref(false)
const formRef = ref(null)
const checkoutFormRef = ref(null)

const form = reactive({
  roomId: '',
  userName: '',
  id: '',
  prepay: 0
})

const checkoutForm = reactive({
  roomId: '',
  prepay: 0,
  payment: 0
})

const rules = {
  roomId: [{ required: true, message: '请选择房间', trigger: 'change' }],
  userName: [{ required: true, message: '请输入入住人姓名', trigger: 'blur' }],
  id: [{ required: true, message: '请输入身份证号', trigger: 'blur' }],
  prepay: [{ required: true, message: '请输入预付款', trigger: 'blur' }]
}

const checkoutRules = {
  payment: [{ required: true, message: '请输入实付款', trigger: 'blur' }]
}

const activeOrders = computed(() => {
  return orderList.value.filter(order => !order.endTime)
})

const historyOrders = computed(() => {
  return orderList.value.filter(order => order.endTime)
})

const formatDate = (date) => {
  return date ? dayjs(date).format('YYYY-MM-DD HH:mm') : '-'
}

const loadData = async () => {
  loading.value = true
  try {
    const res = await getOrderList()
    orderList.value = res.data || []
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const loadAvailableRooms = async () => {
  try {
    const res = await getAvailableRooms()
    availableRooms.value = res.data || []
  } catch (error) {
    console.error(error)
  }
}

const handleAdd = () => {
  Object.assign(form, {
    roomId: '',
    userName: '',
    id: '',
    prepay: 0
  })
  loadAvailableRooms()
  dialogVisible.value = true
}

const handleSubmit = async () => {
  if (!formRef.value) return

  await formRef.value.validate(async (valid) => {
    if (valid) {
      submitting.value = true
      try {
        await addOrder(form)
        ElMessage.success('入住登记成功')
        dialogVisible.value = false
        loadData()
      } catch (error) {
        console.error(error)
      } finally {
        submitting.value = false
      }
    }
  })
}

const handleCheckout = (row) => {
  Object.assign(checkoutForm, {
    roomId: row.roomId,
    prepay: row.prepay,
    payment: row.prepay
  })
  checkoutVisible.value = true
}

const handleCheckoutSubmit = async () => {
  if (!checkoutFormRef.value) return

  await checkoutFormRef.value.validate(async (valid) => {
    if (valid) {
      checkoutLoading.value = true
      try {
        await checkout(checkoutForm.roomId, checkoutForm.payment)
        ElMessage.success('退房结算成功')
        checkoutVisible.value = false
        loadData()
      } catch (error) {
        console.error(error)
      } finally {
        checkoutLoading.value = false
      }
    }
  })
}

const handleDelete = (row) => {
  ElMessageBox.confirm(`确定要删除该订单吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteOrder(row.roomId)
      ElMessage.success('删除成功')
      loadData()
    } catch (error) {
      console.error(error)
    }
  })
}

onMounted(() => {
  loadData()
})
</script>

<style scoped lang="scss">
.order-management {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;
  }
}
</style>
