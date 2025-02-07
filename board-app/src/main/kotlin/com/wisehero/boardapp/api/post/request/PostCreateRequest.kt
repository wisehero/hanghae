package com.wisehero.boardapp.api.post.request

import com.wisehero.boardapp.domain.post.Post

data class PostCreateRequest(
    val title: String,
    val content: String,
    val author: String,
    val password: String
) {
    fun toEntity() = Post(
        title = title,
        content = content,
        author = author,
        password = password
    )
}
