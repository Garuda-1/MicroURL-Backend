package ru.ifmo.kcs.hw2.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.ifmo.kcs.hw2.model.Range

@Service
class UrlShorteningService {
    private val charCount = 62
    private val arr: Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9')
    private val shortLinkSize = 8

    @Autowired
    private var range: Range? = null

    @Autowired
    private lateinit var rds: RangeDistributionService

    @Value("#{range.from}")
    private var current: Long = -1

    fun getNewRange() {
        range = rds.getNewRange()
        current = range!!.from
    }

    fun shortenUrl(url: String): String {
        if (current > range!!.to) {
            getNewRange()
        }
        var count = current
        var short = ""
        while (count > 0) {
            short += arr[(count % charCount).toInt()]
            count /= charCount
        }
        while (short.length < shortLinkSize) {
            short += arr[0]
        }

        current++
        return short.reversed()
    }

}