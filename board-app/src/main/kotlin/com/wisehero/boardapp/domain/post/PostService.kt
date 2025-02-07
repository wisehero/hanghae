package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional
    fun createPost(request: PostCreateRequest): PostCreateResponse {
        val post: Post = request.toEntity()

        val savedPost: Post = postRepository.save(post)

        return PostCreateResponse(
            title = savedPost.title,
            content = savedPost.content,
            author = savedPost.author,
            createdAt = savedPost.createdAt
        )
    }

}