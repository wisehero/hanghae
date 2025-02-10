package com.wisehero.boardapp.api.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.request.PostUpdateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.api.post.response.PostReadResponse
import com.wisehero.boardapp.api.post.response.PostUpdateResponse
import com.wisehero.boardapp.domain.post.PostService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(@Valid @RequestBody request: PostCreateRequest): PostCreateResponse {
        return postService.createPost(request);
    }

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): PostReadResponse {
        return postService.getPost(id)
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @Valid @RequestBody request: PostUpdateRequest): PostUpdateResponse {
        return postService.updatePost(id, request)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long, @RequestBody password: String) {
        postService.deletePost(id, password)
    }
}