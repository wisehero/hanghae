package com.wisehero.boardapp.domain.auth

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class LoginToken(
    val id: Long,
    val grantType: String = "Bearer",
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val accessTokenExpiredAt: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val refreshTokenExpiredAt: LocalDateTime
) {
}