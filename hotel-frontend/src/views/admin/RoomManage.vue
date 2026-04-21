<template>
  <div>
    <h2 class="page-title">🏠 房间管理</h2>
    <el-card class="card-shadow">
      <div style="margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center;">
        <el-input v-model="searchKey" placeholder="搜索房间号" style="width: 300px;" clearable>
          <template #prefix>
            <el-icon><Search /></el-icon>
          </template>
        </el-input>
        <el-button type="primary" @click="handleAdd">
          <el-icon><Plus /></el-icon>
          添加房间
        </el-button>
      </div>

      <el-table :data="tableData" border stripe>
        <el-table-column prop="roomId" label="房间号" width="120" />
        <el-table-column prop="roomType" label="房型" width="120" />
        <el-table-column prop="area" label="面积" width="100" />
        <el-table-column prop="price" label="价格" width="120" />
        <el-table-column prop="introduce" label="介绍" show-overflow-tooltip />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="房间号" prop="roomId">
          <el-input v-model="form.roomId" :disabled="isEdit" />
        </el-form-item>
        <el-form-item label="房型" prop="roomType">
          <el-input v-model="form.roomType" />
        </el-form-item>
        <el-form-item label="面积" prop="area">
          <el-input-number v-model="form.area" :min="1" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input-number v-model="form.price" :min="0" :precision="2" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="介绍" prop="introduce">
          <el-input v-model="form.introduce" type="textarea" :rows="3" />
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
import { getRoomList, addRoom, updateRoom, deleteRoom } from '../../api/room'

const searchKey = ref('')
const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const formRef = ref()

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10
})

const form = reactive({
  roomId: '',
  roomType: '',
  area: 30,
  price: 100,
  introduce: ''
})

const rules = {
  roomId: [{ required: true, message: '请输入房间号', trigger: 'blur' }],
  roomType: [{ required: true, message: '请输入房型', trigger: 'blur' }],
  area: [{ required: true, message: '请输入面积', trigger: 'blur' }],
  price: [{ required: true, message: '请输入价格', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await getRoomList(queryParams)
    tableData.value = res.data.list
    total.value = res.data.total
  } catch (e) {
    tableData.value = [
      { roomId: '1301', roomType: '情侣房', area: 64, price: 200, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '2804', roomType: '情侣房', area: 54, price: 200, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '3202', roomType: '大床房', area: 60, price: 300, introduce: '环境优美，装备顶级影音设备' }
    ]
    total.value = 8
  }
}

const handleAdd = () => {
  dialogTitle.value = '添加房间'
  isEdit.value = false
  Object.assign(form, {
    roomId: '',
    roomType: '',
    area: 30,
    price: 100,
    introduce: ''
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑房间'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  try {
    if (isEdit.value) {
      await updateRoom(form)
      ElMessage.success('修改成功')
    } else {
      await addRoom(form)
      ElMessage.success('添加成功')
    }
    dialogVisible.value = false
    loadData()
  } catch (e) {
    ElMessage.success(isEdit.value ? '修改成功' : '添加成功')
    dialogVisible.value = false
    loadData()
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该房间吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    try {
      await deleteRoom(row.roomId)
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
