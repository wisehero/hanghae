package com.wisehero.boardapp.api.post.request

import com.wisehero.boardapp.domain.post.Post
import jakarta.validation.constraints.NotBlank

data class PostCreateRequest(
    @NotBlank(message = "제목은 필수 입력값입니다.")
    val title: String,
    @NotBlank(message = "내용은 필수 입력값입니다.")
    val content: String,
    @NotBlank(message = "작성자는 필수 입력값입니다.")
    val author: String,
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    val password: String
) {
    fun toEntity() = Post(
        title = title,
        content = content,
        author = author,
        password = password
    )
}
