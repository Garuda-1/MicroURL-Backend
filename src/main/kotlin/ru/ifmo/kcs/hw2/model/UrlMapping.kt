package ru.ifmo.kcs.hw2.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "ProductInfo")
data class UrlMapping(
        @DynamoDBHashKey(attributeName = "short_url")
        var shortUrl: String? = null,

        @DynamoDBAttribute(attributeName = "original_url")
        var originalUrl: String? = null
)