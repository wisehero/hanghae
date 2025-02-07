package com.wisehero.boardapp.domain.post

import io.mockk.every
import io.mockk.mockk
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class PostServiceTest @Autowired constructor(
    private val postRepository: PostRepository,
    private val postService: PostService
) {


    @AfterEach
    fun teardown() {
        postRepository.deleteAll()
    }

    @Test
    @DisplayName("Post 생성 테스트")
    fun 게시글_생성_테스트() {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "password123"
        )
        val post = request.toEntity();

        // when
        postService.createPost(post);


        // then
        val savedPost = postRepository.findAll().first();
        assertThat(savedPost.id).isNotNull
        assertThat(savedPost.title).isEqualTo(request.title)
        assertThat(savedPost.createdAt).isNotNull
        assertThat(savedPost.updatedAt).isNull()
    }

}