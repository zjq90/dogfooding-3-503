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
    path: '/user-login',
    name: 'UserLogin',
    component: () => import('../views/UserLogin.vue')
  },
  {
    path: '/admin',
    name: 'AdminLayout',
    component: () => import('../layouts/AdminLayout.vue'),
    children: [
      {
        path: '',
        name: 'AdminHome',
        component: () => import('../views/admin/Home.vue')
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
        path: 'reserve',
        name: 'ReserveManage',
        component: () => import('../views/admin/ReserveManage.vue')
      },
      {
        path: 'order',
        name: 'OrderManage',
        component: () => import('../views/admin/OrderManage.vue')
      },
      {
        path: 'user',
        name: 'UserManage',
        component: () => import('../views/admin/UserManage.vue')
      },
      {
        path: 'comment',
        name: 'CommentManage',
        component: () => import('../views/admin/CommentManage.vue')
      }
    ]
  },
  {
    path: '/user',
    name: 'UserLayout',
    component: () => import('../layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        name: 'UserHome',
        component: () => import('../views/user/Home.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
