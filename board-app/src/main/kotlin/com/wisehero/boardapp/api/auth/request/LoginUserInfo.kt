package com.wisehero.boardapp.api.auth.request

import com.wisehero.boardapp.domain.user.Role

data class LoginUserInfo(
    val id: Long,
    val role: Role
)
