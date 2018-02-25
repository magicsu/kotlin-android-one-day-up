package me.magicsu.one.net

import io.reactivex.Observable
import me.magicsu.one.BuildConfig
import me.magicsu.one.model.VideoResource
import me.magicsu.one.response.DailyVideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sushun on 2018/2/21.
 */
interface ApiService {

    @GET("v1/dailyVideos/")
    fun dailyVideos(@Query("userId") userId: Long?,
                   @Query("pageNum") pageNum: Int,
                   @Query("pageSize") pageSize: Int): Observable<DailyVideoResponse>
}