package me.magicsu.one.util

import android.content.Context
import android.os.Build
import android.provider.Settings
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

/**
 * Created by sushun on 2018/2/26.
 */
object DeviceUtils {

    fun getDeviceUniqueId(context: Context): String {
        val androidID = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val id = androidID + Build.SERIAL
        return try {
            toMD5(id)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            id
        }
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun toMD5(text: String): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        val digest = messageDigest.digest(text.toByteArray())
        val sb = StringBuilder()
        for (i in digest.indices) {
            val digestInt = digest[i] and 0xff.toByte()
            val hexString = Integer.toHexString(digestInt.toInt())
            if (hexString.length < 2) {
                sb.append(0)
            }
            sb.append(hexString)
        }
        return sb.toString()
    }
}