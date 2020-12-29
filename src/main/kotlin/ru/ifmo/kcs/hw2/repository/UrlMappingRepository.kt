package ru.ifmo.kcs.hw2.repository

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import ru.ifmo.kcs.hw2.model.UrlMapping

@EnableScan
interface UrlMappingRepository: CrudRepository<UrlMapping, String>
