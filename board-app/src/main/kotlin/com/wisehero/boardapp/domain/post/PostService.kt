package com.wisehero.boardapp.domain.post

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional
    fun createPost() {

    }

}