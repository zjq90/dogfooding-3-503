import request from '../utils/request'

export function getNoticeList() {
  return request.get('/notice/list')
}

export function addNotice(data) {
  return request.post('/notice/add', data)
}

export function deleteNotice(serNum) {
  return request.delete(`/notice/delete/${serNum}`)
}
