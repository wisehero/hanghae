package chap07.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chap07.user.stub.StubWeakPasswordChecker;

public class UserRegisterTest {
	private UserRegister userRegister;
	private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

	@BeforeEach
	void setUp() {
		userRegister = new UserRegister(stubWeakPasswordChecker);
	}

	@Test
	@DisplayName("약한 암호면 가입 실패")
	void weakPassword() {
		stubWeakPasswordChecker.setWeak(true); // 약한 암호로 설정

		Assertions.assertThatThrownBy(() -> {
			userRegister.register("id", "pw", "email");
		}).isInstanceOf(WeakPasswordException.class);
	}
}
