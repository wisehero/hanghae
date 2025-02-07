package lannstark.lec10.iinterface;

import lannstark.lec10.abstractclass.JavaAnimal;

public class JavaPenguin extends JavaAnimal implements JavaSwimable, JavaFlyable {

	private final int wingCount;

	public JavaPenguin(String species) {
		super(species, 2);
		this.wingCount = 2;
	}

	@Override
	public void move() {
		System.out.println("펭귄 움직인다");
	}

	@Override
	public int getLegCount() {
		return super.legCount + this.wingCount;
	}

	@Override
	public void act() {
		JavaSwimable.super.act();
		JavaFlyable.super.act();
	}
}
