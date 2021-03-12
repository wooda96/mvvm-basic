package com.wooda.mvvmbasic

import androidx.lifecycle.ViewModel
import com.wooda.mvvmbasic.utils.Logger

class MainViewModel: ViewModel() {
    init {
        Logger.d("${this.javaClass.simpleName} is instantiated.")
    }

    private val _title: String = "MainViewModel Title."
    val title: String
        get() = _title

    override fun onCleared() {
        super.onCleared()
        Logger.d("${this.javaClass.simpleName} - onCleared()")
    }
}