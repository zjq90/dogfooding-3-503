import request from '../utils/request'

export function getUserList() {
  return request.get('/user/list')
}

export function updateUser(data) {
  return request.put('/user/update', data)
}

export function deleteUser(userId) {
  return request.delete(`/user/delete/${userId}`)
}
