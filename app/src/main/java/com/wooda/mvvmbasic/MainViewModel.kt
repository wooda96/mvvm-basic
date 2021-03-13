package com.wooda.mvvmbasic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wooda.mvvmbasic.utils.BaseViewModel

class MainViewModel: BaseViewModel() {

    private val _status = MutableLiveData<String>("Main Status: OK")
    val status: LiveData<String>
        get() = _status
}