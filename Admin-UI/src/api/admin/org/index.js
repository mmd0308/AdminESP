import fetch from 'utils/fetch'

export function orgTree(query) {
  return fetch({
    url: '/api/admin/org/orgTree',
    method: 'get',
    params: query
  })
}

export function getObj(id) {
  return fetch({
    url: '/api/admin/org/' + id,
    method: 'get'
  })
}

export function addObj(obj) {
  return fetch({
    url: '/api/admin/org',
    method: 'post',
    data: obj
  })
}

export function putObj(id, obj) {
  return fetch({
    url: '/api/admin/org/' + id,
    method: 'put',
    data: obj
  })
}

export function getNextCode(id) {
  return fetch({
    url: '/api/admin/org/getNextCode',
    method: 'get',
    params: id
  })
}
