package lannstark.lec17

fun main() {
    val fruits = listOf(
        Fruit("사과", 1_000),
        Fruit("사과", 1_000),
        Fruit("사과", 1_000),
        Fruit("사과", 1_000),
        Fruit("사과", 1_000),
        Fruit("바나나", 2_000),
        Fruit("바나나", 2_000),
        Fruit("바나나", 2_000),
        Fruit("바나나", 2_000),
        Fruit("수박", 3_000)
    )

    val filterFruits = filterFruits(fruits) { it.name == "사과" }
    println(filterFruits)

}

private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }
    return results;
}


class Fruit(
    val name: String,
    val price: Int
) {
    override fun toString(): String {
        return "Fruit(name='$name', price=$price)"
    }
}