package lannstark.lec10.abstractclass;

public class JavaCat extends JavaAnimal {

	public JavaCat(String species) {
		super(species, 4);
	}

	@Override
	public void move() {
		System.out.println("고양이 걷기");
	}
}
