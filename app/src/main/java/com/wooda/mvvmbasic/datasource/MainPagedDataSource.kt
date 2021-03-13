package com.wooda.mvvmbasic.datasource

import androidx.paging.PageKeyedDataSource
import com.wooda.mvvmbasic.model.MainListItem
import com.wooda.mvvmbasic.utils.Logger
import java.time.LocalDateTime

class MainPagedDataSource: PageKeyedDataSource<Int, MainListItem>() {

    companion object {
        const val PageSize = 10
        private const val FirstPage = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MainListItem>
    ) {
        Logger.d("Trying load initial page - load size: ${params.requestedLoadSize}")
        val result = generateListItem(FirstPage)

        callback.onResult(result, null, FirstPage + 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MainListItem>) {
        Logger.d("Trying load next page: key: ${params.key}")
        val result = generateListItem(params.key)

        callback.onResult(result, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MainListItem>) {
        Logger.d("Trying load previous page: key: ${params.key}")
        val result = generateListItem(params.key)

        val adjacentKey = if (params.key > 1)
            params.key - 1
        else
            null

        callback.onResult(result, adjacentKey)
    }

    private fun generateListItem(page: Int): List<MainListItem> {

        val startIndex = (page - 1) * PageSize
        val endIndex = page * PageSize - 1

        Logger.d("Generating item $startIndex to $endIndex")
        Thread.sleep(1000)

        val result = mutableListOf<MainListItem>()
        for (i in startIndex..endIndex) {
            result.add(
                MainListItem(
                    id = i.toString(),
                    title = "Item $i",
                    registerTime = LocalDateTime.now()
                )
            )
        }

        return result
    }
}