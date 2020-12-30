package ru.ifmo.kcs.hw2.utils

import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

class BindingResultUtils {
    companion object {
        fun getErrorMessage(bindingResult: BindingResult): String? {
            return if (bindingResult.hasErrors()) {
                val objectError = bindingResult.allErrors[0]
                if (objectError is FieldError) {
                    "Field " + objectError.field + ": " + objectError.defaultMessage
                } else {
                    objectError.defaultMessage
                }
            } else {
                null
            }
        }
    }
}
