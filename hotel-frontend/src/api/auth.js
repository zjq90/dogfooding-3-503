import request from '../utils/request'

export function adminLogin(data) {
  return request({
    url: '/auth/admin/login',
    method: 'post',
    data
  })
}

export function userLogin(data) {
  return request({
    url: '/auth/user/login',
    method: 'post',
    data
  })
}

export function userRegister(data) {
  return request({
    url: '/auth/user/register',
    method: 'post',
    data
  })
}

export function adminLogout() {
  return request({
    url: '/auth/admin/logout',
    method: 'post'
  })
}

export function userLogout() {
  return request({
    url: '/auth/user/logout',
    method: 'post'
  })
}
