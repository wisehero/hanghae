package lannstark.lec15

fun main() {

    val numbers = listOf(1, 2, 3, 4, 5) // 불변

    println(numbers[0])

    for (number in numbers) {
        println(number)
    }

    println()

    for ((index, value) in numbers.withIndex()) {
        println("${index} : ${value}")
    }

    println()

    val mutableNumbers = mutableListOf(1, 2, 3, 4, 5) // 가변
    println(mutableNumbers)
    mutableNumbers.add(6)
    println(mutableNumbers)

    println()

    val oldMap = mutableMapOf<Int, String>()
    oldMap[1] = "김지웅"
    oldMap[2] = "홍길동"
    println(oldMap)
    println(oldMap.keys)

}