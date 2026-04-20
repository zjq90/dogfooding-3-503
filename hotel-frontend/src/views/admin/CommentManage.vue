<template>
  <div class="comment-manage">
    <el-card>
      <template #header>
        <span>评论管理</span>
      </template>
      <el-select v-model="selectedRoom" placeholder="选择房间查看评论" @change="loadComments" style="margin-bottom: 20px; width: 200px;">
        <el-option v-for="item in rooms" :key="item.roomId" :label="'房间 ' + item.roomId" :value="item.roomId" />
      </el-select>
      <el-table :data="comments" stripe>
        <el-table-column prop="serNum" label="ID" width="80" />
        <el-table-column prop="roomId" label="房间号" width="100" />
        <el-table-column prop="userName" label="评论人" width="120" />
        <el-table-column prop="content" label="评论内容" show-overflow-tooltip />
        <el-table-column prop="releaseTime" label="评论时间" width="180" />
        <el-table-column label="操作" width="100">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getComments, deleteComment } from '../../api/comment'
import { getRoomList } from '../../api/room'

const rooms = ref([])
const comments = ref([])
const selectedRoom = ref('')

const loadRooms = async () => {
  const res = await getRoomList()
  rooms.value = res.data || []
}

const loadComments = async () => {
  if (!selectedRoom.value) return
  const res = await getComments(selectedRoom.value)
  comments.value = res.data || []
}

onMounted(loadRooms)

const handleDelete = async (row) => {
  await ElMessageBox.confirm('确定删除该评论吗？', '提示', { type: 'warning' })
  await deleteComment(row.serNum)
  ElMessage.success('删除成功')
  loadComments()
}
</script>
