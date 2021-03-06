package com.wooda.mvvmbasic.model

import java.time.LocalDateTime

data class MainListItem(
    val id: String,
    val title: String,
    val registerTime: LocalDateTime
)