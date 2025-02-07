package com.wisehero.boardapp.api.post

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController {

    @PostMapping
    fun createPost() {

    }
}