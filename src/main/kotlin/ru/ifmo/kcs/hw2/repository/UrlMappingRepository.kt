package ru.ifmo.kcs.hw2.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ifmo.kcs.hw2.model.UrlMapping

interface UrlMappingRepository: JpaRepository<UrlMapping, Long> {
    fun findByShortUrl(shortUrl: String): UrlMapping?
}
