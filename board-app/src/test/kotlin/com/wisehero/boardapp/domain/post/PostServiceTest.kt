package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import jakarta.transaction.Transactional
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
    fun `게시글이_생성되면_제목_작성자_내용_작성날짜가 반환된다`() {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "password123"
        )

        // when
        val savedPost: PostCreateResponse = postService.createPost(request)

        // then
        assertThat(savedPost.title).isEqualTo(request.title)
        assertThat(savedPost.content).isEqualTo(request.content)
        assertThat(savedPost.author).isEqualTo(request.author)
        assertThat(savedPost.createdAt).isNotNull
    }

    @Test
    fun `예외 케이스 1 - 제목이 없는 경우엔 예외가 발생한다`() {
        // given
        val request = PostCreateRequest(
            title = "",
            content = "테스트 내용",
            author = "작성자",
            password = "password123"
        )

        // when, then
        assertThatThrownBy { postService.createPost(request) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("제목은 필수 입력값입니다.")
    }

}