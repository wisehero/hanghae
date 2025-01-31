package com.wisehero.boardapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BoardAppApplication

fun main(args: Array<String>) {
    runApplication<BoardAppApplication>(*args)
}
