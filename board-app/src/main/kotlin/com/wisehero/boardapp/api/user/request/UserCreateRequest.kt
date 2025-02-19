package com.wisehero.boardapp.api.user.request

import com.wisehero.boardapp.domain.user.Role
import com.wisehero.boardapp.domain.user.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class UserCreateRequest(
    @Email(message = "이메일 형식이 아닙니다.")
    val email: String,
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9]{8,15}\$", message = "비밀번호는 영문, 숫자를 포함한 8~15자여야 합니다.")
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
