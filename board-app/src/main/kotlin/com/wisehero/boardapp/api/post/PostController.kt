package com.wisehero.boardapp.api.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.request.PostUpdateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.api.post.response.PostReadResponse
import com.wisehero.boardapp.api.post.response.PostUpdateResponse
import com.wisehero.boardapp.domain.post.PostService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService
) {

    /**
     * 게시글 생성 API
     */
    @PostMapping
    fun createPost(@Valid @RequestBody request: PostCreateRequest): PostCreateResponse {
        return postService.createPost(request);
    }

    /**
     * 게시글 목록 조회 API
     */
    @GetMapping
    fun getPosts() : List<PostReadResponse> {
        return postService.getPosts();
    }

    /**
     * 게시글 상세 조회 API
     */
    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): PostReadResponse {
        return postService.getPost(id)
    }

    /**
     * 게시글 수정 API
     */
    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @Valid @RequestBody request: PostUpdateRequest): PostUpdateResponse {
        return postService.updatePost(id, request)
    }

    /**
     * 게시글 삭제 API
     */
    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long, @RequestBody password: String) {
        postService.deletePost(id, password)
    }
}