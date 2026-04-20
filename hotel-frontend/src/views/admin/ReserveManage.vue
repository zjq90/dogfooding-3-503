<template>
  <div class="reserve-manage">
    <el-card>
      <template #header>
        <span>预定信息管理</span>
      </template>
      <el-table :data="reserves" stripe>
        <el-table-column prop="roomId" label="房间号" width="100" />
        <el-table-column prop="userName" label="预定人" width="120" />
        <el-table-column prop="phone" label="联系电话" width="140" />
        <el-table-column prop="countDate" label="预定天数" width="100" />
        <el-table-column prop="presetTime" label="预定时间" width="180" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row)">取消预定</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getReserveList, deleteReserve } from '../../api/reserve'

const reserves = ref([])

const loadData = async () => {
  const res = await getReserveList()
  reserves.value = res.data || []
}

onMounted(loadData)

const handleDelete = async (row) => {
  await ElMessageBox.confirm('确定取消该预定吗？', '提示', { type: 'warning' })
  await deleteReserve(row.roomId)
  ElMessage.success('取消成功')
  loadData()
}
</script>
