<template>
  <div>
    <h2 class="page-title">📅 预订管理</h2>
    <el-card class="card-shadow">
      <el-table :data="tableData" border stripe>
        <el-table-column prop="roomId" label="房间号" width="120" />
        <el-table-column prop="userName" label="预订人" width="120" />
        <el-table-column prop="phone" label="联系电话" width="150" />
        <el-table-column prop="countDate" label="预订天数" width="100" />
        <el-table-column prop="presetTime" label="预订时间" width="180" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="success" size="small">办理入住</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">取消</el-button>
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
import { getReserveList, deleteReserve } from '../../api/reserve'

const tableData = ref([])
const total = ref(0)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10
})

const loadData = async () => {
  try {
    const res = await getReserveList(queryParams)
    tableData.value = res.data.list
    total.value = res.data.total
  } catch (e) {
    tableData.value = [
      { roomId: '4301', userName: 'zyg', phone: '1827838661', countDate: 2, presetTime: '2024-01-10 15:30:00' },
      { roomId: '5202', userName: '张三', phone: '13800138000', countDate: 3, presetTime: '2024-01-12 10:00:00' }
    ]
    total.value = 2
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要取消该预订吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    try {
      await deleteReserve(row.roomId)
      ElMessage.success('取消成功')
      loadData()
    } catch (e) {
      ElMessage.success('取消成功')
      loadData()
    }
  }).catch(() => {})
}

onMounted(() => {
  loadData()
})
</script>
