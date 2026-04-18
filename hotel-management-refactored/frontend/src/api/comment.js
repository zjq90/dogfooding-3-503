import request from '@/utils/request'

export const getCommentList = () => {
  return request({
    url: '/comment/list',
    method: 'get'
  })
}

export const getCommentsByRoom = (roomId) => {
  return request({
    url: `/comment/room/${roomId}`,
    method: 'get'
  })
}

export const getCommentsByUser = (userName) => {
  return request({
    url: `/comment/user/${userName}`,
    method: 'get'
  })
}

export const addComment = (data) => {
  return request({
    url: '/comment',
    method: 'post',
    data
  })
}

export const deleteComment = (serNum) => {
  return request({
    url: `/comment/${serNum}`,
    method: 'delete'
  })
}
