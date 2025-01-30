package chap07.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chap07.user.stub.StubWeakPasswordChecker;

public class UserRegisterTest {
	private UserRegister userRegister;
	private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
	private MemoryUserRepository fakeRepository = new MemoryUserRepository();

	@BeforeEach
	void setUp() {
		userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository);
	}

	@Test
	@DisplayName("약한 암호면 가입 실패")
	void weakPassword() {
		stubWeakPasswordChecker.setWeak(true); // 약한 암호로 설정

		Assertions.assertThatThrownBy(() -> {
			userRegister.register("id", "pw", "email");
		}).isInstanceOf(WeakPasswordException.class);
	}

	@Test
	@DisplayName("이미 같은 ID가 존재하면 가입 실패")
	void dupIdExists() {
		fakeRepository.save(new User("id", "pw", "email"));

		Assertions.assertThatThrownBy(() -> {
			userRegister.register("id", "pw", "email");
		}).isInstanceOf(DupIdException.class);
	}
}
