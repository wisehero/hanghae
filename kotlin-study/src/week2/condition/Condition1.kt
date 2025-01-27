package week2.condition

fun main() {
    val number = 10

    if (number > 0) {
        println("$number 는 양수입니다.")
    } else if (number < 0) {
        println("$number 는 음수입니다.")
    } else {
        println("$number 는 0입니다.")
    }

    val day = 3
    val dayString = when (day) {
        1 -> "월요일"
        2 -> "화요일"
        3 -> "수요일"
        4 -> "목요일"
        5 -> "금요일"
        6 -> "토요일"
        7 -> "일요일"
        else -> "알 수 없는 요일"
    }

    println("오늘은 ${dayString}입니다.")

}