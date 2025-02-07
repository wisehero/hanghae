package lannstark.lec10.abstractclass;

public class JavaPenguin extends JavaAnimal {

	private final int wingCount;

	public JavaPenguin(String species, int legCount, int wingCount) {
		super(species, 2);
		this.wingCount = 2;
	}

	@Override
	public void move() {
		System.out.println("펭귄 움직임");
	}

	@Override
	public int getLegCount() {
		return super.legCount + this.wingCount;
	}
}
