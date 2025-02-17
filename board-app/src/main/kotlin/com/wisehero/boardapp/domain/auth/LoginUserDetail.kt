package com.wisehero.boardapp.domain.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoginUserDetail(
    val email: String,
    val role: String?
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (role == null) {
            return mutableListOf()
        }

        return mutableListOf()
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}