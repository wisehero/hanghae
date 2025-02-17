package com.wisehero.boardapp.domain.auth

import com.wisehero.boardapp.api.auth.request.LoginUserInfo
import com.wisehero.boardapp.domain.user.Role
import com.wisehero.boardapp.domain.user.UserRepository
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class LoginInterception(
    private val userRepository: UserRepository
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasMethodAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val authentication = SecurityContextHolder.getContext().authentication
        val role = authentication.authorities.first().toString().split("_")
        var authenRole =
            if (role.size == 2) {
                role[1]
            } else {
                "${role[1]}_${role[2]}"
            }

        val user = userRepository.findByEmailAndRole(authentication.name, Role.valueOf(authenRole))
            ?: throw RuntimeException("사용자 정보 없음")

        return LoginUserInfo(user.id!!, user.role)
    }
}