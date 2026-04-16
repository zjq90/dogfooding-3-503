import request from '@/utils/request'

export const getNoticeList = () => {
  return request({
    url: '/notice/list',
    method: 'get'
  })
}

export const getLatestNotices = (limit = 5) => {
  return request({
    url: '/notice/latest',
    method: 'get',
    params: { limit }
  })
}

export const addNotice = (data) => {
  return request({
    url: '/notice',
    method: 'post',
    data
  })
}

export const updateNotice = (serNum, data) => {
  return request({
    url: `/notice/${serNum}`,
    method: 'put',
    data
  })
}

export const deleteNotice = (serNum) => {
  return request({
    url: `/notice/${serNum}`,
    method: 'delete'
  })
}
