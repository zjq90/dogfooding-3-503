<template>
  <div class="reserve-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>预订管理</span>
        </div>
      </template>

      <el-table :data="reserveList" v-loading="loading" border>
        <el-table-column prop="roomId" label="房间号" width="100" />
        <el-table-column prop="userName" label="预订人" width="120" />
        <el-table-column prop="phone" label="联系电话" width="140" />
        <el-table-column prop="countDate" label="预订天数" width="100" />
        <el-table-column prop="presetTime" label="预订时间" width="160">
          <template #default="{ row }">
            {{ formatDate(row.presetTime) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120" fixed="right">
          <template #default="{ row }">
            <el-button type="danger" link @click="handleCancel(row)">
              <el-icon><Close /></el-icon>取消预订
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getReserveList, cancelReserve } from '@/api/reserve'
import dayjs from 'dayjs'

const loading = ref(false)
const reserveList = ref([])

const formatDate = (date) => {
  return date ? dayjs(date).format('YYYY-MM-DD HH:mm') : '-'
}

const loadData = async () => {
  loading.value = true
  try {
    const res = await getReserveList()
    reserveList.value = res.data || []
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const handleCancel = (row) => {
  ElMessageBox.confirm(`确定要取消房间 ${row.roomId} 的预订吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await cancelReserve(row.roomId)
      ElMessage.success('取消预订成功')
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
.reserve-management {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;
  }
}
</style>
