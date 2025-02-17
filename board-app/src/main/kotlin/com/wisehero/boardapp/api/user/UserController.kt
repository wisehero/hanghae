package com.wisehero.boardapp.api.user

import com.wisehero.boardapp.api.user.request.UserCreateRequest
import com.wisehero.boardapp.domain.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun signUp(@RequestBody request: UserCreateRequest) {
        userService.createUser(request)
    }
}