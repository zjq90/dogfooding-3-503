import request from '@/utils/request'

export const userLogin = (data) => {
  return request({
    url: '/auth/user/login',
    method: 'post',
    params: data
  })
}

export const userRegister = (data) => {
  return request({
    url: '/auth/user/register',
    method: 'post',
    data
  })
}

export const checkUserName = (userName) => {
  return request({
    url: '/auth/user/check',
    method: 'get',
    params: { userName }
  })
}

export const adminLogin = (data) => {
  return request({
    url: '/auth/admin/login',
    method: 'post',
    params: data
  })
}
