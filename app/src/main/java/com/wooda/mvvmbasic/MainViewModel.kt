package com.wooda.mvvmbasic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wooda.mvvmbasic.utils.Logger

class MainViewModel: ViewModel() {

    init {
        Logger.d("${this.javaClass.simpleName} is instantiated.")
    }

    override fun onCleared() {
        super.onCleared()
        Logger.d("${this.javaClass.simpleName} - onCleared()")
    }

    private val _status = MutableLiveData<String>("Main Status: OK")
    val status: LiveData<String>
        get() = _status
}