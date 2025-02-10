package com.wisehero.boardapp.api.post.response

import java.time.LocalDateTime

data class PostUpdateResponse(
    val id: Long,
    val updatedTitle: String,
    val updatedContent: String,
    val updatedAt: LocalDateTime
)
