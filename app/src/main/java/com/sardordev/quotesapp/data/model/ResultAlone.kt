package com.sardordev.quotesapp.data.model

data class ResultAlone(
    val authorId: Int,
    val born: String,
    val died: String,
    val name: String,
    val nationality: String,
    val profession: String,
    val quote: String,
    val quoteId: Int,
    val topicId: Int,
    val topicName: String
)