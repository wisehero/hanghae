package com.wisehero.boardapp.domain.auth

import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {

    fun existsByUserId(userId: Long): Boolean

    fun findByUserId(userId: Long): RefreshToken?

    fun deleteByUserId(usertId: Long)

    fun findByAccessToken(accessToken: String): RefreshToken?


}