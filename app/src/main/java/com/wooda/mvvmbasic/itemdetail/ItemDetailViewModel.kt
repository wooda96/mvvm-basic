package com.wooda.mvvmbasic.itemdetail

import androidx.lifecycle.*
import com.wooda.mvvmbasic.datasource.MainDataSource
import com.wooda.mvvmbasic.model.MainItemDetail
import com.wooda.mvvmbasic.utils.BaseViewModel
import com.wooda.mvvmbasic.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    val id: String
): BaseViewModel() {

    private val _status = MutableLiveData<String>("Detail Fragment Status: Ready")
    val status: LiveData<String>
        get() = _status

    private val _detail: MutableLiveData<MainItemDetail> by lazy {
        MutableLiveData(MainItemDetail.Empty).also {
            loadDetail()
        }
    }
    val detail: LiveData<MainItemDetail>
        get() = _detail

    private fun loadDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            Logger.d("Starting loading item detail... isActive: $isActive")
            _status.postValue("Loading detail id: $id")

            val result = MainDataSource.loadDetail(id)
            Logger.d("Loading detail $id finished. isActive: $isActive")
            _status.postValue("Loading done.")
            _detail.postValue(result)
        }
    }
}

class ItemDetailViewModelFactory(
    private val id: String
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ItemDetailViewModel(id) as T
}