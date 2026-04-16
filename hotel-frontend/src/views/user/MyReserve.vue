<template>
  <div>
    <h2 class="page-title">📅 我的预订</h2>
    <el-card class="card-shadow">
      <el-table :data="reserves" border stripe>
        <el-table-column prop="roomId" label="房间号" width="120" />
        <el-table-column prop="countDate" label="预订天数" width="100" />
        <el-table-column prop="phone" label="联系电话" width="150" />
        <el-table-column prop="presetTime" label="预订时间" width="180" />
        <el-table-column label="状态" width="120">
          <template #default>
            <el-tag type="warning">待入住</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleCancel(row)">取消预订</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const reserves = ref([])

const loadData = () => {
  reserves.value = [
    { roomId: '4301', countDate: 2, phone: '1827838661', presetTime: '2024-01-10 15:30:00' }
  ]
}

const handleCancel = (row) => {
  ElMessageBox.confirm('确定要取消该预订吗？', '提示', {
    type: 'warning'
  }).then(() => {
    ElMessage.success('取消成功')
    loadData()
  }).catch(() => {})
}

onMounted(() => {
  loadData()
})
</script>
