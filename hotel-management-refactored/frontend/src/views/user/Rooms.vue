<template>
  <div class="rooms-page">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>房间列表</span>
          <el-radio-group v-model="filterType" size="small" @change="handleFilterChange">
            <el-radio-button label="">全部</el-radio-button>
            <el-radio-button v-for="type in roomTypes" :key="type.typeId" :label="type.type">
              {{ type.type }}
            </el-radio-button>
          </el-radio-group>
        </div>
      </template>

      <el-row :gutter="20">
        <el-col :span="8" v-for="room in filteredRooms" :key="room.roomId">
          <el-card class="room-card" shadow="hover" @click="goToDetail(room.roomId)">
            <img :src="room.photoUrl || '/img/jiudian1.jpg'" class="room-image" />
            <div class="room-info">
              <div class="room-header">
                <h3>{{ room.roomId }}</h3>
                <el-tag size="small">{{ room.roomType }}</el-tag>
              </div>
              <p class="room-area">{{ room.area }}m²</p>
              <p class="room-intro">{{ room.introduce }}</p>
              <div class="room-footer">
                <div class="room-price">
                  <span class="price">¥{{ room.price }}</span>
                  <span class="unit">/晚</span>
                </div>
                <el-button type="primary" size="small">查看详情</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <el-empty v-if="filteredRooms.length === 0" description="暂无房间" />
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getRoomList } from '@/api/room'
import { getRoomTypeList } from '@/api/roomType'

const route = useRoute()
const router = useRouter()
const roomList = ref([])
const roomTypes = ref([])
const filterType = ref(route.query.type || '')

const filteredRooms = computed(() => {
  if (!filterType.value) {
    return roomList.value
  }
  return roomList.value.filter(room => room.roomType === filterType.value)
})

const loadData = async () => {
  try {
    const [roomsRes, typesRes] = await Promise.all([
      getRoomList(),
      getRoomTypeList()
    ])
    roomList.value = roomsRes.data || []
    roomTypes.value = typesRes.data || []
  } catch (error) {
    console.error(error)
  }
}

const handleFilterChange = () => {
  // 可以添加额外的过滤逻辑
}

const goToDetail = (roomId) => {
  router.push(`/user/room/${roomId}`)
}

onMounted(() => {
  loadData()
})
</script>

<style scoped lang="scss">
.rooms-page {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;
  }

  .room-card {
    margin-bottom: 20px;
    cursor: pointer;
    transition: transform 0.3s;

    &:hover {
      transform: translateY(-5px);
    }

    .room-image {
      width: 100%;
      height: 200px;
      object-fit: cover;
      border-radius: 4px;
      margin-bottom: 12px;
    }

    .room-info {
      .room-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 8px;

        h3 {
          margin: 0;
          color: #303133;
        }
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

      .room-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;

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
}
</style>
