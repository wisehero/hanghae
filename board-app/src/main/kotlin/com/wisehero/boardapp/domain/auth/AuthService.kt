package com.wisehero.boardapp.domain.auth

import com.wisehero.boardapp.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,

    private val tokenProvider: TokenProvider
) {

    @Transactional
    fun authenticate(email: String, encryptedPassword: String): LoginToken {
        val user = userRepository.findByEmail(email) ?: throw IllegalArgumentException("존재하지 않는 계정입니다.")
        require(user.password != encryptedPassword) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }

        if (refreshTokenRepository.existsByUserId(user.id!!)) {
            refreshTokenRepository.deleteByUserId(user.id!!)
        }
        return tokenProvider.getToken(user.id!!, user.role.name, Date())
    }
}