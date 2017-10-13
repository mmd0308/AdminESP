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
