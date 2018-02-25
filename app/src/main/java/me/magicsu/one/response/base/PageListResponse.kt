package me.magicsu.one.response.base

data class PageListResponse<T>(
        var totalRow: Int = 0,
        var pageNumber: Int = 0,
        var totalPage: Int = 0,
        var pageSize: Int = 0,
        var list: List<T>?) : BaseResponse()