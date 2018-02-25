package me.magicsu.one

import android.app.Application

/**
 * Created by sushun on 2018/2/21.
 */
class OneApplicationn : Application() {

    companion object {
        lateinit var INSTANCE: OneApplicationn
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}