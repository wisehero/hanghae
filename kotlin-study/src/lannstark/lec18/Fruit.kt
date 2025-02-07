package lannstark.lec18

fun main() {
    val fruits = listOf(
        Fruit(1, "apple", 1000, 1200),
        Fruit(2, "apple", 1000, 1500),
        Fruit(3, "apple", 1000, 1700),
        Fruit(4, "banana", 1500, 1500),
        Fruit(5, "kiwi", 3000, 3000),
        Fruit(6, "orange", 700, 700),
        Fruit(7, "melon", 5000, 5000)
    )

    // filter
    val apples = fruits.filter { fruit -> fruit.name == "apple" }
    println(apples)

    // all : 조건을 모두 만족하면 true, 그렇지 않으면 false
    // 모든 frutis 배열 안에 있는 fruit의 이름이 사과인가?
    val isAllApple = fruits.all { fruit -> fruit.name == "apple" }
    println(isAllApple) // false

    // any : 조건을 하나라도 만족하면 true, 그렇지 않으면 false
    // frutis 배열 안에 있는 fruit 중에 이름이 사과인게 하나라도 있나?
    val isAnyApple = fruits.any { fruit -> fruit.name == "apple" }
    println(isAnyApple) // true

    // none : 조건을 모두 만족하지 않으면 true, 만족하면 false
    // frutis 배열 안에 있는 fruit 중에 이름이 사과인게 하나도 없나?
    val isNoneApple = fruits.none { fruit -> fruit.name == "apple" }
    println(isNoneApple) // false

    // count: 조건을 만족하는 요소의 개수를 반환
    // frutis 배열 안에 있는 fruit 중에 이름이 사과인게 몇개나 있나?
    val countApple = fruits.count { fruit -> fruit.name == "apple" }
    println(countApple) // 3

    // sortedBy : 정렬
    // 가격이 낮은 순으로 정렬
    val sortedFruits = fruits.sortedBy { fruit -> fruit.currentPrice }
    println(sortedFruits)

    // sortedByDescending : 정렬
    // 가격이 높은 순으로 정렬
    val sortedFruitsDesc = fruits.sortedByDescending { fruit -> fruit.currentPrice }
    println(sortedFruitsDesc)

    // first : 조건을 만족하는 첫번째 요소를 반환
    // 이름이 사과인 첫번째 요소를 반환
    val firstApple = fruits.first { fruit -> fruit.name == "apple" }
    println(firstApple)

    // last : 조건을 만족하는 마지막 요소를 반환
    // 이름이 사과인 마지막 요소를 반환
    val lastApple = fruits.last { fruit -> fruit.name == "apple" }
    println(lastApple)

    // firstOrNull : 조건을 만족하는 첫번째 요소를 반환, 없으면 null
    // 이름이 포도인 첫번째 요소를 반환
    val firstGrape = fruits.firstOrNull { fruit -> fruit.name == "grape" }
    println(firstGrape) // null

    // groupby : 동일한 키를 가진 요소끼리 그룹화
    // 이름이 같은 과일끼리 그룹화
    val groupByFruits: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
    println(groupByFruits)
}

data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long
) {

}