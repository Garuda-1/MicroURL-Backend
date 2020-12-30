package ru.ifmo.kcs.hw2.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.ifmo.kcs.hw2.model.UrlMapping
import ru.ifmo.kcs.hw2.service.UrlMappingService
import ru.ifmo.kcs.hw2.service.UrlShorteningService
import javax.servlet.http.HttpServletResponse

@RestController
class ShorteningController {
    class ShorteningDto(val originalUrl: String)

    @Autowired
    private lateinit var urlShorteningService: UrlShorteningService

    @Autowired
    private lateinit var urlMappingService: UrlMappingService

    @PostMapping(value=["/url/generate"], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun generateLink(@RequestBody requestBody: ShorteningDto): UrlMapping {
        val originalUrl = requestBody.originalUrl
        val shortUrl = urlShorteningService.shortenUrl(originalUrl)
        val urlMapping = UrlMapping(shortUrl, originalUrl)
        urlMappingService.saveNewMapping(urlMapping)
        return urlMapping
    }

    @GetMapping(value=["/urls/{short}"])
    fun getUrlMapping(@PathVariable(value="short") shortUrl: String, response: HttpServletResponse) {
        val urlMapping = urlMappingService.getByShortUrl(shortUrl)
        if (urlMapping == null) {
            response.sendError(400)
        } else {
            response.sendRedirect(urlMapping.originalUrl)
        }
    }
}
