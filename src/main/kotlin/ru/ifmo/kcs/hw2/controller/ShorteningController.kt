package ru.ifmo.kcs.hw2.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.ifmo.kcs.hw2.model.UrlMapping
import ru.ifmo.kcs.hw2.service.UrlMappingService
import ru.ifmo.kcs.hw2.service.UrlShorteningService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class ShorteningController {
    @Autowired
    private lateinit var urlShorteningService: UrlShorteningService

    @Autowired
    private lateinit var urlMappingService: UrlMappingService

    @PostMapping(value=["/url/generate"], produces=[MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun generateLink(@RequestBody requestBody: Map<String, String>, request: HttpServletRequest, response: HttpServletResponse): UrlMapping {
        val originalUrl = requestBody["src"] ?: error("Provide URL to shorten in 'src'")
        val shortUrl = urlShorteningService.shortenUrl(originalUrl)
        val urlMapping = UrlMapping(shortUrl, originalUrl)
        urlMappingService.saveNewMapping(urlMapping)
        return urlMapping
    }
}