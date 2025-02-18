package com.wisehero.myselectshop.api.response

data class Item(
    val title: String,
    val link: String,
    val image: String,
    val lprice: Int
){
    constructor(itemJson: JSONObject) : this(
        title = itemJson.getString("title"),
        link = itemJson.getString("link"),
        image = itemJson.getString("image"),
        lprice = itemJson.getInt("lprice")
    )

    constructor() : this("", "", "", 0)
}
