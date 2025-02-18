package com.wisehero.myselectshop.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Item(
    @JsonProperty("title")
    val title: String = "",
    @JsonProperty("link")
    val link: String = "",
    @JsonProperty("image")
    val image: String = "",
    @JsonProperty("lprice")
    val lprice: Int = 0
)