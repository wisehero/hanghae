package lannstark.lec16

fun main() {

    var str = "ABC"
    println(str.lastChar())

}

// 확장 함수
// 확장 함수는 private, protected 멤버에 접근할 수 없다.
// 확장 함수의 시그니처는 원본 객체의 시그니처가 동일하면 원본 객체의 시그니처가 먼저 호출된다.
fun String.lastChar(): Char {
    return this[this.length - 1]
}