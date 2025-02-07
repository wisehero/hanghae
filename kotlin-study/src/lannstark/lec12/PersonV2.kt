package lannstark.lec12

fun main() {

}

class PersonV2 private constructor(
    var name: String,
    var age: Int
) {

    companion object Factory : Log {
        private const val MIN_AGE = 1 // const 붙으면 진짜 상수
        fun newBaby(name: String): PersonV2 {
            return PersonV2(name, MIN_AGE)
        }

        override fun log() {
            println("나는 Person의 companion object Factory야")
        }
    }
}