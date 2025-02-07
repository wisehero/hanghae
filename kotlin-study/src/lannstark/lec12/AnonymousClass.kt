package lannstark.lec12

fun main() {

    moveSomething(object : Movable {
        override fun move() {
            println("무브 무브")
        }

        override fun fly() {
            println("플라이 플라이")
        }
    })

}

private fun moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}