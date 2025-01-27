package week2.condition

fun main() {
    var koreanScore = 88
    var englishScore = 92
    var mathScore = 99
    var average = (koreanScore + englishScore + mathScore) / 3

    if (average >= 90) {
        println("당신의 등급은 A입니다.")
    } else if (average >= 80) {
        println("당신의 등급은 B입니다.")
    } else if (average >= 70) {
        println("당신의 등급은 C입니다.")
    } else {
        println("당신의 등급은 F입니다.")
    }
}