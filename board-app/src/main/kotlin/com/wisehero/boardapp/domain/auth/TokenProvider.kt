package com.wisehero.boardapp.domain.auth

import com.wisehero.boardapp.domain.user.Role
import com.wisehero.boardapp.domain.user.UserRepository
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class TokenProvider(
    private val userRepository: UserRepository,
    @Value("\${jwt.secret-key}")
    private val secretKey: String,
    @Value("\${jwt.access-token-expire-time}")
    private val ACCESS_TOKEN_EXPIRE_TIME: String,
    @Value("\${jwt.refresh-token-expire-time}")
    private val REFRESH_TOKEN_EXPIRE_TIME: String,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    val key: Key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    /**
     * accessToken, RefreshToken 생성
     */
    fun getToken(id: Long, role: String, now: Date): LoginToken {
        val now: LocalDateTime = LocalDateTime.now()

        val accessTokenTime: LocalDateTime = now.plus(ACCESS_TOKEN_EXPIRE_TIME.toLong(), ChronoUnit.SECONDS)
        val refreshTokenTime: LocalDateTime = now.plus(REFRESH_TOKEN_EXPIRE_TIME.toLong(), ChronoUnit.SECONDS)

        // accessToken 생성
        val accessToken = createToken(
            id = id,
            tokenExpireTime = accessTokenTime.toInstant(ZoneOffset.UTC).toEpochMilli(),
            role = role
        )
        val loginUserRole = Role.valueOf(role)
        var loginToken: LoginToken? = refreshTokenRepository.findByUserId(id)?.let {
            if (now.isAfter(it.expireAt)) {
                throw RuntimeException("리프레시 토큰 만료")
            }
            val user = userRepository.findByIdOrNull(it.id) ?: throw RuntimeException("사용자 정보 없음")

            LoginToken(
                accessToken = accessToken,
                refreshToken = it.token,
                accessTokenExpiredAt = accessTokenTime,
                refreshTokenExpiredAt = it.expireAt,
                id = user.id!!
            )
        }

        if (loginToken == null) {
            val refreshToken = createToken(
                id = id,
                tokenExpireTime = refreshTokenTime.toInstant(ZoneOffset.UTC).toEpochMilli(),
                role = role
            )
            val user = userRepository.findByIdOrNull(id) ?: throw RuntimeException("사용자 정보 없음")
            val savedRefreshToken = refreshTokenRepository.save(
                RefreshToken(
                    user = user,
                    token = refreshToken,
                    expireAt = refreshTokenTime,
                    role = loginUserRole,
                    accessToken = accessToken,
                    accessTokenExpireAt = accessTokenTime
                )
            )
            loginToken = LoginToken(
                accessToken = accessToken,
                refreshToken = refreshToken,
                accessTokenExpiredAt = accessTokenTime,
                refreshTokenExpiredAt = refreshTokenTime,
                id = user.id!!
            )
        }

        return loginToken
    }

    fun getAuthentication(token: String): Authentication {
        val user = userRepository.findByIdOrNull(getUser(token)) ?: throw RuntimeException("사용자 정보 없음")
        val loginUserDetail = LoginUserDetail(user.email, user.role.name)
        return UsernamePasswordAuthenticationToken(loginUserDetail, null, loginUserDetail.authorities)
    }

    // 토큰에 담긴 사용자 정보 추출
    fun getUser(token: String): Long {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
            .parseClaimsJws(token).body.subject.toLong()
    }

    // 토큰 검증
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
            return true;
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: ExpiredJwtException) {
            return false
        } catch (e: UnsupportedJwtException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return false
    }

    // 헤더에 담긴 토큰 추출
    fun resolveToken(request: HttpServletRequest?): String? {
        return request?.getHeader("Authorization")
    }

    // 토큰 생성
    private fun createToken(id: Long, tokenExpireTime: Long, role: String): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .claim("auth", role)
            .setExpiration(Date(tokenExpireTime))
            .signWith(key, io.jsonwebtoken.SignatureAlgorithm.HS512)
            .compact()
    }
}