package lannstark.lec20

/**
 * scope function은 일시적인 영역을 형성하는 함수
 * let, run, with(이건 확장함수가 아님), apply, also
 *
 * let, run -> 결과값을 반환
 * with, apply, also -> 원본 객체를 반환
 *
 * let, also -> it을 사용한다.
 * run, apply -> this를 사용한다.
 */

fun main() {

}


fun printPerson(person: Person?) {
    person?.let {
        println(person.name)
        println(person.age)
    }
    // 위의 코드는 아래의 코드와 같다.
    // if (person != null) {
    //     println(person.name)
    //     println(person.age)
    // }
}

data class Person(
    val name: String,
    val age: Int
)