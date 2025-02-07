package lannstark.lec14.enumclass

import kotlin.reflect.typeOf

fun main() {
    val country = Country.KOREA
    val countryName = Country.KOREA.toString()
    println(country)

}

fun handleCountry(country: Country) {
    when (country) {
        Country.KOREA -> println("한국")
        Country.AMERICA -> println("미국")
    }
}

enum class Country(
    private val code: String
) {
    KOREA("KO"),
    AMERICA("US");
}