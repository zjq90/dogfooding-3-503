<template>
  <div class="user-manage">
    <el-card>
      <template #header>
        <span>会员信息管理</span>
      </template>
      <el-table :data="users" stripe>
        <el-table-column prop="userId" label="ID" width="80" />
        <el-table-column prop="userName" label="用户名" width="120" />
        <el-table-column prop="phone" label="手机号" width="140" />
        <el-table-column prop="email" label="邮箱" width="180" />
        <el-table-column prop="address" label="地址" show-overflow-tooltip />
        <el-table-column prop="qq" label="QQ" width="120" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getUserList, deleteUser } from '../../api/user'

const users = ref([])

const loadData = async () => {
  const res = await getUserList()
  users.value = res.data || []
}

onMounted(loadData)

const handleDelete = async (row) => {
  await ElMessageBox.confirm('确定删除该会员吗？', '提示', { type: 'warning' })
  await deleteUser(row.userId)
  ElMessage.success('删除成功')
  loadData()
}
</script>
