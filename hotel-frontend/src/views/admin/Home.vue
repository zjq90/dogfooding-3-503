<template>
  <div class="admin-home">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
            <el-icon size="32"><House /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-value">{{ stats.roomCount }}</p>
            <p class="stat-label">客房总数</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);">
            <el-icon size="32"><Calendar /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-value">{{ stats.reserveCount }}</p>
            <p class="stat-label">预定数量</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: linear-gradient(135deg, #ff9000 0%, #ff5000 100%);">
            <el-icon size="32"><Document /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-value">{{ stats.orderCount }}</p>
            <p class="stat-label">入住数量</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-icon" style="background: linear-gradient(135deg, #ee0979 0%, #ff6a00 100%);">
            <el-icon size="32"><User /></el-icon>
          </div>
          <div class="stat-info">
            <p class="stat-value">{{ stats.userCount }}</p>
            <p class="stat-label">会员数量</p>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-card class="welcome-card" shadow="never">
      <h2>欢迎使用酒店管理系统</h2>
      <p>请通过左侧菜单进行相关操作</p>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getRoomList } from '../../api/room'
import { getReserveList } from '../../api/reserve'
import { getOrderList } from '../../api/order'
import { getUserList } from '../../api/user'

const stats = ref({
  roomCount: 0,
  reserveCount: 0,
  orderCount: 0,
  userCount: 0
})

onMounted(async () => {
  try {
    const [rooms, reserves, orders, users] = await Promise.all([
      getRoomList(),
      getReserveList(),
      getOrderList(),
      getUserList()
    ])
    stats.value.roomCount = rooms.data?.length || 0
    stats.value.reserveCount = reserves.data?.length || 0
    stats.value.orderCount = orders.data?.length || 0
    stats.value.userCount = users.data?.length || 0
  } catch (e) {
    console.error(e)
  }
})
</script>

<style scoped>
.admin-home {
  padding: 20px;
}

.stat-card {
  display: flex;
  align-items: center;
  padding: 20px;
}

.stat-card :deep(.el-card__body) {
  display: flex;
  align-items: center;
  width: 100%;
  padding: 20px;
}

.stat-icon {
  width: 64px;
  height: 64px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}

.stat-info {
  margin-left: 20px;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin: 0;
}

.stat-label {
  font-size: 14px;
  color: #999;
  margin: 5px 0 0;
}

.welcome-card {
  margin-top: 30px;
  text-align: center;
  padding: 40px;
}

.welcome-card h2 {
  color: #333;
  margin-bottom: 15px;
}

.welcome-card p {
  color: #666;
}
</style>
