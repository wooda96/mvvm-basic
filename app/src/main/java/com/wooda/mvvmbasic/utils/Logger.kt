package com.wooda.mvvmbasic.utils

import android.util.Log

object Logger {
    private val Tag = "MvvmBasic"

    fun d(msg: String) {
        Log.d(Tag, msg)
    }

    fun i(msg: String) {
        Log.i(Tag, msg)
    }

    fun lifecycle(target: Any, msg: String) {
        Log.i(Tag, "[LifeCycle] ${target.javaClass.simpleName} - $msg")
    }
}