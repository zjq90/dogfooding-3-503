import request from '@/utils/request'

export const getRoomList = () => {
  return request({
    url: '/room/list',
    method: 'get'
  })
}

export const getRoomPage = (params) => {
  return request({
    url: '/room/page',
    method: 'get',
    params
  })
}

export const getRoomById = (roomId) => {
  return request({
    url: `/room/${roomId}`,
    method: 'get'
  })
}

export const getRoomsByType = (roomType) => {
  return request({
    url: `/room/type/${roomType}`,
    method: 'get'
  })
}

export const getAvailableRooms = () => {
  return request({
    url: '/room/available',
    method: 'get'
  })
}

export const addRoom = (data) => {
  return request({
    url: '/room',
    method: 'post',
    data
  })
}

export const updateRoom = (roomId, data) => {
  return request({
    url: `/room/${roomId}`,
    method: 'put',
    data
  })
}

export const deleteRoom = (roomId) => {
  return request({
    url: `/room/${roomId}`,
    method: 'delete'
  })
}
