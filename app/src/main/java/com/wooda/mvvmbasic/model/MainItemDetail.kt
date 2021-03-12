package com.wooda.mvvmbasic.model

data class MainItemDetail(
    val id: String,
    val message: String
) {
    companion object {
        val Empty = MainItemDetail(
            id = "?",
            message = "??"
        )
    }
}