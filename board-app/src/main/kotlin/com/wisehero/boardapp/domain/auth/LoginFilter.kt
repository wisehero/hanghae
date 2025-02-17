package com.wisehero.boardapp.domain.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.time.LocalDateTime

class LoginFilter(
    private val tokenProvider: TokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        tokenProvider.resolveToken(request = request as? HttpServletRequest?)?.also {
            val loginToken = it.split(" ")[1]
            val findToken = refreshTokenRepository.findByAccessToken(loginToken)

            if (findToken == null || LocalDateTime.now().isAfter(findToken.accessTokenExpireAt) || !it.toLowerCase()
                    .startsWith("bearer ")
                || !tokenProvider.validateToken(loginToken)
            ) {
                response.sendError(401)
                return
            }
            val authentication = tokenProvider.getAuthentication(loginToken)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}