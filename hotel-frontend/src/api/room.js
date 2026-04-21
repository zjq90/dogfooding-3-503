import request from '../utils/request'

export function getRoomList(params) {
  return request({
    url: '/room/list',
    method: 'get',
    params
  })
}

export function getAllRooms() {
  return request({
    url: '/room/all',
    method: 'get'
  })
}

export function getAvailableRooms() {
  return request({
    url: '/room/available',
    method: 'get'
  })
}

export function getRoomById(id) {
  return request({
    url: `/room/${id}`,
    method: 'get'
  })
}

export function addRoom(data) {
  return request({
    url: '/room/add',
    method: 'post',
    data
  })
}

export function updateRoom(data) {
  return request({
    url: '/room/update',
    method: 'put',
    data
  })
}

export function deleteRoom(id) {
  return request({
    url: `/room/${id}`,
    method: 'delete'
  })
}
