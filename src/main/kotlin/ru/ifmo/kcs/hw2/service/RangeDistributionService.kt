package ru.ifmo.kcs.hw2.service

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong
import org.apache.curator.framework.recipes.shared.SharedCountListener
import org.apache.curator.framework.recipes.shared.SharedCountReader
import org.apache.curator.framework.state.ConnectionState
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.retry.RetryNTimes
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ru.ifmo.kcs.hw2.model.Range
import javax.annotation.PostConstruct

@Service
class RangeDistributionService : SharedCountListener {
    private val zkServer = "localhost:2181"
    private val counterPath = "/KekURL/counter"
    private val rangeSize = 1000

    private lateinit var client: CuratorFramework
    private lateinit var count: DistributedAtomicLong

    @PostConstruct
    private fun postConstruct() {
        client = CuratorFrameworkFactory.newClient(zkServer,
                ExponentialBackoffRetry(1000,3))
        client.start()
        count = DistributedAtomicLong(client, counterPath, RetryNTimes(10, 10))
    }

    @Bean("range")
    fun getNewRange(): Range? {
        try {
            val value = count.increment()
            if (value.succeeded()) {
                return Range(value.preValue() * rangeSize, (value.preValue() + 1) * rangeSize - 1)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun stateChanged(p0: CuratorFramework?, p1: ConnectionState?) {
        // Nothing
    }

    override fun countHasChanged(p0: SharedCountReader?, p1: Int) {
        // Nothing
    }
}