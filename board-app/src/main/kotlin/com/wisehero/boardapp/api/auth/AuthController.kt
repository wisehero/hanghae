package com.wisehero.boardapp.api.auth

import com.wisehero.boardapp.api.auth.request.LoginRequest
import com.wisehero.boardapp.api.auth.request.LoginUserInfo
import com.wisehero.boardapp.domain.auth.*
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
    private val authService: AuthService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginToken {
        val encodedPassword = bCryptPasswordEncoder.encode(request.password)
        return authService.authenticate(request.email, encodedPassword)
    }
}