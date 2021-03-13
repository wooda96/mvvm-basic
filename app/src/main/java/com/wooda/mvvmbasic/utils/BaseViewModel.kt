package com.wooda.mvvmbasic.utils

import androidx.lifecycle.ViewModel
import com.wooda.mvvmbasic.DaggerApplicationComponent

abstract class BaseViewModel: ViewModel() {

    protected val logger = DaggerApplicationComponent.create().logger

    init {
        logger.lifecycle(this, "instance is created.")
    }

    override fun onCleared() {
        super.onCleared()
        logger.lifecycle(this, "onCleared()")
    }
}