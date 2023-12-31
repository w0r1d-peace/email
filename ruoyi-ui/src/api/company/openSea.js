import request from "@/utils/request";

export function getOpenSeaList(data) {
    return request({
        url: '/customer/limits/list',
        method: 'get',
    })
}

export function openSeaEdit(data) {
    return request({
        url: '/customer/limits/edit',
        method: 'post',
        data
    })
}
