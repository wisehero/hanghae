package com.wisehero.myselectshop.api

import com.wisehero.myselectshop.api.response.Item
import com.wisehero.myselectshop.naver.NaverApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/naver")
class NaverApiController(
    private val naverApiService: NaverApiService
) {

    @GetMapping("/search")
    fun searchItems(@RequestParam query: String): List<Item> {
        return naverApiService.searchItems(query);
    }
}