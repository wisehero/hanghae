package lannstark.lec13

class House(
    val address: String,
    val livingRoom: LivingRoom
) {
    inner class LivingRoom( // 기본적으로 바깥 클래스를 참조하지 않는다. 하고 싶다면 inner 키워드를 붙여줘야 한다.
        private var area: Double
    ) {
        val address: String
            get() = this@House.address
    }
}