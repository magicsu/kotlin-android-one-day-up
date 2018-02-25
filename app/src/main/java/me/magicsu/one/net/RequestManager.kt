package me.magicsu.one.net

/**
 * Created by sushun on 2018/2/21.
 */
class RequestManager {

    companion object {
        private var mApiService: ApiService? = null
        private var INSTANCE: RequestManager? = null

        fun getInstance(): RequestManager {
            return INSTANCE ?: RequestManager().apply {
                INSTANCE = this
                mApiService = RetrofitClient.getInstance()
                        .create(ApiService::class.java)
            }
        }
    }

    fun getApiService(): ApiService {
        return mApiService!!
    }
}