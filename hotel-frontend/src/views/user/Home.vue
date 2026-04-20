<template>
  <div class="user-home">
    <el-row :gutter="20" class="search-bar">
      <el-col :span="8">
        <el-select v-model="selectedType" placeholder="按类型筛选" clearable @change="filterByType" style="width: 100%">
          <el-option v-for="item in roomTypes" :key="item.typeId" :label="item.type" :value="item.type" />
        </el-select>
      </el-col>
      <el-col :span="8">
        <el-slider v-model="priceRange" range :min="0" :max="1000" @change="filterByPrice" />
        <span class="price-label">价格: {{ priceRange[0] }} - {{ priceRange[1] }} 元</span>
      </el-col>
      <el-col :span="8">
        <el-button type="primary" @click="resetFilter">重置筛选</el-button>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="room-list">
      <el-col :span="6" v-for="room in filteredRooms" :key="room.roomId">
        <el-card class="room-card" shadow="hover" @click="showDetail(room)">
          <img :src="room.photoUrl || defaultImg" class="room-img" />
          <div class="room-info">
            <h3>{{ room.roomId }}号房 - {{ room.roomType }}</h3>
            <p class="price">￥{{ room.price }}/天</p>
            <p class="area">{{ room.area }}㎡</p>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog v-model="detailVisible" title="房间详情" width="700px">
      <div class="room-detail" v-if="currentRoom">
        <el-row :gutter="20">
          <el-col :span="12">
            <img :src="currentRoom.photoUrl || defaultImg" class="detail-img" />
          </el-col>
          <el-col :span="12">
            <div class="detail-info">
              <h2>{{ currentRoom.roomId }}号房</h2>
              <p><strong>类型：</strong>{{ currentRoom.roomType }}</p>
              <p><strong>面积：</strong>{{ currentRoom.area }}㎡</p>
              <p><strong>价格：</strong><span class="price">￥{{ currentRoom.price }}/天</span></p>
              <p><strong>简介：</strong>{{ currentRoom.introduce }}</p>
            </div>
            <div class="detail-actions">
              <el-button type="primary" @click="openReserve">立即预定</el-button>
              <el-button @click="openComments">查看评论</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-dialog>

    <el-dialog v-model="reserveVisible" title="预定房间" width="400px">
      <el-form :model="reserveForm" label-width="80px">
        <el-form-item label="房间号">
          <el-input :value="currentRoom?.roomId" disabled />
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="reserveForm.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="预定天数">
          <el-input-number v-model="reserveForm.countDate" :min="1" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="reserveVisible = false">取消</el-button>
        <el-button type="primary" @click="submitReserve">确认预定</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="commentVisible" title="房间评论" width="500px">
      <div class="comment-list">
        <div v-for="comment in comments" :key="comment.serNum" class="comment-item">
          <div class="comment-header">
            <span class="user">{{ comment.userName }}</span>
            <span class="time">{{ comment.releaseTime }}</span>
          </div>
          <p class="content">{{ comment.content }}</p>
        </div>
        <el-empty v-if="comments.length === 0" description="暂无评论" />
      </div>
      <el-divider />
      <el-input v-model="newComment" type="textarea" :rows="2" placeholder="写下您的评论..." />
      <div style="text-align: right; margin-top: 10px;">
        <el-button type="primary" @click="submitComment">发表评论</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { getRoomList, getRoomsByType, getRoomsByPrice } from '../../api/room'
import { getRoomTypeList } from '../../api/roomType'
import { addReserve } from '../../api/reserve'
import { getComments, addComment } from '../../api/comment'

const defaultImg = 'https://via.placeholder.com/300x200?text=Hotel+Room'

const rooms = ref([])
const roomTypes = ref([])
const selectedType = ref('')
const priceRange = ref([0, 1000])
const detailVisible = ref(false)
const reserveVisible = ref(false)
const commentVisible = ref(false)
const currentRoom = ref(null)
const comments = ref([])
const newComment = ref('')

