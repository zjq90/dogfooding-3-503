<template>
  <div>
    <h2 class="page-title">🏷️ 房型管理</h2>
    <el-card class="card-shadow">
      <div style="margin-bottom: 20px;">
        <el-button type="primary" @click="handleAdd">
          <el-icon><Plus /></el-icon>
          添加房型
        </el-button>
      </div>

      <el-table :data="tableData" border stripe>
        <el-table-column prop="typeId" label="ID" width="100" />
        <el-table-column prop="type" label="房型名称" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="400px">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item label="房型名称" prop="type">
          <el-input v-model="form.type" />
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

const tableData = ref([])
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const formRef = ref()

const form = reactive({
  typeId: null,
  type: ''
})

const rules = {
  type: [{ required: true, message: '请输入房型名称', trigger: 'blur' }]
}

const loadData = () => {
  tableData.value = [
    { typeId: 1, type: '大床房' },
    { typeId: 2, type: '单人间' },
    { typeId: 3, type: '情侣房' },
    { typeId: 4, type: '双人间' },
    { typeId: 5, type: '商务房' }
  ]
}

const handleAdd = () => {
  dialogTitle.value = '添加房型'
  isEdit.value = false
  form.type = ''
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑房型'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  ElMessage.success(isEdit.value ? '修改成功' : '添加成功')
  dialogVisible.value = false
  loadData()
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该房型吗？', '提示', {
    type: 'warning'
  }).then(() => {
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

onMounted(() => {
  loadData()
})
</script>
