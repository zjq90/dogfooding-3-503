<template>
  <div class="user-layout">
    <el-container>
      <el-header class="header">
        <div class="header-content">
          <h1 class="logo">酒店管理系统</h1>
          <div class="nav-menu">
            <el-link :class="{ active: $route.path === '/user' }" @click="$router.push('/user')">首页</el-link>
          </div>
          <div class="user-info">
            <span>欢迎，{{ userInfo?.userName }}</span>
            <el-button type="danger" size="small" @click="handleLogout">退出</el-button>
          </div>
        </div>
      </el-header>
      <el-main class="main">
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const userInfo = computed(() => {
  const user = sessionStorage.getItem('user')
  return user ? JSON.parse(user) : null
})

const handleLogout = () => {
  sessionStorage.removeItem('user')
  router.push('/user-login')
}
</script>

<style scoped>
.user-layout {
  min-height: 100vh;
  background-color: #f5f7fa;
}

.header {
  background: linear-gradient(135deg, #ff9000 0%, #ff5000 100%);
  padding: 0;
}

.header-content {
  max-width: 1400px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
  padding: 0 20px;
}

.logo {
  color: white;
  font-size: 24px;
  margin: 0;
}

.nav-menu {
  display: flex;
  gap: 30px;
}

.nav-menu .el-link {
  color: white;
  font-size: 16px;
}

.nav-menu .el-link.active {
  font-weight: bold;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 15px;
  color: white;
}

.main {
  max-width: 1400px;
  margin: 0 auto;
  padding: 20px;
}
</style>
