package com.wisehero.boardapp.api.auth.request

import com.wisehero.boardapp.domain.user.Role
import com.wisehero.boardapp.domain.user.User

data class LoginRequest(
    val email: String,
    val password: String
) {
}
