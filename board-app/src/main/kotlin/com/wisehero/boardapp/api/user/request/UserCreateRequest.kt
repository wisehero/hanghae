package com.wisehero.boardapp.api.user.request

import com.wisehero.boardapp.domain.user.Role
import com.wisehero.boardapp.domain.user.User

data class UserCreateRequest(
    val email: String,
    val password: String,
    val role: Role
) {
    fun toEntity(): User {
        return User(
            email = email,
            password = password,
            role = role
        )
    }
}
