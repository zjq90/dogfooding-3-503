<template>
  <div>
    <h2 class="page-title">💬 发表评论</h2>
    <el-card class="card-shadow">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px" style="max-width: 600px;">
        <el-form-item label="房间号" prop="roomId">
          <el-select v-model="form.roomId" style="width: 100%;">
            <el-option label="1301" value="1301" />
            <el-option label="2804" value="2804" />
            <el-option label="3202" value="3202" />
            <el-option label="5201" value="5201" />
          </el-select>
        </el-form-item>
        <el-form-item label="评论内容" prop="content">
          <el-input v-model="form.content" type="textarea" :rows="5" placeholder="请输入您的入住体验..." />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSubmit">提交评论</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <div style="margin-top: 30px;">
      <h3>我的评论</h3>
      <el-timeline>
        <el-timeline-item
          v-for="comment in comments" :key="comment.serNum" :timestamp="comment.releaseTime">
          <el-card>
            <h4>房间 {{ comment.roomId }}</h4>
            <p>{{ comment.content }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const formRef = ref()
const currentUser = JSON.parse(sessionStorage.getItem('user'))

const form = reactive({
  userName: currentUser?.userName || '',
  roomId: '',
  content: ''
})

const rules = {
  roomId: [{ required: true, message: '请选择房间号', trigger: 'change' }],
  content: [{ required: true, message: '请输入评论内容', trigger: 'blur' }]
}

const comments = ref([
  { serNum: 1, roomId: '1301', content: '房间很干净，服务很好，下次还会再来！', releaseTime: '2024-01-15 16:04:27' },
  { serNum: 2, roomId: '3202', content: '性价比很高，环境安静，睡得很舒服。', releaseTime: '2024-01-10 10:30:00' }
])

const handleSubmit = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  ElMessage.success('评论发表成功')
  form.content = ''
  comments.value.unshift({
    serNum: Date.now(),
    roomId: form.roomId,
    content: form.content,
    releaseTime: new Date().toLocaleString()
  })
}

onMounted(() => {
  form.userName = currentUser?.userName || ''
})
</script>
