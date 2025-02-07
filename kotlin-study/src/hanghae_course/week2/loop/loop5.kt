package hanghae_course.week2.loop

fun main() {
    var catStatus = false
    println("고양이는 매우 배고픕니다...")
    println("사료를 10번 먹여주세요.")

    // 고양이가 현재 배고프니까(false) 사료 배급 가능
    if (catStatus == false) {
        for (idx in 0..9) {
            println("${idx}번째: 냠냠")
        }
    }

    // 사료를 10번 준 이후의 상태 변화
    catStatus = true
    if (catStatus == true) {
        println("배부른 고양이입니다.")
    }
}