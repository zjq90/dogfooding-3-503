<template>
  <div class="room-manage">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>客房类型管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>添加类型
          </el-button>
        </div>
      </template>
      <el-table :data="roomTypes" stripe>
        <el-table-column prop="typeId" label="类型ID" width="100" />
        <el-table-column prop="type" label="类型名称" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="添加客房类型" width="400px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="类型名称">
          <el-input v-model="form.type" placeholder="请输入类型名称" />
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
import { getRoomTypeList, addRoomType, deleteRoomType } from '../../api/roomType'

const roomTypes = ref([])
const dialogVisible = ref(false)
const form = reactive({
  type: ''
})

const loadData = async () => {
  const res = await getRoomTypeList()
  roomTypes.value = res.data || []
}

onMounted(loadData)

const handleAdd = () => {
  form.type = ''
  dialogVisible.value = true
}

const submitForm = async () => {
  if (!form.type) {
    ElMessage.warning('请输入类型名称')
    return
  }
  await addRoomType(form)
  ElMessage.success('添加成功')
  dialogVisible.value = false
  loadData()
}

const handleDelete = async (row) => {
  await ElMessageBox.confirm('确定删除该类型吗？', '提示', { type: 'warning' })
  await deleteRoomType(row.typeId)
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
