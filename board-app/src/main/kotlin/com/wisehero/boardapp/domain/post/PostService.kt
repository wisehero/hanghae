package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.util.isValidPassword
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional
    fun createPost(request: PostCreateRequest): PostCreateResponse {
        require(isValidPassword(request.password)) {
            "비밀번호는 영어 대소문자, 숫자, 특수문자를 포함한 8자 이상 20자 이하여야 합니다."
        }

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