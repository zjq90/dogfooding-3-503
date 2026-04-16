import request from '@/utils/request'

export const getOrderList = () => {
  return request({
    url: '/order/list',
    method: 'get'
  })
}

export const getOrdersByUser = (userName) => {
  return request({
    url: `/order/user/${userName}`,
    method: 'get'
  })
}

export const getActiveOrders = () => {
  return request({
    url: '/order/active',
    method: 'get'
  })
}

export const addOrder = (data) => {
  return request({
    url: '/order',
    method: 'post',
    data
  })
}

export const checkout = (roomId, payment) => {
  return request({
    url: `/order/checkout/${roomId}`,
    method: 'post',
    params: { payment }
  })
}

export const deleteOrder = (roomId) => {
  return request({
    url: `/order/${roomId}`,
    method: 'delete'
  })
}
