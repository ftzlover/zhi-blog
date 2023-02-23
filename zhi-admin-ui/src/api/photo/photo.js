import request from '@/utils/request'

// 查询照片管理列表
export function listPhoto(query) {
  return request({
    url: '/photo/photo/list',
    method: 'get',
    params: query
  })
}

// 查询照片管理详细
export function getPhoto(id) {
  return request({
    url: '/photo/photo/' + id,
    method: 'get'
  })
}

// 新增照片管理
export function addPhoto(data) {
  return request({
    url: '/photo/photo',
    method: 'post',
    data: data
  })
}

// 修改照片管理
export function updatePhoto(data) {
  return request({
    url: '/photo/photo',
    method: 'put',
    data: data
  })
}

// 修改照片所属相册
export function updatePhotoAlbum(data) {
  return request({
    url: '/photo/photo/album',
    method: 'put',
    data: data
  })
}


// 删除照片管理
export function delPhoto(id) {
  return request({
    url: '/photo/photo/' + id,
    method: 'delete'
  })
}
