package com.wooda.mvvmbasic.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wooda.mvvmbasic.DaggerApplicationComponent
import com.wooda.mvvmbasic.model.MainItemDetail
import com.wooda.mvvmbasic.model.MainListItem

class MainDataSourceFactory: DataSource.Factory<Int, MainListItem>() {

    private val logger = DaggerApplicationComponent.create().logger

    private val _itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, MainListItem>> = MutableLiveData()
    val itemLiveDataSource: LiveData<PageKeyedDataSource<Int, MainListItem>>
        get() = _itemLiveDataSource

    override fun create(): DataSource<Int, MainListItem> {
        logger.d("Creating data source")
        val dataSource = MainPagedDataSource()
        _itemLiveDataSource.postValue(dataSource)
        return dataSource
    }
}

class MainDetailDataSourceFactory(
    private val initialId: String
): DataSource.Factory<Int, MainItemDetail>() {

    private val logger = DaggerApplicationComponent.create().logger

    override fun create(): DataSource<Int, MainItemDetail> {
        logger.d("Creating detail data source.")
        return DetailPagedDataSource(initialId.toInt())
    }
}