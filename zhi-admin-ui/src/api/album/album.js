import request from '@/utils/request'

// 查询相册管理列表
export function listAlbum(query) {
  return request({
    url: '/album/album/list',
    method: 'get',
    params: query
  })
}

// 查询相册管理详细
export function getAlbum(id) {
  return request({
    url: '/album/album/' + id,
    method: 'get'
  })
}

// 新增相册管理
export function addAlbum(data) {
  return request({
    url: '/album/album',
    method: 'post',
    data: data
  })
}

// 修改相册管理
export function updateAlbum(data) {
  return request({
    url: '/album/album',
    method: 'put',
    data: data
  })
}

// 删除相册管理
export function delAlbum(id) {
  return request({
    url: '/album/album/' + id,
    method: 'delete'
  })
}
