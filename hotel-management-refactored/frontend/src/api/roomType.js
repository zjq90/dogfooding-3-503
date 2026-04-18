import request from '@/utils/request'

export const getRoomTypeList = () => {
  return request({
    url: '/room-type/list',
    method: 'get'
  })
}

export const addRoomType = (data) => {
  return request({
    url: '/room-type',
    method: 'post',
    data
  })
}

export const updateRoomType = (typeId, data) => {
  return request({
    url: `/room-type/${typeId}`,
    method: 'put',
    data
  })
}

export const deleteRoomType = (typeId) => {
  return request({
    url: `/room-type/${typeId}`,
    method: 'delete'
  })
}
