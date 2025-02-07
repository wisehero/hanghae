package lannstark.lec15

fun main() {
    val array = arrayOf(1, 2, 3, 4, 5)

    for (i in array.indices) {
        println("${i} : ${array[i]}")
    }

    println()

    for ((index, value) in array.withIndex()) {
        println("${index} : ${value}")
    }

    println()

    array.plus(6)
    for (i in array.indices) {
        println("${i} : ${array[i]}")
    }
}