package com.wisehero.boardapp.api.auth

import com.wisehero.boardapp.api.auth.request.LoginRequest
import com.wisehero.boardapp.api.auth.request.LoginUserInfo
import com.wisehero.boardapp.domain.auth.LoginToken
import com.wisehero.boardapp.domain.auth.LoginUser
import com.wisehero.boardapp.domain.auth.RefreshTokenRepository
import com.wisehero.boardapp.domain.auth.TokenProvider
import com.wisehero.boardapp.domain.user.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenProvider: TokenProvider
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginToken {
        val user = userRepository.findByEmail(request.email) ?: throw IllegalArgumentException("존재하지 않는 계정입니다.")
        require(bCryptPasswordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }

        if (refreshTokenRepository.existsByUserId(user.id!!)) {
            refreshTokenRepository.deleteByUserId(user.id!!)
        }
        return tokenProvider.getToken(user.id!!, user.role.name, Date())
    }
}