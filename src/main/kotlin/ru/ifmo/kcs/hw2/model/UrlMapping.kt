package ru.ifmo.kcs.hw2.model

import javax.persistence.*

@Entity
@Table(name = "url_mapping")
data class UrlMapping(
        @Column(name = "short_url")
        var shortUrl: String? = null,

        @Column(name = "original_url")
        var originalUrl: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String? = null
}