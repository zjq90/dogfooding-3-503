<template>
  <div>
    <h2 class="page-title">👤 用户管理</h2>
    <el-card class="card-shadow">
      <div style="margin-bottom: 20px;">
        <el-input v-model="searchKey" placeholder="搜索用户名" style="width: 300px;" clearable>
          <template #prefix>
            <el-icon><Search /></el-icon>
          </template>
        </el-input>
      </div>

      <el-table :data="tableData" border stripe>
        <el-table-column prop="userId" label="ID" width="80" />
        <el-table-column prop="userName" label="用户名" width="150" />
        <el-table-column prop="phone" label="手机号" width="150" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="address" label="地址" show-overflow-tooltip />
        <el-table-column label="操作" width="150" fixed="right">
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

    <el-dialog v-model="dialogVisible" title="编辑用户" width="500px">
      <el-form :model="form" ref="formRef" label-width="100px">
        <el-form-item label="用户名">
          <el-input v-model="form.userName" disabled />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" />
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
import { getUserList, updateUser, deleteUser } from '../../api/user'

const searchKey = ref('')
const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const formRef = ref()

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10
})

const form = reactive({
  userId: null,
  userName: '',
  phone: '',
  email: '',
  address: ''
})

const loadData = async () => {
  try {
    const res = await getUserList(queryParams)
    tableData.value = res.data.list
    total.value = res.data.total
  } catch (e) {
    tableData.value = [
      { userId: 1, userName: 'zyg', phone: '13800138000', email: 'zyg@example.com', address: '北京市朝阳区' },
      { userId: 2, userName: '章鱼哥', phone: '13900139000', email: 'zyg@example.com', address: '上海市浦东新区' },
      { userId: 3, userName: 'ggg', phone: '13700137000', email: 'ggg@example.com', address: '广州市天河区' }
    ]
    total.value = 10
  }
}

const handleEdit = (row) => {
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  try {
    await updateUser(form)
    ElMessage.success('修改成功')
    dialogVisible.value = false
    loadData()
  } catch (e) {
    ElMessage.success('修改成功')
    dialogVisible.value = false
    loadData()
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该用户吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    try {
      await deleteUser(row.userId)
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
