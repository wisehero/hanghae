package com.wisehero.boardapp.domain.post

import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.request.PostUpdateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostServiceTest @Autowired constructor(
    private val postRepository: PostRepository,
    private val postService: PostService
) {


    @AfterEach
    fun teardown() {
        postRepository.deleteAll()
    }

    @Test
    fun `게시글이_생성되면_제목_작성자_내용_작성날짜가 반환된다`() {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "Abcdefg1!@#"
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
    @DisplayName("Testable Code -> 기댓값을 변수나 필드로 표현하지 않기")
    fun `게시글이_생성되면_제목_작성자_내용_작성날짜가 반환된다 Version 2`() {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "Abcdefg1!@#"
        )

        // when
        val savedPost: PostCreateResponse = postService.createPost(request)

        // then
        assertThat(savedPost.title).isEqualTo("테스트 제목")
        assertThat(savedPost.content).isEqualTo("테스트 내용")
        assertThat(savedPost.author).isEqualTo("작성자")
        assertThat(savedPost.createdAt).isNotNull
    }

    @ParameterizedTest(name = "잘못된 비밀번호 {0}는 게시글 작성에 실패한다.")
    @ValueSource(
        strings = [
            "password",         // 소문자만, 특수문자, 숫자, 대문자 부족
            "PASSWORD",         // 대문자만, 나머지 부족
            "Passw1",           // 길이가 8자 미만
            "Pass1234",         // 특수문자 부족
            "pass1234!",        // 대문자 부족
            "PASS1234!",         // 소문자 부족
            "12345678",          // 영문자, 특수문자 부족
            "12345678!",         // 영문자 대문자, 소문자 부족
            "12345678A",         // 특수문자 부족
            "12345678!",         // 영문자 대문자, 소문자 부족
            "1231231231231231231" // 길이가 20자 초과
        ]
    )
    fun `패스워드는 영어 대소문자, 숫자, 특수문자를 포함한 8자이상 20자 이하여야 한다 규칙에 맞지않으면 게시글 작성에 실패한다`(wrongPassword: String) {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = wrongPassword
        )

        // when then
        assertThatThrownBy { postService.createPost(request) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("비밀번호는 영어 대소문자, 숫자, 특수문자를 포함한 8자 이상 20자 이하여야 합니다.")
    }

    @Test
    fun `게시글 ID로 게시글 상세를 조회할 수 있다`() {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "Abcdefg1!@#"
        )
        postRepository.save(request.toEntity())

        // when
        val post = postService.getPost(1L)

        // then
        assertThat(post.id).isEqualTo(1L)
        assertThat(post.title).isEqualTo("테스트 제목")
        assertThat(post.content).isEqualTo("테스트 내용")
        assertThat(post.author).isEqualTo("작성자")
        assertThat(post.createdAt).isNotNull
    }

    @Test
    fun `존재하지 않는 게시글 ID로 게시글 상세를 조회하면 예외가 발생한다`() {
        // when then
        assertThatThrownBy { postService.getPost(1L) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("해당 ID의 게시글이 존재하지 않습니다.")
    }

    @Test
    fun `게시글 수정 성공 테스트`() {
        // given
        val request = PostUpdateRequest(
            title = "수정된 제목",
            content = "수정된 내용",
            password = "Abcdefg123!"
        )
        val post = Post(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "Abcdefg123!"
        )
        val savedPost = postRepository.save(post)

        // when
        val updatedPost = postService.updatePost(savedPost.id!!, request)

        // then
        assertThat(updatedPost.id).isEqualTo(savedPost.id)
        assertThat(updatedPost.updatedTitle).isEqualTo("수정된 제목")
        assertThat(updatedPost.updatedContent).isEqualTo("수정된 내용")
        assertThat(updatedPost.updatedAt).isNotNull
    }

    @Test
    fun `게시글 삭제 테스트`() {
        // given
        val Post = Post(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "Abcdefg123!"
        )
        val savedPost = postRepository.save(Post)

        // when
        postService.deletePost(savedPost.id!!, savedPost.password)

        // then
        assertThat(postRepository.findById(1L).isPresent).isFalse
    }
}