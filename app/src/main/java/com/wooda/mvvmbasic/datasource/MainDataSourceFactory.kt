package com.wooda.mvvmbasic.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wooda.mvvmbasic.model.MainListItem
import com.wooda.mvvmbasic.utils.Logger

class MainDataSourceFactory: DataSource.Factory<Int, MainListItem>() {

    private val _itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, MainListItem>> = MutableLiveData()
    val itemLiveDataSource: LiveData<PageKeyedDataSource<Int, MainListItem>>
        get() = _itemLiveDataSource

    override fun create(): DataSource<Int, MainListItem> {
        Logger.d("Creating data source")
        val dataSource = MainPagedDataSource()
        _itemLiveDataSource.postValue(dataSource)
        return dataSource
    }
}