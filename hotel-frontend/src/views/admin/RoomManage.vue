<template>
  <div class="room-manage">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>客房信息管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>添加客房
          </el-button>
        </div>
      </template>
      <el-table :data="rooms" stripe>
        <el-table-column prop="roomId" label="房间号" width="100" />
        <el-table-column prop="roomType" label="类型" width="100" />
        <el-table-column prop="area" label="面积(㎡)" width="100" />
        <el-table-column prop="price" label="价格(元/天)" width="120" />
        <el-table-column prop="introduce" label="简介" show-overflow-tooltip />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑客房' : '添加客房'" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="房间号">
          <el-input v-model="form.roomId" :disabled="isEdit" placeholder="请输入房间号" />
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="form.roomType" placeholder="请选择类型" style="width: 100%">
            <el-option v-for="item in roomTypes" :key="item.typeId" :label="item.type" :value="item.type" />
          </el-select>
        </el-form-item>
        <el-form-item label="面积">
          <el-input-number v-model="form.area" :min="1" style="width: 100%" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number v-model="form.price" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="form.introduce" type="textarea" :rows="3" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="图片URL">
          <el-input v-model="form.photoUrl" placeholder="请输入图片URL" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getRoomList, addRoom, updateRoom, deleteRoom } from '../../api/room'
import { getRoomTypeList } from '../../api/roomType'

const rooms = ref([])
const roomTypes = ref([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const form = reactive({
  roomId: '',
  roomType: '',
  area: 0,
  price: 0,
  introduce: '',
  photoUrl: ''
})

const loadData = async () => {
  const [roomRes, typeRes] = await Promise.all([getRoomList(), getRoomTypeList()])
  rooms.value = roomRes.data || []
  roomTypes.value = typeRes.data || []
}

onMounted(loadData)

const handleAdd = () => {
  isEdit.value = false
  Object.assign(form, { roomId: '', roomType: '', area: 0, price: 0, introduce: '', photoUrl: '' })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  Object.assign(form, row)
  dialogVisible.value = true
}

const submitForm = async () => {
  if (!form.roomId || !form.roomType) {
    ElMessage.warning('请填写完整信息')
    return
  }
  if (isEdit.value) {
    await updateRoom(form)
    ElMessage.success('更新成功')
  } else {
    await addRoom(form)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadData()
}

const handleDelete = async (row) => {
  await ElMessageBox.confirm('确定删除该客房吗？', '提示', { type: 'warning' })
  await deleteRoom(row.roomId)
  ElMessage.success('删除成功')
  loadData()
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
