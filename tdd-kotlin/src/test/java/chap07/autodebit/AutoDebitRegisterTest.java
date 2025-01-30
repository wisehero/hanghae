package chap07.autodebit;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoDebitRegisterTest {

	private AutoDebitRegister register;

	@BeforeEach
	void setUp() {
		CardNumberValidator validator = new CardNumberValidator();
		AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
		register = new AutoDebitRegister(validator, repository);
	}

	@Test
	@DisplayName("유효한 카드번호를 사용하면 VALID")
	void validCard() {
		// 업체에서 받은 테스트용 유효한 카드번호 사용
		AutoDebitRequest request = new AutoDebitRequest("user1", "1234123412341234");
		RegisterResult result = register.register(request);
		assertThat(result.getValidity()).isEqualTo(CardValidity.VALID);
	}

	@Test
	@DisplayName("도난당한 카드를 사용하면 THEFT")
	void theftCard() {
		// 업체에서 받은 테스트용 도난 당한 카드번호 사용
		AutoDebitRequest request = new AutoDebitRequest("user1", "1234567890123456");
		RegisterResult result = register.register(request);
		assertThat(result.getValidity()).isEqualTo(CardValidity.THEFT);
	}
}