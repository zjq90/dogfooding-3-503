import request from '../utils/request'

export function getOrderList(params) {
  return request({
    url: '/order/list',
    method: 'get',
    params
  })
}

export function getOrdersByUserName(userName) {
  return request({
    url: `/order/user/${userName}`,
    method: 'get'
  })
}

export function addOrder(data) {
  return request({
    url: '/order/add',
    method: 'post',
    data
  })
}

export function updateOrder(data) {
  return request({
    url: '/order/update',
    method: 'put',
    data
  })
}

export function checkOut(roomId) {
  return request({
    url: `/order/checkout/${roomId}`,
    method: 'post'
  })
}

export function deleteOrder(id) {
  return request({
    url: `/order/${id}`,
    method: 'delete'
  })
}
