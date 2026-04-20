import request from '../utils/request'

export function getComments(roomId) {
  return request.get(`/comment/list/${roomId}`)
}

export function addComment(data) {
  return request.post('/comment/add', data)
}

export function deleteComment(serNum) {
  return request.delete(`/comment/delete/${serNum}`)
}
