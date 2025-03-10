package com.wisehero.boardapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun findByEmailAndRole(email: String, role: Role): User?
}