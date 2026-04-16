import request from '../utils/request'

export function getRoomTypeList() {
  return request.get('/roomType/list')
}

export function addRoomType(data) {
  return request.post('/roomType/add', data)
}

export function deleteRoomType(typeId) {
  return request.delete(`/roomType/delete/${typeId}`)
}
