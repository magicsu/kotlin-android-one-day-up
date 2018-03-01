package me.magicsu.one.receiver

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.xiaomi.mipush.sdk.*

/**
 * Created by sushun on 2018/2/26.
 */
class MiPushReceiver : PushMessageReceiver() {

    private var mRegId: String? = null
    private var mTopic: String? = null
    private var mAlias: String? = null
    private var mAccount: String? = null
    private var mStartTime: String? = null
    private var mEndTime: String? = null

    companion object {
        const val TAG = "MiPushReceiver"
    }

    override fun onReceivePassThroughMessage(context: Context, message: MiPushMessage) {
        Log.v(TAG,
                "onReceivePassThroughMessage is called. " + message.toString())

        if (!TextUtils.isEmpty(message.topic)) {
            mTopic = message.topic
        } else if (!TextUtils.isEmpty(message.alias)) {
            mAlias = message.alias
        }

    }

    override fun onNotificationMessageClicked(context: Context, message: MiPushMessage) {
        Log.v(TAG,
                "onNotificationMessageClicked is called. " + message.toString())

        if (!TextUtils.isEmpty(message.topic)) {
            mTopic = message.topic
        } else if (!TextUtils.isEmpty(message.alias)) {
            mAlias = message.alias
        }

    }

    override fun onNotificationMessageArrived(context: Context, message: MiPushMessage) {
        Log.v(TAG,
                "onNotificationMessageArrived is called. " + message.toString())

        if (!TextUtils.isEmpty(message.topic)) {
            mTopic = message.topic
        } else if (!TextUtils.isEmpty(message.alias)) {
            mAlias = message.alias
        }
    }

    override fun onCommandResult(context: Context, message: MiPushCommandMessage) {
        Log.v(TAG,
                "onCommandResult is called. " + message.toString())
        val command = message.command
        val arguments = message.commandArguments
        val cmdArg1 = if (arguments != null && arguments.size > 0) arguments[0] else null
        val cmdArg2 = if (arguments != null && arguments.size > 1) arguments[1] else null
        val log: String
        if (MiPushClient.COMMAND_REGISTER == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mRegId = cmdArg1
            }
        } else if (MiPushClient.COMMAND_SET_ALIAS == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mAlias = cmdArg1
            }
        } else if (MiPushClient.COMMAND_UNSET_ALIAS == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mAlias = cmdArg1
            }
        } else if (MiPushClient.COMMAND_SET_ACCOUNT == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mAccount = cmdArg1
            }
        } else if (MiPushClient.COMMAND_UNSET_ACCOUNT == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mAccount = cmdArg1
            }
        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mTopic = cmdArg1
            }
        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mTopic = cmdArg1
            }
        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mStartTime = cmdArg1
                mEndTime = cmdArg2
            }
        }
    }

    override fun onReceiveRegisterResult(context: Context, message: MiPushCommandMessage) {
        Log.v(TAG,
                "onReceiveRegisterResult is called. " + message.toString())
        val command = message.command
        val arguments = message.commandArguments
        val cmdArg1 = if (arguments != null && arguments.size > 0) arguments[0] else null
        val log: String
        if (MiPushClient.COMMAND_REGISTER == command) {
            if (message.resultCode.compareTo(ErrorCode.SUCCESS) == 0) {
                mRegId = cmdArg1
            }
        } else {
            log = message.reason
        }
    }
}