package me.magicsu.one.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import me.magicsu.one.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
class RetrofitClient {

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    companion object {
        private const val DEFAULT_TIMEOUT: Long = 15

        @Volatile
        var INSTANCE: RetrofitClient? = null

        fun getInstance(): RetrofitClient {
            if (INSTANCE == null) {
                synchronized(RetrofitClient::class) {
                    if (INSTANCE == null) {
                        INSTANCE = RetrofitClient()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    init {
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_SERVER_URL)
                .build()
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }
}