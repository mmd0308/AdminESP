import fetch from 'utils/fetch'

export function dictTree(query) {
  return fetch({
    url: '/api/admin/dict/dictTree',
    method: 'get',
    params: query
  })
}

export function getObj(id) {
  return fetch({
    url: '/api/admin/dict/get/' + id,
    method: 'get'
  })
}

export function addObj(obj) {
  return fetch({
    url: '/api/admin/dict/add',
    method: 'post',
    data: obj
  })
}

export function putObj(id, obj) {
  return fetch({
    url: '/api/admin/dict/put/' + id,
    method: 'put',
    data: obj
  })
}

export function getNextCode(id) {
  return fetch({
    url: '/api/admin/dict/getNextCode',
    method: 'get',
    params: id
  })
}

export function checkCode(id, code) {
  return fetch({
    url: '/api/admin/dict/checkCode',
    method: 'get',
    params: id, code
  })
}

export function delObj(id) {
  return fetch({
    url: '/api/admin/dict/deleted',
    method: 'delete',
    params: id
  })
}

