package com.wooda.mvvmbasic.itemdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wooda.mvvmbasic.datasource.DetailPagedDataSource
import com.wooda.mvvmbasic.datasource.MainDetailDataSourceFactory
import com.wooda.mvvmbasic.model.MainItemDetail
import com.wooda.mvvmbasic.utils.BaseViewModel

class ItemDetailMainViewModel(
    initialId: String
): BaseViewModel() {

    val detailPagedList: LiveData<PagedList<MainItemDetail>>

    init {
        val dataSourceFactory = MainDetailDataSourceFactory(initialId)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(DetailPagedDataSource.PageSize)
            .build()

        detailPagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .build()
    }
}

class ItemDetailMainViewModelFactory(
    private val initialId: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ItemDetailMainViewModel(initialId) as T
}