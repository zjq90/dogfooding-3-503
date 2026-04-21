<template>
  <div>
    <h2 class="page-title">📋 我的入住</h2>
    <el-card class="card-shadow">
      <el-table :data="orders" border stripe>
        <el-table-column prop="roomId" label="房间号" width="120" />
        <el-table-column prop="startTime" label="入住时间" width="180" />
        <el-table-column prop="endTime" label="退房时间" width="180">
          <template #default="{ row }">
            <el-tag v-if="row.endTime" type="success">已退房</el-tag>
            <el-tag v-else type="danger">入住中</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="prepay" label="押金" width="100" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button v-if="!row.endTime" type="success" size="small">正在入住</el-button>
            <el-button type="primary" size="small" @click="handleComment(row)">发表评论</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const orders = ref([])
const currentUser = JSON.parse(sessionStorage.getItem('user'))

const loadData = () => {
  orders.value = [
    { roomId: '1301', startTime: '2024-01-15 14:30:00', endTime: null, prepay: 200 },
    { roomId: '3202', startTime: '2024-01-10 10:00:00', endTime: '2024-01-12 12:00:00', prepay: 300 }
  ]
}

const handleComment = (row) => {
}

onMounted(() => {
  loadData()
})
</script>
