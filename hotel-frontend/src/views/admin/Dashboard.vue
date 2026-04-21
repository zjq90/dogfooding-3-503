<template>
  <div>
    <h2 class="page-title">📊 数据概览</h2>
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="stat-card blue">
          <el-icon size="40"><OfficeBuilding /></el-icon>
          <div class="stat-number">{{ stats.roomCount }}</div>
          <div class="stat-label">房间总数</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card green">
          <el-icon size="40"><User /></el-icon>
          <div class="stat-number">{{ stats.userCount }}</div>
          <div class="stat-label">用户总数</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card orange">
          <el-icon size="40"><Document /></el-icon>
          <div class="stat-number">{{ stats.orderCount }}</div>
          <div class="stat-label">入住订单</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card purple">
          <el-icon size="40"><ChatDotRound /></el-icon>
          <div class="stat-number">{{ stats.commentCount }}</div>
          <div class="stat-label">评论数量</div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 30px;">
      <el-col :span="12">
        <el-card class="card-shadow">
          <template #header>
            <span style="font-size: 16px; font-weight: 600;">最新公告</span>
          </template>
          <el-list>
            <el-list-item v-for="notice in notices" :key="notice.serNum">
              <el-text>{{ notice.notice }}</el-text>
              <template #extra>
                <el-text type="info" size="small">{{ formatDate(notice.releaseTime) }}</el-text>
              </template>
            </el-list-item>
          </el-list>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="card-shadow">
          <template #header>
            <span style="font-size: 16px; font-weight: 600;">最新评论</span>
          </template>
          <el-list>
            <el-list-item v-for="comment in comments" :key="comment.serNum">
              <div>
                <el-text strong>{{ comment.userName }}:</el-text>
                <el-text style="margin-left: 10px;">{{ comment.content }}</el-text>
              </div>
              <template #extra>
                <el-tag size="small" type="info">房间{{ comment.roomId }}</el-tag>
              </template>
            </el-list-item>
          </el-list>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getRoomList } from '../../api/room'
import { getUserList } from '../../api/user'
import { getOrderList } from '../../api/order'

const stats = ref({
  roomCount: 0,
  userCount: 0,
  orderCount: 0,
  commentCount: 0
})

const notices = ref([
  { serNum: 1, notice: '即日起：所有客房打8折', releaseTime: '2024-01-15' },
  { serNum: 2, notice: '即日起：豪华房优惠100元', releaseTime: '2024-01-10' }
])

const comments = ref([
  { serNum: 1, userName: '张三', roomId: '1301', content: '房间很干净，服务很好！' },
  { serNum: 2, userName: '李四', roomId: '5201', content: '性价比很高，下次还来！' },
  { serNum: 3, userName: '王五', roomId: '3202', content: '环境不错，很安静。' }
])

const formatDate = (date) => {
  return new Date(date).toLocaleDateString()
}

const loadData = async () => {
  try {
    const [roomRes, userRes, orderRes] = await Promise.all([
      getRoomList({ pageNum: 1, pageSize: 1 }),
      getUserList({ pageNum: 1, pageSize: 1 }),
      getOrderList({ pageNum: 1, pageSize: 1 })
    ])
    stats.value.roomCount = roomRes.data.total
    stats.value.userCount = userRes.data.total
    stats.value.orderCount = orderRes.data.total
  } catch (e) {
    stats.value.roomCount = 8
    stats.value.userCount = 10
    stats.value.orderCount = 5
  }
  stats.value.commentCount = 12
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.el-list {
  max-height: 300px;
  overflow-y: auto;
}
</style>
