package lannstark.lec10.iinterface;

public interface JavaFlyable {

	default void act() {
		System.out.println("파닥 파닥");
	}
}
