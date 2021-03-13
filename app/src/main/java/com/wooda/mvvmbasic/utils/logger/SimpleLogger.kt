package com.wooda.mvvmbasic.utils.logger

import android.util.Log
import javax.inject.Inject

class SimpleLogger @Inject constructor(): ILogger {

    companion object {
        private val Tag = "MvvmBasicLog"
    }

    init {
        Log.d(Tag, "[LoggerTrack] SimpleLogger is created.")
    }

    override fun d(msg: String) {
        Log.d(Tag, msg)
    }

    override fun i(msg: String) {
        Log.i(Tag, msg)
    }

    override fun w(msg: String) {
        Log.w(Tag, msg)
    }

    override fun e(msg: String) {
        Log.e(Tag, msg)
    }

    override fun ex(e: Throwable, msg: String?) {
        val logMsg = msg ?: e.message ?: "Unknown exception"
        Log.e(Tag, logMsg)
    }

    override fun lifecycle(target: Any, msg: String) {
        Log.i(Tag, "[LifeCycle] ${target.javaClass.simpleName} - $msg")
    }
}