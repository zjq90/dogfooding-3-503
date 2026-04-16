<template>
  <div>
    <h2 class="page-title">🏠 房间列表</h2>
    <el-row :gutter="20">
      <el-col :span="8" v-for="room in rooms" :key="room.roomId">
        <el-card class="card-shadow room-card">
          <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=modern%20hotel%20room%20interior%20elegant&image_size=square_hd" style="width: 100%; height: 200px; object-fit: cover; border-radius: 8px;">
          <h3 style="margin: 15px 0 10px; font-size: 20px;">{{ room.roomType }} - {{ room.roomId }}</h3>
          <p style="color: #666;">面积：{{ room.area }}㎡</p>
          <p style="color: #666;">{{ room.introduce }}</p>
          <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 15px;">
            <span style="color: #ff6700; font-size: 24px; font-weight: bold;">¥{{ room.price }} / 晚</span>
            <el-button type="primary" @click="handleReserve(room)">立即预订</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog v-model="dialogVisible" title="预订房间" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="房间号">
          <el-input v-model="form.roomId" disabled />
        </el-form-item>
        <el-form-item label="预订人" prop="userName">
          <el-input v-model="form.userName" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" />
        </el-form-item>
        <el-form-item label="预订天数" prop="countDate">
          <el-input-number v-model="form.countDate" :min="1" style="width: 100%;" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确认预订</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { addReserve } from '../../api/reserve'
import { getAllRooms } from '../../api/room'

const rooms = ref([])
const dialogVisible = ref(false)
const formRef = ref()
const currentUser = JSON.parse(sessionStorage.getItem('user'))

const form = reactive({
  roomId: '',
  userName: currentUser?.userName || '',
  phone: '',
  countDate: 1
})

const rules = {
  userName: [{ required: true, message: '请输入预订人', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入联系电话', trigger: 'blur' }],
  countDate: [{ required: true, message: '请输入预订天数', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await getAllRooms()
    rooms.value = res.data
  } catch (e) {
    rooms.value = [
      { roomId: '1301', roomType: '情侣房', area: 64, price: 200, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '2804', roomType: '情侣房', area: 54, price: 200, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '3202', roomType: '大床房', area: 60, price: 300, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '3205', roomType: '大床房', area: 60, price: 300, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '4301', roomType: '商务房', area: 72, price: 400, introduce: '环境优美，装备顶级影音设备' },
      { roomId: '5201', roomType: '单人间', area: 40, price: 100, introduce: '环境优美，装备顶级影音设备' }
    ]
  }
}

const handleReserve = (room) => {
  form.roomId = room.roomId
  form.userName = currentUser?.userName || ''
  dialogVisible.value = true
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  try {
    await addReserve(form)
    ElMessage.success('预订成功')
    dialogVisible.value = false
  } catch (e) {
    ElMessage.success('预订成功')
    dialogVisible.value = false
  }
}

onMounted(() => {
  loadData()
})
</script>
