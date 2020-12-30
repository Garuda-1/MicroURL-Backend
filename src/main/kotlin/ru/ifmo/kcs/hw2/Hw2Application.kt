package ru.ifmo.kcs.hw2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [
"ru.ifmo.kcs.hw2.config",
"ru.ifmo.kcs.hw2.controller",
"ru.ifmo.kcs.hw2.model",
"ru.ifmo.kcs.hw2.repository",
"ru.ifmo.kcs.hw2.service"])
class Hw2Application

fun main(args: Array<String>) {
    runApplication<Hw2Application>(*args)
}
