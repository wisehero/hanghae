package lannstark.lec13;

public class JavaHouse {

	private String address;
	private LivingRoom livingRoom;

	public LivingRoom getLivingRoom() {
		return livingRoom;
	}

	public class LivingRoom {
		private double area;

		public LivingRoom(double area) {
			this.area = area;
		}

		public String getAddress() {
			// 바깥 클래스와 연결되어 있다.
			// 하지만 LivingRoom 클래스에 static을 붙이면 바깥 클래스와 연결되지 않는다.
			// 내부 중첩 클래스는 일반적으로 권장되지 않는다.
			// 내부 클래스를 사용할 때는 static을 권장한다.
			return JavaHouse.this.address;
		}
	}
}
