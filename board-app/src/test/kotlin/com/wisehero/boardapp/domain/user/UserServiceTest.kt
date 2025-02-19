package com.wisehero.boardapp.domain.user

import com.wisehero.boardapp.api.user.request.UserCreateRequest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
internal class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository


    @Test
    @Transactional
    fun `회원 가입 테스트`() {
        // given
        val request = UserCreateRequest(
            email = "test@gmail.com",
            password = "pasSword1!",
            role = Role.USER
        )

        // when
        val createdUser = userService.createUser(request)

        // then
        assertThat(createdUser.email).isEqualTo("test@gmail.com")
        assertThat(createdUser.role).isEqualTo(Role.USER)
    }

    @Test
    @Transactional
    fun `회원 가입시 메일이 중복된 회원이 있으면 가입에 실패한다`() {
        // given
        val request = UserCreateRequest(
            email = "test@gmail.com",
            password = "pasSword1!",
            role = Role.USER
        )

        userService.createUser(request)

        // when then
        assertThatThrownBy { userService.createUser(request) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이미 존재하는 회원입니다.")
    }
}