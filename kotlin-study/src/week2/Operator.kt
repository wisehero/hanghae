package week2

fun main() {
    println("===================== 산술 연산자 =====================")
    var num1 = 30
    var num2 = 10

    // 덧셈 연산자 사용해서 num1과 num2를 더한후에 plusResult에 대입함
    var plusResult = num1 + num2
    println("num1 + num2는 ${plusResult}이야")

    // 뺄셈 연산자를 사용해서 num1에서 num2를 뺀 후에 minusResult에 대입함
    var minusResult = num1 - num2
    println("num1 - num2는 ${minusResult}이야")

    // 곱셈 연산자를 사용해서 num1과 num2를 곱한 후에 multiplyResult에 대입함
    var multiplyResult = num1 * num2
    println("num1 * num2는 ${multiplyResult}이야")

    // 나눗셈 연산자를 사용해서 num1에서 num2를 나눈후에 divideResult에 대입함
    var divideResult = num1 / num2
    println("num1 / num2는 ${divideResult}이야")

    // num2를 10에서 7로 변경

    // 나머지 연산자 사용해서 num1에서 num2를 나눈 나머지를 modResult에 대입함
    var modResult = num1 % num2
    println("num1 % num2는 정수로 ${modResult}이야")


    println("===================== 복합 대입 연산자 =====================")

    var num3 = 30
    var num4 = 10

    // 산술 연산자와 대입 연산자를 따로 사용
    // num3의 값을 10 증가시켜서 40을 만들고 싶다면?
    num3 = num3 + 10
    println("산술, 대입 따로 적용한 결과 ${num3}")

    // 복합대입연산자를 사용
    // num4의 값을 10 증가시켜서 20을 만들고 싶다면?
    num4 += 10
    println("복합대입연산자를 사용한 결과 ${num4}")

    // 뺄셈, 곱셈 나눗셈, 나머지 연산도 동일하게 사용 가능하다.
    // 뺄셈 a -= b
    // 곱셈 a *= b
    // 나눗셈 a /= b
    // 나머지 a %= b

    println("===================== 증감 연산자 =====================")

    var num5 = 30
    var num6 = 10
    println("num5의 값은 ${num5}, num6의 값은 ${num6}")

    // num1의 값을 1 증가시키고 싶다면?

    // 1) 산술연산자, 대입연산자 따로 사용
    num5 = num5 + 1
    println("산술, 대입 연산자 따로 사용해서 1증가 ${num5}")

    // 2) 복합 대입 연산자 사용
    num5 += 1
    println("복합 대입 연산자 사용해서 1증가 ${num5}")

    // 3) 증감 연산자
    num5++
    println("증감 연산자 사용해서 1증가 ${num5}")

    // num2의 값을 1 감소시키고 싶다면?

    // 1) 산술연산자, 대입연산자 따로 사용
    num6 = num6 - 1
    println("산술, 대입 연산자 따로 사용해서 1감소 ${num6}")

    // 2) 복합 대입 연산자 사용
    num6 -= 1
    println("복합 대입 연산자 사용해서 1감소 ${num6}")

    // 3) 증감 연산자
    num6--
    println("증감 연산자 사용해서 1감소 ${num6}")

    println("===================== 논리 연산자 =====================")

    var mathScore = 60

    // 수학 점수가 90점 이상인지 판단
    var isMathHighRank = mathScore > 90
    println("내 수학점수는 고등급이 맞나요? ${isMathHighRank}")

    var englishScore = 60

    var varisEnglishMiddleRank = englishScore >= 60
    println("내 영어점수는 중위권이 맞나요? ${varisEnglishMiddleRank}")

    var baseAge = 20
    var myAge = 20
    var teacherAge = 50
    var babyAge = 7

    var isMyAgePass = myAge == baseAge
    var isTeacherAgePass = teacherAge == baseAge
    var isBabyAgeNoPass = babyAge != baseAge

    println("어서오세요~")
    println("이번 이벤트는 ${baseAge}살만 참여할 수 있습니다!")

    println("============= 신분증 검사중 =================")

    println("내 나이는 ${myAge}니까 ${isMyAgePass}이야")
    println("선생님 나이는 ${teacherAge}니까 ${isTeacherAgePass}이야")
    println("${babyAge}살은 참여할 수 없는게 맞아요! ${isBabyAgeNoPass}")


}