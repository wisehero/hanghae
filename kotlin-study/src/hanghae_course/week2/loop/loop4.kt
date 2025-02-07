package hanghae_course.week2.loop

fun main() {
    var infos = arrayOf("꿩", "닭", "참새", "오목눈이", "공작새")

    for (info in infos) {
        if (info == "참새") {
            println("찾았다 참새!")
            break;
        } else {
            continue
        }
    }
}