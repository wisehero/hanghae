package week2.condition

fun main() {
    val todayNumber = readLine()!!.toInt()
    when (todayNumber) {
        1 -> {
            println("재물이 들어올 것입니다.")
        }

        2 -> {
            println("검정색을 조심하세요.")
        }

        3 -> {
            println("지인과의 관계에 조심하세요.")
        }

        4 -> {
            println("물을 조심하십시오...")
        }
    }
}