package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.request.PostUpdateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.api.post.response.PostReadResponse
import com.wisehero.boardapp.api.post.response.PostUpdateResponse
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

    fun getPosts(): List<PostReadResponse> {
        return postRepository.findAll().map {
            PostReadResponse(
                id = it.id!!,
                title = it.title,
                content = it.content,
                author = it.author,
                createdAt = it.createdAt
            )
        }.sortedByDescending { it.createdAt }
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

    @Transactional
    fun updatePost(id: Long, request: PostUpdateRequest): PostUpdateResponse {
        val post = postRepository.findPostById(id) ?: throw IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        post.updatePostContent(request)
        return PostUpdateResponse(
            id = post.id!!,
            updatedTitle = post.title,
            updatedContent = post.content,
            updatedAt = post.updatedAt
        )
    }

    @Transactional
    fun deletePost(id: Long, password: String) {
        val post = postRepository.findPostById(id) ?: throw IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        require(post.password == password) {
            "비밀번호가 일치하지 않습니다."
        }
        postRepository.delete(post)
    }
}