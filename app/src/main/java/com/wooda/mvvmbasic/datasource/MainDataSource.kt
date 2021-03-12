package com.wooda.mvvmbasic.datasource

import com.wooda.mvvmbasic.model.MainItemDetail
import com.wooda.mvvmbasic.model.MainListItem
import java.time.LocalDateTime

object MainDataSource {
    fun getAllItems(): List<MainListItem> {
        Thread.sleep(2_000L)
        val result = mutableListOf<MainListItem>()
        for (i in 0..100) {
            result.add(
                MainListItem(
                    id = i.toString(),
                    title = "Item #$i",
                    registerTime = LocalDateTime.now()
            ))
        }

        return result
    }

    fun loadDetail(id: String): MainItemDetail {
        Thread.sleep(2_000L)
        return MainItemDetail(
            id = id,
            message = "Detail item message $id"
        )
    }
}