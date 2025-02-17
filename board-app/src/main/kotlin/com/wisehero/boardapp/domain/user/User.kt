package com.wisehero.boardapp.domain.user

import com.wisehero.boardapp.domain.BaseTimeEntity
import jakarta.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: Role
) : BaseTimeEntity() {


}