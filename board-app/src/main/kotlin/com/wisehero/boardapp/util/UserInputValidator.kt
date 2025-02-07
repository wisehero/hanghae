package com.wisehero.boardapp.util

fun isValidPassword(password: String): Boolean {
    val pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,20}$".toRegex()
    return pattern.matches(password)
}