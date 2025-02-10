package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostUpdateRequest
import com.wisehero.boardapp.domain.BaseTimeEntity
import jakarta.persistence.*

@Entity
class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    @Column(nullable = false)
    var author: String,

    @Column(nullable = false)
    var password: String
) : BaseTimeEntity() {

    fun updatePostContent(updateRequest: PostUpdateRequest) {
        require(updateRequest.password == this.password) {
            "비밀번호가 일치하지 않습니다."
        }
        if (updateRequest.title.isNotBlank()) {
            this.title = updateRequest.title
        }
        if (updateRequest.content.isNotBlank()) {
            this.content = updateRequest.content
        }
    }
}