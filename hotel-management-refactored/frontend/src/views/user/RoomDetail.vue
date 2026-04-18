<template>
  <div class="room-detail">
    <el-page-header @back="goBack" title="房间详情" />

    <el-row :gutter="20" class="detail-content">
      <el-col :span="12">
        <el-card>
          <img :src="room.photoUrl || '/img/jiudian1.jpg'" class="room-image" />
          <div class="room-basic">
            <h2>{{ room.roomId }} - {{ room.roomType }}</h2>
            <p class="room-area">{{ room.area }}m²</p>
            <div class="room-price">
              <span class="price">¥{{ room.price }}</span>
              <span class="unit">/晚</span>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card>
          <template #header>
            <span>房间介绍</span>
          </template>
          <p class="intro-text">{{ room.introduce }}</p>

          <div class="action-buttons">
            <el-button type="primary" size="large" @click="showReserveDialog">
              <el-icon><Calendar /></el-icon>立即预订
            </el-button>
          </div>
        </el-card>

        <el-card class="comment-card">
          <template #header>
            <span>用户评论</span>
          </template>
          <div v-if="comments.length > 0">
            <div v-for="comment in comments" :key="comment.serNum" class="comment-item">
              <div class="comment-header">
                <span class="username">{{ comment.userName }}</span>
                <span class="time">{{ formatDate(comment.releaseTime) }}</span>
              </div>
              <p class="comment-content">{{ comment.content }}</p>
            </div>
          </div>
          <el-empty v-else description="暂无评论" />

          <div class="add-comment">
            <el-input
              v-model="newComment"
              type="textarea"
              :rows="3"
              placeholder="发表您的评论..."
            />
            <el-button type="primary" @click="submitComment" :loading="commentLoading">
              发表评论
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 预订对话框 -->
    <el-dialog v-model="reserveVisible" title="预订房间" width="400px">
      <el-form ref="reserveFormRef" :model="reserveForm" :rules="reserveRules" label-width="100px">
        <el-form-item label="房间号">
          <el-input v-model="reserveForm.roomId" disabled />
        </el-form-item>
        <el-form-item label="预订天数" prop="countDate">
          <el-input-number v-model="reserveForm.countDate" :min="1" :max="30" style="width: 100%" />
        </el-form-item>
        <el-form-item label="联系人" prop="userName">
          <el-input v-model="reserveForm.userName" placeholder="请输入联系人姓名" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="reserveForm.phone" placeholder="请输入联系电话" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="reserveVisible = false">取消</el-button>
        <el-button type="primary" @click="submitReserve" :loading="reserveLoading">确认预订</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { getRoomById } from '@/api/room'
import { getCommentsByRoom, addComment } from '@/api/comment'
import { addReserve } from '@/api/reserve'
import { useUserStore } from '@/store/user'
import dayjs from 'dayjs'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const room = ref({})
const comments = ref([])
const newComment = ref('')
const commentLoading = ref(false)
const reserveVisible = ref(false)
const reserveLoading = ref(false)
const reserveFormRef = ref(null)

const reserveForm = reactive({
  roomId: '',
  countDate: 1,
  userName: userStore.userInfo?.userName || '',
  phone: ''
})

const reserveRules = {
  countDate: [{ required: true, message: '请输入预订天数', trigger: 'blur' }],
  userName: [{ required: true, message: '请输入联系人姓名', trigger: 'blur' }],
  phone: [
    { required: true, message: '请输入联系电话', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ]
}

const formatDate = (date) => {
  return date ? dayjs(date).format('YYYY-MM-DD HH:mm') : '-'
}

const goBack = () => {
  router.back()
}

const loadData = async () => {
  const roomId = route.params.roomId
  try {
    const [roomRes, commentsRes] = await Promise.all([
      getRoomById(roomId),
      getCommentsByRoom(roomId)
    ])
    room.value = roomRes.data || {}
    comments.value = commentsRes.data || []
  } catch (error) {
    console.error(error)
  }
}

const showReserveDialog = () => {
  reserveForm.roomId = room.value.roomId
  reserveForm.countDate = 1
  reserveForm.userName = userStore.userInfo?.userName || ''
  reserveForm.phone = ''
  reserveVisible.value = true
}

const submitReserve = async () => {
  if (!reserveFormRef.value) return

  await reserveFormRef.value.validate(async (valid) => {
    if (valid) {
      reserveLoading.value = true
      try {
        await addReserve(reserveForm)
        ElMessage.success('预订成功')
        reserveVisible.value = false
      } catch (error) {
        console.error(error)
      } finally {
        reserveLoading.value = false
      }
    }
  })
}

const submitComment = async () => {
  if (!newComment.value.trim()) {
    ElMessage.warning('请输入评论内容')
    return
  }

  commentLoading.value = true
  try {
    await addComment({
      roomId: room.value.roomId,
      userName: userStore.userInfo?.userName,
      content: newComment.value
    })
    ElMessage.success('评论成功')
    newComment.value = ''
    const res = await getCommentsByRoom(room.value.roomId)
    comments.value = res.data || []
  } catch (error) {
    console.error(error)
  } finally {
    commentLoading.value = false
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped lang="scss">
.room-detail {
  .detail-content {
    margin-top: 20px;
  }

  .room-image {
    width: 100%;
    height: 300px;
    object-fit: cover;
    border-radius: 8px;
    margin-bottom: 20px;
  }

  .room-basic {
    h2 {
      margin: 0 0 12px;
      color: #303133;
    }

    .room-area {
      color: #909399;
      margin: 0 0 12px;
    }

    .room-price {
      .price {
        color: #f56c6c;
        font-size: 32px;
        font-weight: bold;
      }

      .unit {
        color: #909399;
        font-size: 16px;
      }
    }
  }

  .intro-text {
    color: #606266;
    line-height: 1.8;
    font-size: 15px;
  }

  .action-buttons {
    margin-top: 24px;

    .el-button {
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 8px;
    }
  }

  .comment-card {
    margin-top: 20px;

    .comment-item {
      padding: 16px 0;
      border-bottom: 1px solid #ebeef5;

      &:last-child {
        border-bottom: none;
      }

      .comment-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 8px;

        .username {
          font-weight: bold;
          color: #303133;
        }

        .time {
          color: #909399;
          font-size: 14px;
        }
      }

      .comment-content {
        color: #606266;
        margin: 0;
        line-height: 1.6;
      }
    }

    .add-comment {
      margin-top: 20px;
      padding-top: 20px;
      border-top: 1px solid #ebeef5;

      .el-input {
        margin-bottom: 12px;
      }

      .el-button {
        width: 100%;
      }
    }
  }
}
</style>
