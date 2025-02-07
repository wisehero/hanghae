package lannstark.lec10.iinterface

import lannstark.lec10.abstractclass.Animal

class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2;

    override fun move() {
        println("펭귄 움직임 ㄷㄷ")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }
}