<template>
  <div class="user-home">
    <!-- Banner区域 -->
    <div class="banner">
      <div class="banner-content">
        <h1>欢迎来到酒店预订系统</h1>
        <p>为您提供舒适、便捷的住宿体验</p>
        <el-button type="primary" size="large" @click="$router.push('/user/rooms')">
          立即预订
        </el-button>
      </div>
    </div>

    <!-- 公告区域 -->
    <div class="section">
      <h2 class="section-title">
        <el-icon><Bell /></el-icon>
        最新公告
      </h2>
      <el-carousel height="60px" direction="vertical" :autoplay="true" indicator-position="none">
        <el-carousel-item v-for="notice in notices" :key="notice.serNum">
          <div class="notice-item">
            <el-tag type="warning" size="small">公告</el-tag>
            <span>{{ notice.notice }}</span>
            <span class="notice-time">{{ formatDate(notice.releaseTime) }}</span>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>

    <!-- 房间类型 -->
    <div class="section">
      <h2 class="section-title">
        <el-icon><House /></el-icon>
        房间类型
      </h2>
      <el-row :gutter="20">
        <el-col :span="6" v-for="type in roomTypes" :key="type.typeId">
          <el-card class="type-card" shadow="hover" @click="goToRooms(type.type)">
            <div class="type-icon">
              <el-icon size="48" color="#409EFF"><HomeFilled /></el-icon>
            </div>
            <h3>{{ type.type }}</h3>
            <p>点击查看更多</p>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 推荐房间 -->
    <div class="section">
      <h2 class="section-title">
        <el-icon><Star /></el-icon>
        推荐房间
      </h2>
      <el-row :gutter="20">
        <el-col :span="8" v-for="room in recommendedRooms" :key="room.roomId">
          <el-card class="room-card" shadow="hover" @click="goToRoomDetail(room.roomId)">
            <img :src="room.photoUrl || '/img/jiudian1.jpg'" class="room-image" />
            <div class="room-info">
              <h3>{{ room.roomId }} - {{ room.roomType }}</h3>
              <p class="room-area">{{ room.area }}m²</p>
              <p class="room-intro">{{ room.introduce }}</p>
              <div class="room-price">
                <span class="price">¥{{ room.price }}</span>
                <span class="unit">/晚</span>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getLatestNotices } from '@/api/notice'
import { getRoomTypeList } from '@/api/roomType'
import { getRoomList } from '@/api/room'
import dayjs from 'dayjs'

const router = useRouter()
const notices = ref([])
const roomTypes = ref([])
const recommendedRooms = ref([])

const formatDate = (date) => {
  return date ? dayjs(date).format('YYYY-MM-DD') : '-'
}

const goToRooms = (type) => {
  router.push({
    path: '/user/rooms',
    query: { type }
  })
}

const goToRoomDetail = (roomId) => {
  router.push(`/user/room/${roomId}`)
}

const loadData = async () => {
  try {
    const [noticesRes, typesRes, roomsRes] = await Promise.all([
      getLatestNotices(5),
      getRoomTypeList(),
      getRoomList()
    ])
    notices.value = noticesRes.data || []
    roomTypes.value = typesRes.data || []
    recommendedRooms.value = (roomsRes.data || []).slice(0, 6)
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped lang="scss">
.user-home {
  .banner {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-radius: 12px;
    padding: 60px 40px;
    margin-bottom: 30px;
    color: #fff;
    text-align: center;

    .banner-content {
      h1 {
        font-size: 36px;
        margin-bottom: 16px;
      }

      p {
        font-size: 18px;
        margin-bottom: 24px;
        opacity: 0.9;
      }
    }
  }

  .section {
    margin-bottom: 30px;

    .section-title {
      display: flex;
      align-items: center;
      gap: 8px;
      font-size: 20px;
      margin-bottom: 20px;
      color: #303133;

      .el-icon {
        color: #409EFF;
      }
    }
  }

  .notice-item {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 0 20px;
    background: #fdf6ec;
    border-radius: 4px;
    height: 100%;

    .notice-time {
      margin-left: auto;
      color: #909399;
      font-size: 14px;
    }
  }

  .type-card {
    text-align: center;
    cursor: pointer;
    transition: transform 0.3s;

    &:hover {
      transform: translateY(-5px);
    }

    .type-icon {
      margin-bottom: 12px;
    }

    h3 {
      margin: 0 0 8px;
      color: #303133;
    }

    p {
      margin: 0;
      color: #909399;
      font-size: 14px;
    }
  }

  .room-card {
    cursor: pointer;
    transition: transform 0.3s;

    &:hover {
      transform: translateY(-5px);
    }

    .room-image {
      width: 100%;
      height: 180px;
      object-fit: cover;
      border-radius: 4px;
      margin-bottom: 12px;
    }

    .room-info {
      h3 {
        margin: 0 0 8px;
        color: #303133;
        font-size: 16px;
      }

      .room-area {
        color: #909399;
        font-size: 14px;
        margin: 0 0 8px;
      }

      .room-intro {
        color: #606266;
        font-size: 14px;
        margin: 0 0 12px;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
      }

      .room-price {
        .price {
          color: #f56c6c;
          font-size: 24px;
          font-weight: bold;
        }

        .unit {
          color: #909399;
          font-size: 14px;
        }
      }
    }
  }
}
</style>
