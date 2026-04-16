import request from '../utils/request'

export function getReserveList() {
  return request.get('/reserve/list')
}

export function addReserve(data) {
  return request.post('/reserve/add', data)
}

export function deleteReserve(roomId) {
  return request.delete(`/reserve/delete/${roomId}`)
}

export function checkReserved(roomId) {
  return request.get(`/reserve/check/${roomId}`)
}
