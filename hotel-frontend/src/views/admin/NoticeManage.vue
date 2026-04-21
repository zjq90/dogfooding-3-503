<template>
  <div>
    <h2 class="page-title">📢 公告管理</h2>
    <el-card class="card-shadow">
      <div style="margin-bottom: 20px;">
        <el-button type="primary" @click="handleAdd">
          <el-icon><Plus /></el-icon>
          发布公告
        </el-button>
      </div>

      <el-table :data="tableData" border stripe>
        <el-table-column prop="serNum" label="ID" width="80" />
        <el-table-column prop="notice" label="公告内容" show-overflow-tooltip />
        <el-table-column prop="releaseTime" label="发布时间" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item label="公告内容" prop="notice">
          <el-input v-model="form.notice" type="textarea" :rows="4" />
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
  serNum: null,
  notice: ''
})

const rules = {
  notice: [{ required: true, message: '请输入公告内容', trigger: 'blur' }]
}

const loadData = () => {
  tableData.value = [
    { serNum: 1, notice: '即日起：所有客房打8折，欢迎新老顾客光临！', releaseTime: '2024-01-15 18:52:09' },
    { serNum: 2, notice: '即日起：豪华房优惠100元，数量有限先到先得！', releaseTime: '2024-01-10 23:04:50' }
  ]
}

const handleAdd = () => {
  dialogTitle.value = '发布公告'
  isEdit.value = false
  form.notice = ''
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑公告'
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  ElMessage.success(isEdit.value ? '修改成功' : '发布成功')
  dialogVisible.value = false
  loadData()
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该公告吗？', '提示', {
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
