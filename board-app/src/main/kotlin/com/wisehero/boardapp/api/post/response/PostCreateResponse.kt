package com.wisehero.boardapp.api.post.response

import java.time.LocalDateTime

data class PostCreateResponse(
    val title: String,
    val content: String,
    val author: String,
    val createdAt: LocalDateTime
)
