import request from '../utils/request'

export function adminLogin(data) {
  return request.post('/auth/admin/login', data)
}

export function userLogin(data) {
  return request.post('/auth/user/login', data)
}

export function userRegister(data) {
  return request.post('/auth/user/register', data)
}
