package lannstark.lec10.iinterface;

public interface JavaSwimable {
	default void act() {
		System.out.println("어푸 어푸");
	}
}
