package com.wisehero.boardapp.api.user.response

import com.wisehero.boardapp.domain.user.Role

data class UserCreateResponse(
    val id: Long,
    val email: String,
    val role: Role
)
