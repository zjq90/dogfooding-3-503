import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/admin',
    name: 'AdminLayout',
    component: () => import('../views/admin/Layout.vue'),
    redirect: '/admin/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/admin/Dashboard.vue')
      },
      {
        path: 'room',
        name: 'RoomManage',
        component: () => import('../views/admin/RoomManage.vue')
      },
      {
        path: 'room-type',
        name: 'RoomTypeManage',
        component: () => import('../views/admin/RoomTypeManage.vue')
      },
      {
        path: 'user',
        name: 'UserManage',
        component: () => import('../views/admin/UserManage.vue')
      },
      {
        path: 'order',
        name: 'OrderManage',
        component: () => import('../views/admin/OrderManage.vue')
      },
      {
        path: 'reserve',
        name: 'ReserveManage',
        component: () => import('../views/admin/ReserveManage.vue')
      },
      {
        path: 'comment',
        name: 'CommentManage',
        component: () => import('../views/admin/CommentManage.vue')
      },
      {
        path: 'notice',
        name: 'NoticeManage',
        component: () => import('../views/admin/NoticeManage.vue')
      }
    ]
  },
  {
    path: '/user',
    name: 'UserLayout',
    component: () => import('../views/user/Layout.vue'),
    redirect: '/user/home',
    children: [
      {
        path: 'home',
        name: 'UserHome',
        component: () => import('../views/user/Home.vue')
      },
      {
        path: 'rooms',
        name: 'UserRooms',
        component: () => import('../views/user/Rooms.vue')
      },
      {
        path: 'my-order',
        name: 'MyOrder',
        component: () => import('../views/user/MyOrder.vue')
      },
      {
        path: 'my-reserve',
        name: 'MyReserve',
        component: () => import('../views/user/MyReserve.vue')
      },
      {
        path: 'comment',
        name: 'UserComment',
        component: () => import('../views/user/Comment.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token')
  if (to.path.startsWith('/admin') && !token) {
    next('/login')
  } else if (to.path.startsWith('/user') && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
