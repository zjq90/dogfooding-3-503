import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/store/user'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { public: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register.vue'),
    meta: { public: true }
  },
  {
    path: '/admin',
    name: 'AdminLayout',
    component: () => import('@/layouts/AdminLayout.vue'),
    meta: { requiresAuth: true, requiresAdmin: true },
    children: [
      {
        path: '',
        redirect: '/admin/dashboard'
      },
      {
        path: 'dashboard',
        name: 'AdminDashboard',
        component: () => import('@/views/admin/Dashboard.vue'),
        meta: { title: '控制台' }
      },
      {
        path: 'rooms',
        name: 'RoomManagement',
        component: () => import('@/views/admin/RoomManagement.vue'),
        meta: { title: '房间管理' }
      },
      {
        path: 'room-types',
        name: 'RoomTypeManagement',
        component: () => import('@/views/admin/RoomTypeManagement.vue'),
        meta: { title: '房型管理' }
      },
      {
        path: 'orders',
        name: 'OrderManagement',
        component: () => import('@/views/admin/OrderManagement.vue'),
        meta: { title: '入住管理' }
      },
      {
        path: 'reserves',
        name: 'ReserveManagement',
        component: () => import('@/views/admin/ReserveManagement.vue'),
        meta: { title: '预订管理' }
      },
      {
        path: 'users',
        name: 'UserManagement',
        component: () => import('@/views/admin/UserManagement.vue'),
        meta: { title: '用户管理' }
      },
      {
        path: 'comments',
        name: 'CommentManagement',
        component: () => import('@/views/admin/CommentManagement.vue'),
        meta: { title: '评论管理' }
      },
      {
        path: 'notices',
        name: 'NoticeManagement',
        component: () => import('@/views/admin/NoticeManagement.vue'),
        meta: { title: '公告管理' }
      }
    ]
  },
  {
    path: '/user',
    name: 'UserLayout',
    component: () => import('@/layouts/UserLayout.vue'),
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        redirect: '/user/home'
      },
      {
        path: 'home',
        name: 'UserHome',
        component: () => import('@/views/user/Home.vue'),
        meta: { title: '首页' }
      },
      {
        path: 'rooms',
        name: 'UserRooms',
        component: () => import('@/views/user/Rooms.vue'),
        meta: { title: '房间列表' }
      },
      {
        path: 'room/:roomId',
        name: 'RoomDetail',
        component: () => import('@/views/user/RoomDetail.vue'),
        meta: { title: '房间详情' }
      },
      {
        path: 'my-orders',
        name: 'MyOrders',
        component: () => import('@/views/user/MyOrders.vue'),
        meta: { title: '我的订单' }
      },
      {
        path: 'my-reserves',
        name: 'MyReserves',
        component: () => import('@/views/user/MyReserves.vue'),
        meta: { title: '我的预订' }
      },
      {
        path: 'profile',
        name: 'UserProfile',
        component: () => import('@/views/user/Profile.vue'),
        meta: { title: '个人中心' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  userStore.loadUserInfo()

  // 公开页面直接通过
  if (to.meta.public) {
    next()
    return
  }

  // 需要登录但未登录
  if (to.meta.requiresAuth && !userStore.isLoggedIn) {
    next('/login')
    return
  }

  // 需要管理员权限但不是管理员
  if (to.meta.requiresAdmin && !userStore.isAdmin) {
    next('/user/home')
    return
  }

  next()
})

export default router
