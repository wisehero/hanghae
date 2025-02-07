package lannstark.lec10.abstractclass

class Penguin(
    species: String
) : Animal(species, 2) {

    private val wingCount: Int = 2;

    override fun move() {
        println("펭귄이 움직인다~");
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount;
}