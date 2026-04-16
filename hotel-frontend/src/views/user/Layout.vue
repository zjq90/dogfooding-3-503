<template>
  <div>
    <el-container>
      <el-header class="user-header">
        <div class="header-logo">🏨 酒店管理系统</div>
        <el-menu mode="horizontal" :router="true" class="header-menu">
          <el-menu-item index="/user/home">首页</el-menu-item>
          <el-menu-item index="/user/rooms">房间列表</el-menu-item>
          <el-menu-item index="/user/my-order">我的入住</el-menu-item>
          <el-menu-item index="/user/my-reserve">我的预订</el-menu-item>
          <el-menu-item index="/user/comment">发表评论</el-menu-item>
        </el-menu>
        <div class="header-right">
          <span>欢迎，{{ user.userName }}</span>
          <el-button type="danger" size="small" @click="handleLogout">退出</el-button>
        </div>
      </el-header>
      <el-main class="user-main">
        <router-view />
      </el-main>
      <el-footer class="user-footer">
        <p>© 2024 酒店管理系统 版权所有</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { userLogout } from '../../api/auth'

const router = useRouter()
const user = ref(JSON.parse(sessionStorage.getItem('user')))

const handleLogout = () => {
  ElMessageBox.confirm('确定要退出登录吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await userLogout()
    sessionStorage.clear()
    ElMessage.success('退出成功')
    router.push('/login')
  }).catch(() => {})
}
</script>

<style scoped>
.user-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  padding: 0 40px;
}

.header-logo {
  color: white;
  font-size: 20px;
  font-weight: 600;
  margin-right: 40px;
}

.header-menu {
  flex: 1;
  background: transparent;
  border: none;
}

.header-menu :deep(.el-menu-item) {
  color: white;
}

.header-menu :deep(.el-menu-item:hover),
.header-menu :deep(.el-menu-item.is-active) {
  background: rgba(255, 255, 255, 0.2);
  color: white;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 15px;
  color: white;
}

.user-main {
  background: #f5f7fa;
  min-height: calc(100vh - 120px);
  padding: 30px 40px;
}

.user-footer {
  background: #304156;
  color: white;
  text-align: center;
  line-height: 60px;
}

.user-footer p {
  margin: 0;
}
</style>
