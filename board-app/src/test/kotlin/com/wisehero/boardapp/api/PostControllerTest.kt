package com.wisehero.boardapp.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wisehero.boardapp.api.post.PostController
import com.wisehero.boardapp.api.post.request.PostCreateRequest
import com.wisehero.boardapp.api.post.request.PostUpdateRequest
import com.wisehero.boardapp.api.post.response.PostCreateResponse
import com.wisehero.boardapp.api.post.response.PostReadResponse
import com.wisehero.boardapp.api.post.response.PostUpdateResponse
import com.wisehero.boardapp.domain.post.PostService
import com.wisehero.boardapp.security.SecurityConfig
import jakarta.persistence.PostUpdate
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PostControllerTest(
    @Autowired
    val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

    @MockitoBean
    lateinit var postService: PostService


    @Test
    fun `게시글 생성 API 테스트`() {
        // given
        val request = PostCreateRequest(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            password = "Abcdefg1!@#"
        )
        val expectedResponse = PostCreateResponse(
            title = "테스트 제목",
            content = "테스트 내용",
            author = "작성자",
            createdAt = LocalDateTime.now()
        )
        given(postService.createPost(request)).willReturn(expectedResponse)
        val jsonRequest = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
            post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("테스트 제목"))
            .andExpect(jsonPath("$.content").value("테스트 내용"))
            .andExpect(jsonPath("$.author").value("작성자"))
            .andExpect(jsonPath("$.createdAt").exists())
    }

    @ParameterizedTest
    @MethodSource("invalidRequests")
    fun `게시글 생성시 PostCreateRequest에서 제한한 유효성 검사를 통과 실패하면 400`(request: PostCreateRequest) {
        mockMvc.perform(
            post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest)
    }

    companion object {
        @JvmStatic
        fun invalidRequests() = listOf(
            Arguments.of(
                PostCreateRequest("", "content", "author", "pass"),
                "title이 비어있을 때"
            ),
            Arguments.of(
                PostCreateRequest("title", "", "author", "pass"),
                "content가 비어있을 때"
            ),
            Arguments.of(
                PostCreateRequest("title", "content", "", "pass"),
                "author가 비어있을 때"
            ),
            Arguments.of(
                PostCreateRequest("title", "content", "author", ""),
                "password가 비어있을 때"
            )
        )
    }

    @Test
    fun `게시글 목록 조회 API 테스트`() {
        // given
        val posts = listOf(
            PostReadResponse(
                id = 1,
                title = "테스트 제목1",
                author = "작성자1",
                content = "내용1",
                createdAt = LocalDateTime.now()
            ),
            PostReadResponse(
                id = 2,
                title = "테스트 제목2",
                author = "작성자2",
                content = "내용2",
                createdAt = LocalDateTime.now()
            )
        )
        given(postService.getPosts()).willReturn(posts)

        // expected
        mockMvc.perform(get("/api/posts"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].title").value("테스트 제목1"))
            .andExpect(jsonPath("$[0].author").value("작성자1"))
            .andExpect(jsonPath("$[0].createdAt").exists())
    }

    @Test
    fun `게시글 수정 API 테스트`() {
        // given
        val postId = 1L
        val updatedTime = LocalDateTime.now()
        val request = PostUpdateRequest(title = "수정 제목", content = "수정 내용", password = "Abcdefg1!@#")
        val response = PostUpdateResponse(
            id = postId,
            updatedTitle = "수정 제목",
            updatedContent = "수정 내용",
            updatedAt = updatedTime
        )
        given(postService.updatePost(postId, request)).willReturn(response)
        val jsonRequest = objectMapper.writeValueAsString(request)

        // expected
        mockMvc.perform(
            put("/api/posts/$postId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(postId))
            .andExpect(jsonPath("$.updatedTitle").value("수정 제목"))
            .andExpect(jsonPath("$.updatedContent").value("수정 내용"))
    }

    @Test
    fun `게시글 삭제 API 테스트`() {
        // given
        val postId = 1L
        val password = "Abcdefg1!@#"
        // 삭제 API는 리턴값이 없으므로, 단순 호출 검증만 진행

        // when
        mockMvc.perform(
            delete("/api/posts/{id}", postId)
                .contentType(MediaType.APPLICATION_JSON)
                // 문자열 타입의 단순 값은 JSON 형태로 보내기 위해 따옴표 포함
                .content(password)
        )
            .andExpect(status().isOk)

        // then: 서비스의 deletePost 호출 여부 확인
        verify(postService).deletePost(postId, password)
    }
}