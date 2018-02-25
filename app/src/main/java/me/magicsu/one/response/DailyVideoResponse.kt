package me.magicsu.one.response

import me.magicsu.one.model.VideoResource
import me.magicsu.one.response.base.BaseResponse
import me.magicsu.one.response.base.PageListResponse

/**
 * Created by sushun on 2018/2/25.
 */
data class DailyVideoResponse(var data: PageListResponse<VideoResource>) : BaseResponse()