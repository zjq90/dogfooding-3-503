<template>
  <div>
    <el-carousel height="400px">
      <el-carousel-item v-for="i in 3" :key="i">
      <div style="color: #fff; font-size: 24px; display: flex; align-items: center; justify-content: center; height: 100%; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
        <div style="text-align: center;">
          <h1 style="font-size: 48px; margin-bottom: 20px;">🏨 欢迎光临酒店管理系统</h1>
          <p style="font-size: 20px;">舒适环境，贴心服务，给您家一般的温暖</p>
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>

    <div style="margin-top: 40px;">
      <h2 style="text-align: center; margin-bottom: 30px; font-size: 28px;">✨ 最新公告</h2>
      <el-row :gutter="20">
        <el-col :span="12" v-for="notice in notices" :key="notice.serNum">
          <el-card class="card-shadow" style="margin-bottom: 20px;">
            <template #header>
              <div style="display: flex; justify-content: space-between; align-items: center;">
              <strong>📢 {{ notice.notice.substring(0, 20) }}...</strong>
              <el-tag type="info">最新</el-tag>
            </div>
          </template>
            <p>{{ notice.notice }}</p>
            <p style="text-align: right; color: #999; margin-top: 15px;">发布时间：{{ notice.releaseTime }}</p>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div style="margin-top: 40px;">
      <h2 style="text-align: center; margin-bottom: 30px; font-size: 28px;">🏠 特色房间</h2>
      <el-row :gutter="20">
        <el-col :span="6" v-for="room in rooms" :key="room.roomId">
          <el-card class="card-shadow room-card">
          <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=modern%20hotel%20room%20interior%20design%20luxury&image_size=square_hd" style="width: 100%; height: 180px; object-fit: cover; border-radius: 8px;">
            <h3 style="margin: 15px 0 10px;">{{ room.roomType }} - {{ room.roomId }}</h3>
            <p style="color: #666; font-size: 14px;">面积：{{ room.area }}㎡</p>
            <p style="color: #ff6700; font-size: 20px; font-weight: bold; margin-top: 10px;">¥{{ room.price }} / 晚</p>
          </el-card>
        </el-col>
      </el-row>
      <div style="text-align: center; margin-top: 30px;">
        <el-button type="primary" size="large" @click="$router.push('/user/rooms')">查看全部房间 →</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const notices = ref([
  { serNum: 1, notice: '即日起所有客房打8折，欢迎新老顾客光临，我们将竭诚为您提供最优质的服务！', releaseTime: '2024-01-15' },
  { serNum: 2, notice: '豪华房优惠100元，数量有限先到先得，预订从速，错过不再有！', releaseTime: '2024-01-10' }
])

const rooms = ref([
  { roomId: '1301', roomType: '情侣房', area: 64, price: 200 },
  { roomId: '3202', roomType: '大床房', area: 60, price: 300 },
  { roomId: '4301', roomType: '商务房', area: 72, price: 400 },
  { roomId: '5201', roomType: '单人间', area: 40, price: 100 }
])
</script>
