package lannstark.lec14.dataclass;

import java.util.Objects;

public class JavaPersonDto {

	private final String name;
	private final int age;

	public JavaPersonDto(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		JavaPersonDto that = (JavaPersonDto)object;
		return age == that.age && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(name);
		result = 31 * result + age;
		return result;
	}

	@Override
	public String toString() {
		return "JavaPersonDto{" +
			"name='" + name + '\'' +
			", age=" + age +
			'}';
	}
}
