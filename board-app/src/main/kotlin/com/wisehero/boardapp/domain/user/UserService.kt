package com.wisehero.boardapp.domain.user

import com.wisehero.boardapp.api.user.request.UserCreateRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun createUser(request: UserCreateRequest) {
        userRepository.findByEmail(request.email)?.let {
            throw IllegalArgumentException("이미 존재하는 회원입니다.")
        }

        val hashedPassword = passwordEncoder.encode(request.password)
        val user = User(
            email = request.email,
            password = hashedPassword,
            role = Role.USER
        )

        userRepository.save(user)
    }
}