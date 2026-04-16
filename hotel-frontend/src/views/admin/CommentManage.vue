<template>
  <div>
    <h2 class="page-title">💬 评论管理</h2>
    <el-card class="card-shadow">
      <el-table :data="tableData" border stripe>
        <el-table-column prop="serNum" label="ID" width="80" />
        <el-table-column prop="userName" label="评论人" width="120" />
        <el-table-column prop="roomId" label="房间号" width="100" />
        <el-table-column prop="content" label="评论内容" show-overflow-tooltip />
        <el-table-column prop="releaseTime" label="评论时间" width="180" />
        <el-table-column label="操作" width="100" fixed="right">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        v-model:current-page="queryParams.pageNum"
        v-model:page-size="queryParams.pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5, 10, 20]"
        style="margin-top: 20px; text-align: right;"
        @size-change="loadData"
        @current-change="loadData"
      />
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const tableData = ref([])
const total = ref(0)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10
})

const loadData = () => {
  tableData.value = [
    { serNum: 1, userName: 'zyg', roomId: '1301', content: '体验很好，服务周到', releaseTime: '2024-01-15 16:04:27' },
    { serNum: 2, userName: '章鱼哥', roomId: '1301', content: 'very good！环境不错，设施齐全', releaseTime: '2024-01-14 22:56:38' },
    { serNum: 3, userName: 'ggg', roomId: '5203', content: '这个房间很吊，下次还来', releaseTime: '2024-01-13 21:10:51' }
  ]
  total.value = 12
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该评论吗？', '提示', {
    type: 'warning'
  }).then(() => {
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

onMounted(() => {
  loadData()
})
</script>
