package chap07.autodebit.stub;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chap07.autodebit.AutoDebitInfo;
import chap07.autodebit.AutoDebitRegister;
import chap07.autodebit.AutoDebitRequest;
import chap07.autodebit.MemoryAutoDebitInfoRepository;
import chap07.autodebit.RegisterResult;

public class AutoDebitRegister_Fake_Test {

	private AutoDebitRegister register;
	private StubCardNumberValidator cardNumberValidator;
	private MemoryAutoDebitInfoRepository repository; // 메모리 저장소. DB 대역

	@BeforeEach
	void setUp() {
		cardNumberValidator = new StubCardNumberValidator();
		repository = new MemoryAutoDebitInfoRepository();
		register = new AutoDebitRegister(cardNumberValidator, repository);
	}

	@Test
	@DisplayName("자동이체 정보가 등록되어 있을 때 기존 정보가 올바르게 바뀌는지 테스트")
	void alreadyRegistered_InfoUpdated() {
		repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

		AutoDebitRequest request = new AutoDebitRequest("user1", "123456789012");
		RegisterResult result = register.register(request);

		AutoDebitInfo saved = repository.findOne("user1");
		assertThat(saved.getCardNumber()).isEqualTo("123456789012");
	}

	@Test
	@DisplayName("자동이체 정보가 없을 때 신규로 등록되는지 테스트")
	void notYetRegistered_newInfoRegistered() {
		AutoDebitRequest request = new AutoDebitRequest("user1", "123412341234");
		RegisterResult result = this.register.register(request);

		AutoDebitInfo saved = repository.findOne("user1");
		assertThat(saved.getCardNumber()).isEqualTo("123412341234");
	}
}
