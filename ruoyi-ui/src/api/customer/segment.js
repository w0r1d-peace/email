import request from "@/utils/request";

// 客群-用户列表
export function getSegmentUserList() {
  return request({
    url: '/customer/segment/user/list',
    method: 'get',
  })
}

// 客群管理列表
export function getSegmentList(params) {
  return request({
    url: '/customer/segment/list',
    method: 'get',
    params
  })
}

export function addSegment(data) {
  return request({
    url: '/customer/segment/add',
    method: 'post',
    data
  })
}

export function editSegment(data) {
  return request({
    url: '/customer/segment/edit',
    method: 'post',
    data
  })
}

export function deleteSegment(data) {
  return request({
    url: '/customer/segment/delete',
    method: 'post',
    data
  })
}

// 获取规则字段
export function getRuleField() {
  return request({
    url: '/customer/segment/get/condition/rule/column',
    method: 'get',
  })
}