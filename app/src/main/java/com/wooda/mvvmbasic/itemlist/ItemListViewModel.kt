package com.wooda.mvvmbasic.itemlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.wooda.mvvmbasic.datasource.MainDataSourceFactory
import com.wooda.mvvmbasic.datasource.MainPagedDataSource
import com.wooda.mvvmbasic.model.MainListItem
import com.wooda.mvvmbasic.utils.BaseViewModel

class ItemListViewModel: BaseViewModel() {

    private val _status = MutableLiveData<String>("Fragment Status: OK")
    val status: LiveData<String>
        get() = _status

    private val itemLiveDataSource: LiveData<PageKeyedDataSource<Int, MainListItem>>
    val itemPagedList: LiveData<PagedList<MainListItem>>

    init {
        val itemDataSourceFactory = MainDataSourceFactory()
        itemLiveDataSource = itemDataSourceFactory.itemLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MainPagedDataSource.PageSize)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
            .build()
    }
}