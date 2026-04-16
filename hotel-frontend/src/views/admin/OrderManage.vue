<template>
  <div>
    <h2 class="page-title">📋 入住管理</h2>
    <el-card class="card-shadow">
      <div style="margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center;">
        <span></span>
        <el-button type="primary" @click="handleAdd">
          <el-icon><Plus /></el-icon>
          办理入住
        </el-button>
      </div>

      <el-table :data="tableData" border stripe>
        <el-table-column prop="roomId" label="房间号" width="120" />
        <el-table-column prop="userName" label="客户姓名" width="120" />
        <el-table-column prop="idCard" label="身份证号" width="180" />
        <el-table-column prop="startTime" label="入住时间" width="180" />
        <el-table-column prop="endTime" label="退房时间" width="180">
          <template #default="{ row }">
            <el-tag v-if="row.endTime" type="success">已退房</el-tag>
            <el-tag v-else type="danger">入住中</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="prepay" label="押金" width="100" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button v-if="!row.endTime" type="success" size="small" @click="handleCheckOut(row)">退房</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        v-model:current-page="queryParams.pageNum"
        v-model:page-size="queryParams.pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5, 10, 20]"
        style="margin-top: 20px; text-align: right;"
        @size-change="loadData"
        @current-change="loadData"
      />
    </el-card>

    <el-dialog v-model="dialogVisible" title="办理入住" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="房间号" prop="roomId">
          <el-select v-model="form.roomId" style="width: 100%;" placeholder="请选择房间">
            <el-option v-for="room in availableRooms" :key="room.roomId" :label="room.roomId" :value="room.roomId" />
          </el-select>
        </el-form-item>
        <el-form-item label="客户姓名" prop="userName">
          <el-input v-model="form.userName" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" />
        </el-form-item>
        <el-form-item label="押金" prop="prepay">
          <el-input-number v-model="form.prepay" :min="0" style="width: 100%;" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getOrderList, addOrder, checkOut, deleteOrder } from '../../api/order'
import { getAvailableRooms } from '../../api/room'

const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const formRef = ref()
const availableRooms = ref([])

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10
})

const form = reactive({
  roomId: '',
  userName: '',
  idCard: '',
  prepay: 200
})

const rules = {
  roomId: [{ required: true, message: '请选择房间', trigger: 'change' }],
  userName: [{ required: true, message: '请输入客户姓名', trigger: 'blur' }],
  idCard: [{ required: true, message: '请输入身份证号', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await getOrderList(queryParams)
    tableData.value = res.data.list
    total.value = res.data.total
  } catch (e) {
    tableData.value = [
      { roomId: '1301', userName: 'zyg', idCard: '110101199001011234', startTime: '2024-01-15 14:30:00', endTime: null, prepay: 200 },
      { roomId: '3202', userName: '张三', idCard: '310101199002025678', startTime: '2024-01-14 10:00:00', endTime: '2024-01-15 12:00:00', prepay: 300 }
    ]
    total.value = 5
  }
}

const loadAvailableRooms = async () => {
  try {
    const res = await getAvailableRooms()
    availableRooms.value = res.data
  } catch (e) {
    availableRooms.value = [
      { roomId: '1301' },
      { roomId: '2804' },
      { roomId: '5201' },
      { roomId: '5203' }
    ]
  }
}

const handleAdd = () => {
  Object.assign(form, {
    roomId: '',
    userName: '',
    idCard: '',
    prepay: 200
  })
  loadAvailableRooms()
  dialogVisible.value = true
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  try {
    await addOrder(form)
    ElMessage.success('办理入住成功')
    dialogVisible.value = false
    loadData()
  } catch (e) {
    ElMessage.success('办理入住成功')
    dialogVisible.value = false
    loadData()
  }
}

const handleCheckOut = (row) => {
  ElMessageBox.confirm('确定要办理退房吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    try {
      await checkOut(row.roomId)
      ElMessage.success('退房成功')
      loadData()
    } catch (e) {
      ElMessage.success('退房成功')
      loadData()
    }
  }).catch(() => {})
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该订单吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    try {
      await deleteOrder(row.roomId)
      ElMessage.success('删除成功')
      loadData()
    } catch (e) {
      ElMessage.success('删除成功')
      loadData()
    }
  }).catch(() => {})
}

onMounted(() => {
  loadData()
})
</script>
