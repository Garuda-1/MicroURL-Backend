package ru.ifmo.kcs.hw2.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.ifmo.kcs.hw2.model.Range

@Service
class UrlShorteningService {
    private val arrCons: Array<Char> = arrayOf('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z',
            'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9')
    private val arrVow: Array<Char> = arrayOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    private val shortLinkSize = 6

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
        var cons = true
        while (count > 0) {
            if (cons) {
                short += arrCons[(count % arrCons.size).toInt()]
                count /= arrCons.size
            } else {
                short += arrVow[(count % arrVow.size).toInt()]
                count /= arrVow.size
            }
            cons = !cons
        }
        while (short.length < shortLinkSize) {
            short += '0'
        }

        current++
        return short.reversed()
    }

}