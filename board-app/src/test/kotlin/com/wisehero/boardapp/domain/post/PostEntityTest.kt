package com.wisehero.boardapp.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class PostEntityTest {

    @Autowired
    private lateinit var postRepository: PostRepository

    @Test
    fun `Post Entity 저장 테스트`() {
        // given
        val post = Post(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "password123"
        )

        // when
        val savedPost = postRepository.save(post)

        // then
        assertThat(savedPost.id).isNotNull
        assertThat(savedPost.title).isEqualTo(post.title)
        assertThat(savedPost.content).isEqualTo(post.content)
        assertThat(savedPost.author).isEqualTo(post.author)
        assertThat(savedPost.password).isEqualTo(post.password)
        assertThat(savedPost.createdAt).isNotNull
        assertThat(savedPost.updatedAt).isNotNull
    }

    @Test
    fun `Post Entity 조회 테스트`() {
        // given
        val post = Post(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "password123"
        )
        val savedPost = postRepository.save(post)

        // when
        val foundPost = postRepository.findById(savedPost.id!!).orElse(null)

        assertThat(foundPost).isNotNull
        assertThat("테스트 제목").isEqualTo(foundPost.title)
        assertThat("테스트 내용").isEqualTo(foundPost.content)
        assertThat("작성자").isEqualTo(foundPost.author)
        assertThat("password123").isEqualTo(foundPost.password)
    }
}

