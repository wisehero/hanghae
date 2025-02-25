package com.wisehero.boardapp.security

import com.wisehero.boardapp.domain.auth.JwtAccessDeniedHandler
import com.wisehero.boardapp.domain.auth.LoginFilter
import com.wisehero.boardapp.domain.auth.RefreshTokenRepository
import com.wisehero.boardapp.domain.auth.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtAccessDeniedHander: JwtAccessDeniedHandler,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity, corsConfigurationSource: CorsConfigurationSource): SecurityFilterChain {
        http.csrf { it.disable() }
        http.httpBasic { it.disable() }
        http.formLogin { it.disable() }
        // cors
        http.cors { it.configurationSource(corsConfigurationSource) }
        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        http.addFilterBefore(
            LoginFilter(tokenProvider, refreshTokenRepository),
            UsernamePasswordAuthenticationFilter::class.java
        )

        http.exceptionHandling { it.accessDeniedHandler(jwtAccessDeniedHander) }
        http.authorizeHttpRequests {
            it.requestMatchers("/api/auth/**").permitAll()
            it.anyRequest().permitAll()
        }

        return http.build();
    }

    @Bean
    fun bcryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}