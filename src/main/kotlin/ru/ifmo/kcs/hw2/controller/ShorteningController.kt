package ru.ifmo.kcs.hw2.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.kcs.hw2.model.UrlMapping
import ru.ifmo.kcs.hw2.service.UrlMappingService
import ru.ifmo.kcs.hw2.service.UrlShorteningService
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/url")
class ShorteningController {
    class ShorteningDto(val originalUrl: String)

    @Autowired
    private lateinit var urlShorteningService: UrlShorteningService

    @Autowired
    private lateinit var urlMappingService: UrlMappingService

    @PostMapping("/generate")
    fun generateLink(@RequestBody requestBody: String): UrlMapping {
        val originalUrl = requestBody
        val shortUrl = urlShorteningService.shortenUrl(originalUrl)
        val urlMapping = UrlMapping(shortUrl, originalUrl)
        urlMappingService.saveNewMapping(urlMapping)
        return UrlMapping(shortUrl, originalUrl)
    }

    @GetMapping("/short/{short}")
    fun getUrlMapping(@PathVariable(value="short") shortUrl: String, response: HttpServletResponse) {
        val urlMapping = urlMappingService.getByShortUrl(shortUrl)
        if (urlMapping == null) {
            response.sendError(400)
        } else {
            response.sendRedirect(urlMapping.originalUrl)
        }
    }
}
