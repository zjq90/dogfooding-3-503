import request from '../utils/request'

export function getRoomList() {
  return request.get('/room/list')
}

export function getRoomDetail(roomId) {
  return request.get(`/room/detail/${roomId}`)
}

export function getRoomsByType(roomType) {
  return request.get(`/room/type/${roomType}`)
}

export function getRoomsByPrice(startPrice, endPrice) {
  return request.get('/room/price', { params: { startPrice, endPrice } })
}

export function getAvailableRooms() {
  return request.get('/room/available')
}

export function addRoom(data) {
  return request.post('/room/add', data)
}

export function updateRoom(data) {
  return request.put('/room/update', data)
}

export function deleteRoom(roomId) {
  return request.delete(`/room/delete/${roomId}`)
}
