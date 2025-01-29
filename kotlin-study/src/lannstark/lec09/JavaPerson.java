package lannstark.lec09;

public class JavaPerson {

	private String name;
	private int age;

	public JavaPerson(String name, int age) {
		if (age <= 0) {
			throw new IllegalArgumentException("나이는 0보다 커야 합니다.");
		}

		this.name = name;
		this.age = age;
	}

	public JavaPerson(String name) {
		this(name, 1);
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAdult() {
		return age >= 20;
	}

	public String getUpperCaseName() {
		return name.toUpperCase();
	}
}
