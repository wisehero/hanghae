package com.wisehero.boardapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class BoardAppApplication

fun main(args: Array<String>) {
    runApplication<BoardAppApplication>(*args)
}
