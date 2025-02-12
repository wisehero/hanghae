package kotlin_in_action.chap02

// 코틀린의 기본 가시성은 public이므로 자바와는 다르게 생략이 가능하다.
// val로 선언한 키워드는 읽기 전용이며 getter만 제공된다.
// 반면 var로 선언한 키워드는 setter와 getter가 모두 제공된다.

fun main() {
    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarriged)

    person.isMarriged = false
    println(person.isMarriged)
}

class Person(
    val name: String,
    var isMarriged: Boolean
) {
}
