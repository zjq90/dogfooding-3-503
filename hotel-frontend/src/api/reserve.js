import request from '../utils/request'

export function getReserveList(params) {
  return request({
    url: '/reserve/list',
    method: 'get',
    params
  })
}

export function getReservesByUserName(userName) {
  return request({
    url: `/reserve/user/${userName}`,
    method: 'get'
  })
}

export function addReserve(data) {
  return request({
    url: '/reserve/add',
    method: 'post',
    data
  })
}

export function updateReserve(data) {
  return request({
    url: '/reserve/update',
    method: 'put',
    data
  })
}

export function deleteReserve(id) {
  return request({
    url: `/reserve/${id}`,
    method: 'delete'
  })
}
