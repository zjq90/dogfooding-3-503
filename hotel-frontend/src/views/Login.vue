<template>
  <div class="login-bg">
    <div class="login-card">
      <h1 class="login-title">🏨 酒店管理系统</h1>
      <el-tabs v-model="activeTab" class="login-tabs">
        <el-tab-pane label="管理员登录" name="admin">
          <el-form :model="adminForm" :rules="rules" ref="adminFormRef">
            <el-form-item prop="adminName">
              <el-input v-model="adminForm.adminName" placeholder="请输入账号" prefix-icon="User">
              </el-input>
            </el-form-item>
            <el-form-item prop="adminPwd">
              <el-input v-model="adminForm.adminPwd" type="password" placeholder="请输入密码" prefix-icon="Lock"
                @keyup.enter="handleAdminLogin">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleAdminLogin" class="login-btn">管理员登录</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="用户登录" name="user">
          <el-form :model="userForm" :rules="rules" ref="userFormRef">
            <el-form-item prop="userName">
              <el-input v-model="userForm.userName" placeholder="请输入用户名" prefix-icon="User">
              </el-input>
            </el-form-item>
            <el-form-item prop="userPwd">
              <el-input v-model="userForm.userPwd" type="password" placeholder="请输入密码" prefix-icon="Lock"
                @keyup.enter="handleUserLogin">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleUserLogin" class="login-btn">用户登录</el-button>
              <el-button @click="showRegister = true">用户注册</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>

    <el-dialog v-model="showRegister" title="用户注册" width="400px">
      <el-form :model="registerForm" :rules="registerRules" ref="registerFormRef">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="registerForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPwd">
          <el-input v-model="registerForm.userPwd" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPwd">
          <el-input v-model="registerForm.confirmPwd" type="password"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showRegister = false">取消</el-button>
        <el-button type="primary" @click="handleRegister">注册</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { adminLogin, userLogin, userRegister } from '../api/auth'

const router = useRouter()
const activeTab = ref('admin')
const showRegister = ref(false)

const adminFormRef = ref()
const userFormRef = ref()
const registerFormRef = ref()

const adminForm = reactive({
  adminName: 'admin',
  adminPwd: '123456'
})

const userForm = reactive({
  userName: '',
  userPwd: ''
})

const registerForm = reactive({
  userName: '',
  userPwd: '',
  confirmPwd: ''
})

const validateConfirmPwd = (rule, value, callback) => {
  if (value !== registerForm.userPwd) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

const rules = {
  adminName: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  adminPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  userPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const registerRules = {
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  userPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  confirmPwd: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    { validator: validateConfirmPwd, trigger: 'blur' }
  ]
}

const handleAdminLogin = async () => {
  const valid = await adminFormRef.value.validate()
  if (!valid) return
  try {
    const res = await adminLogin(adminForm)
    sessionStorage.setItem('token', res.data.token)
    sessionStorage.setItem('admin', JSON.stringify(res.data.admin))
    sessionStorage.setItem('userType', 'admin')
    ElMessage.success('登录成功')
    router.push('/admin/dashboard')
  } catch (e) {
    console.log(e)
  }
}

const handleUserLogin = async () => {
  const valid = await userFormRef.value.validate()
  if (!valid) return
  try {
    const res = await userLogin(userForm)
    sessionStorage.setItem('token', res.data.token)
    sessionStorage.setItem('user', JSON.stringify(res.data.user))
    sessionStorage.setItem('userType', 'user')
    ElMessage.success('登录成功')
    router.push('/user/home')
  } catch (e) {
    console.log(e)
  }
}

const handleRegister = async () => {
  const valid = await registerFormRef.value.validate()
  if (!valid) return
  try {
    await userRegister({
      userName: registerForm.userName,
      userPwd: registerForm.userPwd
    })
    ElMessage.success('注册成功，请登录')
    showRegister.value = false
    activeTab.value = 'user'
    userForm.userName = registerForm.userName
    userForm.userPwd = registerForm.userPwd
  } catch (e) {
    console.log(e)
  }
}
</script>

<style scoped>
.login-tabs :deep(.el-tabs__content) {
  padding-top: 20px;
}

.login-btn {
  width: 100%;
}
</style>
