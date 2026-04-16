<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: #409EFF;">
            <el-icon size="32" color="#fff"><House /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-title">房间总数</p>
            <p class="stat-value">{{ stats.roomCount }}</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: #67C23A;">
            <el-icon size="32" color="#fff"><User /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-title">注册用户</p>
            <p class="stat-value">{{ stats.userCount }}</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: #E6A23C;">
            <el-icon size="32" color="#fff"><DocumentChecked /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-title">在住订单</p>
            <p class="stat-value">{{ stats.activeOrderCount }}</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: #F56C6C;">
            <el-icon size="32" color="#fff"><Calendar /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-title">今日预订</p>
            <p class="stat-value">{{ stats.reserveCount }}</p>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="mt-20">
      <el-col :span="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>最新公告</span>
              <el-button type="primary" link @click="$router.push('/admin/notices')">
                查看更多
              </el-button>
            </div>
          </template>
          <el-timeline>
            <el-timeline-item
              v-for="notice in latestNotices"
              :key="notice.serNum"
              :timestamp="formatDate(notice.releaseTime)"
              type="primary"
            >
              {{ notice.notice }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>快捷操作</span>
            </div>
          </template>
          <div class="quick-actions">
            <el-button type="primary" @click="$router.push('/admin/rooms')">
              <el-icon><Plus /></el-icon>添加房间
            </el-button>
            <el-button type="success" @click="$router.push('/admin/orders')">
              <el-icon><DocumentChecked /></el-icon>办理入住
            </el-button>
            <el-button type="warning" @click="$router.push('/admin/notices')">
              <el-icon><Bell /></el-icon>发布公告
            </el-button>
            <el-button type="info" @click="$router.push('/admin/users')">
              <el-icon><User /></el-icon>用户管理
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getRoomList } from '@/api/room'
import { getUserList } from '@/api/user'
import { getActiveOrders } from '@/api/order'
import { getReserveList } from '@/api/reserve'
import { getLatestNotices } from '@/api/notice'
import dayjs from 'dayjs'

const stats = ref({
  roomCount: 0,
  userCount: 0,
  activeOrderCount: 0,
  reserveCount: 0
})

const latestNotices = ref([])

const formatDate = (date) => {
  return dayjs(date).format('YYYY-MM-DD HH:mm')
}

const loadStats = async () => {
  try {
    const [rooms, users, orders, reserves] = await Promise.all([
      getRoomList(),
      getUserList(),
      getActiveOrders(),
      getReserveList()
    ])
    stats.value.roomCount = rooms.data?.length || 0
    stats.value.userCount = users.data?.length || 0
    stats.value.activeOrderCount = orders.data?.length || 0
    stats.value.reserveCount = reserves.data?.length || 0
  } catch (error) {
    console.error(error)
  }
}

const loadNotices = async () => {
  try {
    const res = await getLatestNotices(5)
    latestNotices.value = res.data || []
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadStats()
  loadNotices()
})
</script>

<style scoped lang="scss">
.dashboard {
  .mt-20 {
    margin-top: 20px;
  }

  .stat-card {
    display: flex;
    align-items: center;
    padding: 10px;

    .stat-icon {
      width: 64px;
      height: 64px;
      border-radius: 8px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 16px;
    }

    .stat-info {
      flex: 1;

      .stat-title {
        color: #909399;
        font-size: 14px;
        margin: 0 0 8px;
      }

      .stat-value {
        color: #303133;
        font-size: 28px;
        font-weight: bold;
        margin: 0;
      }
    }
  }

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;
  }

  .quick-actions {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 16px;

    .el-button {
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 8px;
      padding: 16px;
    }
  }
}
</style>
