package ru.ifmo.kcs.hw2.controller.handler

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import ru.ifmo.kcs.hw2.exception.ValidationException

@ControllerAdvice
class RestControllerExceptionHandler {
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(e: Exception): ResponseEntity<Any> {
        return ResponseEntity<Any>(e.message, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException) {
        // Nothing
    }
}