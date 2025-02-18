package com.wisehero.myselectshop.naver

import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.wisehero.myselectshop.api.response.Item
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class NaverApiService {

    private val naverClientId = "EhMWCXmbPc7xQvSiG3rv"
    private val naverClientSecret = "GRteUnE4BP"
    private val log = LoggerFactory.getLogger(this::class.java)

    private val client = RestClient.builder()
        .baseUrl("https://openapi.naver.com")
        .defaultHeader("X-Naver-Client-Id", naverClientId)
        .defaultHeader(
            "X-Naver-Client-Secret", naverClientSecret
        )
        .build()

    fun searchItems(query: String): List<Item> {
        val response = client.get()
            .uri("/v1/search/shop.json") {
                it.queryParam("display", "15")
                    .queryParam("query", query)
                    .build()
            }
            .retrieve()
            .onStatus({ status -> status.is2xxSuccessful }) { request, response ->
                log.info("NAVER API Status Code: ${response.statusCode.value()}")
            }
            .body(String::class.java)

        return fromJsonToItems(response)
    }

    fun fromJsonToItems(response: String?): List<Item> {
        val mapper = jacksonObjectMapper()
        val rootNode = mapper.readTree(response)
        val itemsNode = rootNode.get("items")
        val itemsArrayNode = itemsNode as ArrayNode

        return mapper.readValue(itemsNode.toString())
    }
}