package com.wooda.mvvmbasic.datasource

import com.wooda.mvvmbasic.model.MainListItem
import java.time.LocalDateTime

object MainDataSource {
    fun getAllItems(): List<MainListItem> {
        Thread.sleep(5_000L)
        val result = mutableListOf<MainListItem>()
        for (i in 0..100) {
            result.add(
                MainListItem(
                title = "Item #$i",
                registerTime = LocalDateTime.now()
            ))
        }

        return result
    }
}