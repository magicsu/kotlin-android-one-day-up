package me.magicsu.one

import android.app.Application
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.xiaomi.channel.commonutils.logger.LoggerInterface
import com.xiaomi.mipush.sdk.Logger
import com.xiaomi.mipush.sdk.MiPushClient
import android.os.Process

/**
 * Created by sushun on 2018/2/21.
 */
class OneApplicationn : Application() {

    companion object {
        lateinit var INSTANCE: OneApplicationn
        private const val TAG_MI = "OneApplicationn"
        private val APP_ID = "2882303761517721198"
        private val APP_KEY = "5801772196198"
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY)
        }

        val newLogger = object : LoggerInterface {
            override fun setTag(tag: String) {
                // ignore
            }

            override fun log(content: String, t: Throwable) {
                Log.d(TAG_MI, content, t)
            }

            override fun log(content: String) {
                Log.d(TAG_MI, content)
            }
        }
        Logger.setLogger(this, newLogger)
    }

    private fun shouldInit(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = Process.myPid()
        return processInfos.any { it.pid == myPid && mainProcessName == it.processName }
    }
}