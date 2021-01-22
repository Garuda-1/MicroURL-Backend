package ru.ifmo.kcs.hw2.validator

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import ru.ifmo.kcs.hw2.controller.ShorteningController
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

@Component
class UrlValidator : Validator {
    override fun supports(clazz: Class<*>) = ShorteningController.ShorteningDto::class.java.isAssignableFrom(clazz)

    override fun validate(obj: Any, errors: Errors) {
        val urlMapping = obj as ShorteningController.ShorteningDto
        try {
            val url = URL(urlMapping.originalUrl)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "HEAD"
            if (httpURLConnection.responseCode in 400..404) {
                errors.reject("originalUrl.nonSuccessfulRc", "URL is not unreachable")
            }
        } catch (e: MalformedURLException) {
            errors.reject("originalUrl.malformedUrl", "Malformed URL")
        } catch (e: IOException) {
            errors.reject("originalUrl.IOerror", "IO Error")
        } catch (e: Exception) {
            errors.reject("other", e.message ?: "Unknown error")
        }
    }
}