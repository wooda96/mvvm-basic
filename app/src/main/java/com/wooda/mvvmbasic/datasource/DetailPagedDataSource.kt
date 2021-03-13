package com.wooda.mvvmbasic.datasource

import androidx.paging.PageKeyedDataSource
import com.wooda.mvvmbasic.DaggerApplicationComponent
import com.wooda.mvvmbasic.model.MainItemDetail

class DetailPagedDataSource(
    private val FirstPage: Int
): PageKeyedDataSource<Int, MainItemDetail>() {

    private val logger = DaggerApplicationComponent.create().logger

    companion object {
        const val PageSize = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MainItemDetail>
    ) {
        logger.d("Trying load detail initial page, requested size: ${params.requestedLoadSize}")
        val result = generateDetail(FirstPage)

        callback.onResult(result, getPrevPageKey(FirstPage), FirstPage + 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MainItemDetail>) {
        logger.d("Trying load next detail, key: ${params.key}")
        val result = generateDetail(params.key)

        callback.onResult(result, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MainItemDetail>) {
        logger.d("Trying load prev detail, key: ${params.key}")
        val result = generateDetail(params.key)

        callback.onResult(result, getPrevPageKey(params.key))
    }

    private fun getPrevPageKey(thisPage: Int) = if (thisPage > 0)
        thisPage - 1
    else
        null

    private fun generateDetail(id: Int): List<MainItemDetail> {
        logger.d("Generating item $id")

        Thread.sleep(2_000)
        return listOf(
            MainItemDetail(
            id = id.toString(),
            message = "Detail item message $id"
        ))
    }
}