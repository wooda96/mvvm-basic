package com.wooda.mvvmbasic.utils

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    init {
        Logger.lifecycle(this, "instance is created.")
    }

    override fun onCleared() {
        super.onCleared()
        Logger.lifecycle(this, "onCleared()")
    }
}