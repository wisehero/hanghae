package chap07.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chap07.user.stub.StubWeakPasswordChecker;

public class UserRegisterTest {
	private UserRegister userRegister;
	private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
	private MemoryUserRepository fakeRepository = new MemoryUserRepository();
	private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

	@BeforeEach
	void setUp() {
		userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
	}

	@Test
	@DisplayName("약한 암호면 가입 실패")
	void weakPassword() {
		stubWeakPasswordChecker.setWeak(true); // 약한 암호로 설정

		assertThatThrownBy(() -> {
			userRegister.register("id", "pw", "email");
		}).isInstanceOf(WeakPasswordException.class);
	}

	@Test
	@DisplayName("이미 같은 ID가 존재하면 가입 실패")
	void dupIdExists() {
		fakeRepository.save(new User("id", "pw", "email"));

		assertThatThrownBy(() -> {
			userRegister.register("id", "pw", "email");
		}).isInstanceOf(DupIdException.class);
	}

	@Test
	@DisplayName("같은 ID가 없으면 가입 성공함")
	void noDupId_RegisterSuccess() {
		userRegister.register("id", "pw", "email");

		User savedUser = fakeRepository.findById("id"); // 가입 결과 확인
		assertThat(savedUser.getId()).isEqualTo("id");
		assertThat(savedUser.getEmail()).isEqualTo("email");
	}

	@Test
	@DisplayName("가입하면 메일을 전송함")
	void whenRegisterThenSendMail() {
		userRegister.register("id", "pw", "email");
		assertThat(spyEmailNotifier.isCalled()).isTrue();
		assertThat(spyEmailNotifier.getEmail()).isEqualTo("email");
	}
}
