package ru.ifmo.kcs.hw2.model

import javax.persistence.*

//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

//@DynamoDBTable(tableName = "ProductInfo")
@Entity
@Table(name = "url_mapping")
data class UrlMapping(
//        @DynamoDBHashKey(attributeName = "short_url")
        @Column(name = "short_url")
        var shortUrl: String? = null,

//        @DynamoDBAttribute(attributeName = "original_url")
        @Column(name = "original_url")
        var originalUrl: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String? = null
}