package ru.ifmo.kcs.hw2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/help")
class HelpController {
    @GetMapping("/pls")
    fun help(): String {
        return "Help"
    }
}