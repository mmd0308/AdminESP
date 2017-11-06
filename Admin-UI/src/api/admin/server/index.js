import fetch from 'utils/fetch'

export function page(query) {
  return fetch({
    url: '/api/admin/server/all',
    method: 'get',
    params: query
  })
}

export function getImages(query) {
  return fetch({
    url: '/api/admin/server/getImages',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return fetch({
    url: '/api/admin/server/add',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return fetch({
    url: '/api/admin/server/get/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return fetch({
    url: '/api/admin/server/delete/' + id,
    method: 'delete'
  })
}

export function putObj(id, obj) {
  return fetch({
    url: '/api/admin/server/put/' + id,
    method: 'put',
    data: obj
  })
}
