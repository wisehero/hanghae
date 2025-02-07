package lannstark.lec14.dataclass


fun main() {
    val dto1 = PersonDto("김지웅", 100)
    val dto2 = PersonDto("김지웅", 100)

    println(dto1) // toString
    println(dto2) // toString

    println(dto1 == dto2) // equals
    println(dto1 === dto2)
}

data class PersonDto(
    val name: String,
    val age: Int
)
