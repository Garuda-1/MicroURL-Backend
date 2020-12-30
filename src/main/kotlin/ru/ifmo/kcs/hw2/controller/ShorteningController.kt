package ru.ifmo.kcs.hw2.controller

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import ru.ifmo.kcs.hw2.exception.ValidationException
import ru.ifmo.kcs.hw2.model.UrlMapping
import ru.ifmo.kcs.hw2.service.UrlMappingService
import ru.ifmo.kcs.hw2.service.UrlShorteningService
import ru.ifmo.kcs.hw2.utils.BindingResultUtils
import ru.ifmo.kcs.hw2.validator.UrlValidator
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/url")
class ShorteningController(
        val urlValidator: UrlValidator
) {
    @Validated
    class ShorteningDto(@JsonProperty("originalUrl") var originalUrl: String)

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(urlValidator)
    }

    @Autowired
    private lateinit var urlShorteningService: UrlShorteningService

    @Autowired
    private lateinit var urlMappingService: UrlMappingService

    @PostMapping("generate")
    fun generateLink(@RequestBody @Valid requestBody: ShorteningDto, bindingResult: BindingResult): UrlMapping {
        if (bindingResult.hasErrors()) {
            throw ValidationException(BindingResultUtils.getErrorMessage(bindingResult))
        }

        val originalUrl = requestBody.originalUrl
        val shortUrl = urlShorteningService.shortenUrl(originalUrl)
        val urlMapping = UrlMapping(shortUrl, originalUrl)
        urlMappingService.saveNewMapping(urlMapping)
        return UrlMapping(shortUrl, originalUrl)
    }

    @GetMapping("short/{short}")
    fun getUrlMapping(@PathVariable(value="short") shortUrl: String, response: HttpServletResponse) {
        val urlMapping = urlMappingService.getByShortUrl(shortUrl)
        if (urlMapping == null) {
            response.sendError(400)
        } else {
            response.sendRedirect(urlMapping.originalUrl)
        }
    }
}