const userInfo = computed(() => {
  const user = sessionStorage.getItem('user')
  return user ? JSON.parse(user) : null
})

const reserveForm = reactive({
  phone: '',
  countDate: 1
})

const filteredRooms = ref([])

const loadData = async () => {
  const [roomRes, typeRes] = await Promise.all([getRoomList(), getRoomTypeList()])
  rooms.value = roomRes.data || []
  roomTypes.value = typeRes.data || []
  filteredRooms.value = rooms.value
}

onMounted(loadData)

const filterByType = async () => {
  if (!selectedType.value) {
    filteredRooms.value = rooms.value
    return
  }
  const res = await getRoomsByType(selectedType.value)
  filteredRooms.value = res.data || []
}

const filterByPrice = async () => {
  const res = await getRoomsByPrice(priceRange.value[0], priceRange.value[1])
  filteredRooms.value = res.data || []
}

const resetFilter = () => {
  selectedType.value = ''
  priceRange.value = [0, 1000]
  filteredRooms.value = rooms.value
}

const showDetail = (room) => {
  currentRoom.value = room
  detailVisible.value = true
}

const openReserve = () => {
  reserveForm.phone = userInfo.value?.phone || ''
  reserveForm.countDate = 1
  reserveVisible.value = true
}

const submitReserve = async () => {
  if (!reserveForm.phone) {
    ElMessage.warning('请输入联系电话')
    return
  }
  await addReserve({
    roomId: currentRoom.value.roomId,
    userName: userInfo.value?.userName,
    phone: reserveForm.phone,
    countDate: reserveForm.countDate
  })
  ElMessage.success('预定成功')
  reserveVisible.value = false
}

const openComments = async () => {
  const res = await getComments(currentRoom.value.roomId)
  comments.value = res.data || []
  newComment.value = ''
  commentVisible.value = true
}

const submitComment = async () => {
  if (!newComment.value.trim()) {
    ElMessage.warning('请输入评论内容')
    return
  }
  await addComment({
    roomId: currentRoom.value.roomId,
    userName: userInfo.value?.userName,
    content: newComment.value
  })
  ElMessage.success('评论成功')
  newComment.value = ''
  const res = await getComments(currentRoom.value.roomId)
  comments.value = res.data || []
}
</script>

<style scoped>
.user-home {
  padding: 20px;
}

.search-bar {
  margin-bottom: 30px;
  padding: 20px;
  background: white;
  border-radius: 8px;
}

.price-label {
  color: #666;
  font-size: 14px;
}

.room-card {
  cursor: pointer;
  transition: transform 0.3s;
}

.room-card:hover {
  transform: translateY(-5px);
}

.room-img {
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-radius: 4px;
}

.room-info {
  padding: 15px 0;
}

.room-info h3 {
  font-size: 16px;
  color: #333;
  margin-bottom: 10px;
}

.room-info .price {
  color: #ff5000;
  font-size: 18px;
  font-weight: bold;
}

.room-info .area {
  color: #999;
  font-size: 14px;
  margin-top: 5px;
}

.detail-img {
  width: 100%;
  border-radius: 8px;
}

.detail-info h2 {
  margin-bottom: 20px;
  color: #333;
}

.detail-info p {
  margin-bottom: 15px;
  color: #666;
}

.detail-info .price {
  color: #ff5000;
  font-size: 24px;
  font-weight: bold;
}

.detail-actions {
  margin-top: 30px;
}

.comment-list {
  max-height: 300px;
  overflow-y: auto;
}

.comment-item {
  padding: 15px 0;
  border-bottom: 1px solid #eee;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.comment-header .user {
  font-weight: bold;
  color: #333;
}

.comment-header .time {
  color: #999;
  font-size: 12px;
}

.comment-item .content {
  color: #666;
  line-height: 1.6;
}
</style>
