package ru.ifmo.kcs.hw2.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.kcs.hw2.model.UrlMapping
import ru.ifmo.kcs.hw2.repository.UrlMappingRepository

@Service
class UrlMappingService {
    @Autowired
    private lateinit var urlMappingRepo: UrlMappingRepository

    fun getByShortUrl(shortUrl: String): UrlMapping? {
        return urlMappingRepo.findById(shortUrl).orElse(null)
    }

    fun saveNewMapping(urlMapping: UrlMapping) {
        urlMappingRepo.save(urlMapping)
    }
}