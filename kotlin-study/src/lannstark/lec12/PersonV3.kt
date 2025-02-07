package lannstark.lec12

fun main() {
    println(Singleton.printName())
}

class PersonV3 private constructor(
    var name: String,
    var age: Int
) {

    companion object Factory : Log {
        private const val MIN_AGE = 1 // const 붙으면 진짜 상수

        @JvmStatic
        fun newBaby(name: String): PersonV3 {
            return PersonV3(name, MIN_AGE)
        }

        override fun log() {
            println("나는 Person의 companion object Factory야")
        }
    }
}

object Singleton {
    init {
        println("Singleton class invoked")
    }

    var name = "홍길동"
    fun printName() {
        println(name)
    }
}