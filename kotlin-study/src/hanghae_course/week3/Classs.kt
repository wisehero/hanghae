package hanghae_course.week3


fun main() {
    var magicianOne = Character4("불마법사", "red", 180.2)
    println("${magicianOne.name}의 머리색상은 ${magicianOne.hairColor}입니다")
    magicianOne.fireBall()

    // 냉마법사로 객체화
    var magicianTwo = Character4("냉마법사", "blue", 162.2, 25, "여")
    println("${magicianTwo.name}의 머리색상은 ${magicianTwo.hairColor}이고 나이는 ${magicianTwo.age}입니다.")
    magicianTwo.fireBall()
}


class Character4 {
    var name: String = ""
    var hairColor:String = ""
    var height:Double = 0.0
    var age:Int = 0
    var gender:String = ""

    // 명시적 생성자 (Constructor)
    // _name, _hairColor, _height와 같이 생성자에 변수를 넘기는 경우에 사용함
    constructor(_name:String, _hairColor:String, _height:Double) {
        println("${_name}을 생성자로 넘겼어요")
        println("${_hairColor}를 생성자로 넘겼어요")
        println("${_height}를 생성자로 넘겼어요")
        name = _name
        hairColor = _hairColor
        height = _height
    }
    // _name, _hairColor, _height, _age, _gender와 같이 생성자에 변수를 넘기는 경우에 사용함
    constructor(_name:String, _hairColor:String, _height:Double, _age:Int, _gender:String) {
        println("${_name}을 생성자로 넘겼어요")
        println("${_hairColor}를 생성자로 넘겼어요")
        println("${_height}를 생성자로 넘겼어요")
        println("${_age}를 생성자로 넘겼어요")
        println("${_gender}를 생성자로 넘겼어요")

        name = _name
        hairColor = _hairColor
        height = _height
        age = _age
        gender = _gender
    }

    fun fireBall() {
        println("파이어볼!")
    }
    fun compositing(device1:String, device2:String): String {
        var device3 = device1 + device2
        println("새로운 무기인 ${device3}입니다")
        return device3
    }

}