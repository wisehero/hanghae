package lannstark.lec09


fun main() {
    val person = Person("김지웅", 29)
    println(person.isAudult)
    println()

    val person2 = Person("김지웅")
    println(person2.isAudult)
    println()

    val person3 = Person()
    println(person3.isAudult)
    println()
}

class Person(
    val name: String = "김지웅",
    var age: Int
) {

    // 객체가 초기화 될 때 아래의 블록이 실행된다. 이곳에 검증 로직을 넣을 수 있다.
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 0보다 작을 수 없습니다.")
        }
        println("이름은 ${name}이고, 나이는 ${age}세 입니다.")
    }

    constructor(name: String) : this(name, 1) {
        println("1번째 부 생성자")
    }

    constructor() : this("김지웅") {
        println("2번째 부 생성자")
    }

    // 커스텀 Getter를 함수처럼 만들거나, Property로 작성 가능
    val isAudult: Boolean
        get() = this.age >= 20

    val upperCaseName: String
        get() = this.name.uppercase()
}