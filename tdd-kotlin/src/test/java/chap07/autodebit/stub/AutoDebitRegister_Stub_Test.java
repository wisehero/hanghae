package chap07.autodebit.stub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chap07.autodebit.AutoDebitRegister;
import chap07.autodebit.AutoDebitRequest;
import chap07.autodebit.CardValidity;
import chap07.autodebit.RegisterResult;

public class AutoDebitRegister_Stub_Test {

	private AutoDebitRegister register;
	private StubCardNumberValidator stubValidator;
	private StubAutoDebitInfoRepository stubRepository;

	@BeforeEach
	void setUp() {
		stubValidator = new StubCardNumberValidator(); // 여기에서 StubCardNumberValidator를 사용
		stubRepository = new StubAutoDebitInfoRepository();
		register = new AutoDebitRegister(stubValidator, stubRepository);
	}

	@Test
	@DisplayName("정상적이지 않은 카드 번호 테스트")
	void invalidCardNumber() {
		stubValidator.setInvalidNo("111122223333");

		AutoDebitRequest request = new AutoDebitRequest("user1", "111122223333");
		RegisterResult result = register.register(request);

		assertThat(result.getValidity()).isEqualTo(CardValidity.INVALID);
	}

	@Test
	@DisplayName("도난당한 카드 번호 테스트")
	void theftCardNumber() {
		stubValidator.setTheftNo("1234567890123456");

		AutoDebitRequest request = new AutoDebitRequest("user1", "1234567890123456");
		RegisterResult result = register.register(request);

		assertThat(result.getValidity()).isEqualTo(CardValidity.THEFT);
	}
}
