package com.wisehero.boardapp.domain.auth

import com.wisehero.boardapp.domain.user.Role
import com.wisehero.boardapp.domain.user.User
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Enumerated(EnumType.STRING)
    val role: Role,
    @Column(nullable = false)
    val token: String,
    @Column(nullable = false)
    val expireAt: LocalDateTime,
    @OneToOne(fetch = FetchType.LAZY)
    val user: User,
    @Column(nullable = false)
    val accessToken: String,
    @Column(nullable = false)
    val accessTokenExpireAt: LocalDateTime
) {

}