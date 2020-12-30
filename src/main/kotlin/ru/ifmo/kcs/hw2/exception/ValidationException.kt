package ru.ifmo.kcs.hw2.exception

import java.lang.RuntimeException

class ValidationException(message: String?) : RuntimeException(message)