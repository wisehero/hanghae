package hanghae_course.week3

fun main() {

    var num1 = readLine()!!.toInt()
    var num2 = readLine()!!.toInt()

    sum(num1, num2)

    display()

    var score = readLine()!!.toInt()
    var rank = checkRank(score)
    println("당신의 등급은 ${rank}입니다.")
}

fun sum(num1: Int, num2: Int) {
    var result = num1 + num2
    println("두 수의 합은 ${result}입니다.")
}

fun display(): Unit {
    println("오늘의 날씨는 화창합니다.")
    println("오늘은 검정색을 조심하세요.")
}

fun checkRank(score: Int): String {
    return when (score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}