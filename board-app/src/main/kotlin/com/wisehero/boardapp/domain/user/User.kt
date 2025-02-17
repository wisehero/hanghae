package com.wisehero.boardapp.domain.user

import com.wisehero.boardapp.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String,
    val password: String,
    val role: Role
) : BaseTimeEntity() {


}