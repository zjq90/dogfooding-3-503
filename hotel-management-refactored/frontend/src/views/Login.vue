<template>
  <div class="login-page">
    <div class="login-container">
      <div class="login-box">
        <div class="login-header">
          <el-icon size="48" color="#409EFF"><Hotel /></el-icon>
          <h2>酒店管理系统</h2>
          <p>Hotel Management System</p>
        </div>

        <el-tabs v-model="activeTab" class="login-tabs" stretch>
          <el-tab-pane label="用户登录" name="user">
            <el-form
              ref="userFormRef"
              :model="userForm"
              :rules="userRules"
              class="login-form"
            >
              <el-form-item prop="userName">
                <el-input
                  v-model="userForm.userName"
                  placeholder="请输入用户名"
                  :prefix-icon="User"
                  size="large"
                />
              </el-form-item>
              <el-form-item prop="userPwd">
                <el-input
                  v-model="userForm.userPwd"
                  type="password"
                  placeholder="请输入密码"
                  :prefix-icon="Lock"
                  size="large"
                  show-password
                  @keyup.enter="handleUserLogin"
                />
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  size="large"
                  class="login-btn"
                  :loading="loading"
                  @click="handleUserLogin"
                >
                  登录
                </el-button>
              </el-form-item>
              <div class="login-options">
                <router-link to="/register">注册账号</router-link>
              </div>
            </el-form>
          </el-tab-pane>

          <el-tab-pane label="管理员登录" name="admin">
            <el-form
              ref="adminFormRef"
              :model="adminForm"
              :rules="adminRules"
              class="login-form"
            >
              <el-form-item prop="adminName">
                <el-input
                  v-model="adminForm.adminName"
                  placeholder="请输入管理员账号"
                  :prefix-icon="User"
                  size="large"
                />
              </el-form-item>
              <el-form-item prop="adminPwd">
                <el-input
                  v-model="adminForm.adminPwd"
                  type="password"
                  placeholder="请输入密码"
                  :prefix-icon="Lock"
                  size="large"
                  show-password
                  @keyup.enter="handleAdminLogin"
                />
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  size="large"
                  class="login-btn"
                  :loading="loading"
                  @click="handleAdminLogin"
                >
                  登录
                </el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import { userLogin, adminLogin } from '@/api/auth'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()
const loading = ref(false)
const activeTab = ref('user')

const userFormRef = ref(null)
const adminFormRef = ref(null)

const userForm = reactive({
  userName: '',
  userPwd: ''
})

const adminForm = reactive({
  adminName: '',
  adminPwd: ''
})

const userRules = {
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  userPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const adminRules = {
  adminName: [{ required: true, message: '请输入管理员账号', trigger: 'blur' }],
  adminPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const handleUserLogin = async () => {
  if (!userFormRef.value) return

  await userFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        const res = await userLogin(userForm)
        userStore.setUserInfo(res.data)
        ElMessage.success('登录成功')
        router.push('/user/home')
      } catch (error) {
        console.error(error)
      } finally {
        loading.value = false
      }
    }
  })
}

const handleAdminLogin = async () => {
  if (!adminFormRef.value) return

  await adminFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        const res = await adminLogin(adminForm)
        userStore.setUserInfo(res.data)
        ElMessage.success('登录成功')
        router.push('/admin/dashboard')
      } catch (error) {
        console.error(error)
      } finally {
        loading.value = false
      }
    }
  })
}
</script>

<style scoped lang="scss">
.login-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-container {
  width: 100%;
  max-width: 420px;
  padding: 20px;
}

.login-box {
  background: #fff;
  border-radius: 16px;
  padding: 40px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}

.login-header {
  text-align: center;
  margin-bottom: 30px;

  h2 {
    margin: 15px 0 5px;
    color: #303133;
    font-size: 24px;
  }

  p {
    color: #909399;
    font-size: 14px;
  }
}

.login-tabs {
  :deep(.el-tabs__nav-wrap::after) {
    height: 1px;
  }
}

.login-form {
  margin-top: 20px;

  .login-btn {
    width: 100%;
  }

  .login-options {
    display: flex;
    justify-content: flex-end;
    margin-top: 10px;

    a {
      color: #409EFF;
      text-decoration: none;
      font-size: 14px;

      &:hover {
        text-decoration: underline;
      }
    }
  }
}
</style>
