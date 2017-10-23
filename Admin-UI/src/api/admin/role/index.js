import fetch from 'utils/fetch';

export function fetchTree(query) {
  return fetch({
    url: '/api/admin/role/tree',
    method: 'get',
    params: query
  });
}

export function getNextCode(parentId) {
  return fetch({
    url: '/api/admin/role/getNextCode',
    method: 'get',
    params: {parentId: parentId}
  });
}

export function checkCode(value, menuId) {
  const param = {code: value, id: menuId};
  return fetch({
    url: '/api/admin/role/checkCode',
    method: 'get',
    params: param
  });
}

export function addObj(obj) {
  return fetch({
    url: '/api/admin/role/add',
    method: 'post',
    data: obj
  });
}

export function getObj(id) {
  return fetch({
    url: '/api/admin/role/get/' + id,
    method: 'get'
  });
}

export function delObj(id) {
  return fetch({
    url: '/api/admin/role/delete/' + id,
    method: 'delete'
  });
}

export function putObj(id, obj) {
  return fetch({
    url: '/api/admin/role/put/' + id,
    method: 'put',
    data: obj
  });
}

