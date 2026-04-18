import request from '@/utils/request'

export const getUserList = () => {
  return request({
    url: '/user/list',
    method: 'get'
  })
}

export const getUserPage = (params) => {
  return request({
    url: '/user/page',
    method: 'get',
    params
  })
}

export const getUserById = (userId) => {
  return request({
    url: `/user/${userId}`,
    method: 'get'
  })
}

export const updateUser = (userId, data) => {
  return request({
    url: `/user/${userId}`,
    method: 'put',
    data
  })
}

export const deleteUser = (userName) => {
  return request({
    url: `/user/${userName}`,
    method: 'delete'
  })
}
