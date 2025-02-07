package com.wisehero.boardapp.api.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.domain.post.PostService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
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
}