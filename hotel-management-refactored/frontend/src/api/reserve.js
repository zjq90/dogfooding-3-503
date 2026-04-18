import request from '@/utils/request'

export const getReserveList = () => {
  return request({
    url: '/reserve/list',
    method: 'get'
  })
}

export const getReservesByUser = (userName) => {
  return request({
    url: `/reserve/user/${userName}`,
    method: 'get'
  })
}

export const addReserve = (data) => {
  return request({
    url: '/reserve',
    method: 'post',
    data
  })
}

export const cancelReserve = (roomId) => {
  return request({
    url: `/reserve/${roomId}`,
    method: 'delete'
  })
}
