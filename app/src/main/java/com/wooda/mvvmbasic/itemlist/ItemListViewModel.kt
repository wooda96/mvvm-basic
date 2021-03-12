package com.wooda.mvvmbasic.itemlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wooda.mvvmbasic.datasource.MainDataSource
import com.wooda.mvvmbasic.model.MainListItem
import com.wooda.mvvmbasic.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ItemListViewModel: ViewModel() {

    init {
        Logger.d("${this.javaClass.simpleName} is instantiated.")
    }

    override fun onCleared() {
        super.onCleared()
        Logger.d("${this.javaClass.simpleName} - onCleared()")
    }

    private val _status = MutableLiveData<String>("Fragment Status: OK")
    val status: LiveData<String>
        get() = _status

    private val _items: MutableLiveData<List<MainListItem>> by lazy {
        MutableLiveData(listOf<MainListItem>()).also {
            loadItems()
        }
    }
    val items: LiveData<List<MainListItem>>
        get() = _items

    private fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            Logger.d("Starting load main items... isActive: $isActive")
            _status.postValue("Loading...")

            val result = MainDataSource.getAllItems()
            Logger.d("Loading is finished: isActive: $isActive")
            _status.postValue("Done")

            _items.postValue(result)
        }
    }
}