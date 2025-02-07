package lannstark.lec12

fun main() {

}

class Person private constructor(
    var name: String,
    var age: Int
) {

    companion object {
        private const val MIN_AGE = 1 // const 붙으면 진짜 상수
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }
    }
}