<template>
  <div class="my-orders">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>我的订单</span>
        </div>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="在住订单" name="active">
          <el-table :data="activeOrders" v-loading="loading" border>
            <el-table-column prop="roomId" label="房间号" width="100" />
            <el-table-column prop="id" label="身份证号" width="180" />
            <el-table-column prop="startTime" label="入住时间" width="160">
              <template #default="{ row }">
                {{ formatDate(row.startTime) }}
              </template>
            </el-table-column>
            <el-table-column prop="prepay" label="预付款" width="100">
              <template #default="{ row }">
                ¥{{ row.prepay }}
              </template>
            </el-table-column>
            <el-table-column label="状态" width="100">
              <template #default>
                <el-tag type="success">在住</el-tag>
              </template>
            </el-table-column>
          </el-table>
          <el-empty v-if="activeOrders.length === 0" description="暂没有在住订单" />
        </el-tab-pane>

        <el-tab-pane label="历史订单" name="history">
          <el-table :data="historyOrders" v-loading="loading" border>
            <el-table-column prop="roomId" label="房间号" width="100" />
            <el-table-column prop="id" label="身份证号" width="180" />
            <el-table-column prop="startTime" label="入住时间" width="160">
              <template #default="{ row }">
                {{ formatDate(row.startTime) }}
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="退房时间" width="160">
              <template #default="{ row }">
                {{ formatDate(row.endTime) }}
              </template>
            </el-table-column>
            <el-table-column prop="prepay" label="预付款" width="100">
              <template #default="{ row }">
                ¥{{ row.prepay }}
              </template>
            </el-table-column>
            <el-table-column prop="payment" label="实付款" width="100">
              <template #default="{ row }">
                ¥{{ row.payment }}
              </template>
            </el-table-column>
            <el-table-column label="状态" width="100">
              <template #default>
                <el-tag type="info">已退房</el-tag>
              </template>
            </el-table-column>
          </el-table>
          <el-empty v-if="historyOrders.length === 0" description="暂无历史订单" />
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getOrdersByUser } from '@/api/order'
import { useUserStore } from '@/store/user'
import dayjs from 'dayjs'

const userStore = useUserStore()
const loading = ref(false)
const orderList = ref([])
const activeTab = ref('active')

const activeOrders = computed(() => {
  return orderList.value.filter(order => !order.endTime)
})

const historyOrders = computed(() => {
  return orderList.value.filter(order => order.endTime)
})

const formatDate = (date) => {
  return date ? dayjs(date).format('YYYY-MM-DD HH:mm') : '-'
}

const loadData = async () => {
  loading.value = true
  try {
    const userName = userStore.userInfo?.userName
    if (userName) {
      const res = await getOrdersByUser(userName)
      orderList.value = res.data || []
    }
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped lang="scss">
.my-orders {
  .card-header {
    font-weight: bold;
  }
}
</style>
