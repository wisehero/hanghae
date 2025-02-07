package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.api.post.response.PostReadResponse
import com.wisehero.boardapp.util.isValidPassword

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
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

    fun getPost(id: Long): PostReadResponse {
        val post = postRepository.findPostById(id) ?: throw IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        return PostReadResponse(
            id = post.id!!,
            title = post.title,
            content = post.content,
            author = post.author,
            createdAt = post.createdAt
        )
    }
}