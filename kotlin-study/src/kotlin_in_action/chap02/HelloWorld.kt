package kotlin_in_action.chap02

// 코틀린은 함수를 최상위 수준에 정의할 수 있다. 자바와 달리 꼭 클래스 안에 함수를 넣어야 할 필요가 없다.
fun main() {
    println("Hello, world!")

    println(max(1, 2))
    println(max(3, 4))
}


fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// 코틀린에서 if문은 식이다. 따라서 아래와 같이 작성할 수 있다.
fun maxV2(a: Int, b: Int): Int = if (a > b) a else b



