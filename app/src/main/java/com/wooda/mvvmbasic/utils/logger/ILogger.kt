package com.wooda.mvvmbasic.utils.logger

interface ILogger {
    fun d(msg: String)
    fun i(msg: String)
    fun w(msg: String)
    fun e(msg: String)
    fun ex(e: Throwable, msg: String? = null)
    fun lifecycle(target: Any, msg: String)
}