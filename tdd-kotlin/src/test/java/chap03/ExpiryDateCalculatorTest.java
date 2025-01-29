package chap03;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

	/**
	 * 1. 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한달 뒤가 서비스 만료일이 된다.
	 *
	 * 2. 2개월 이상 요금을 납부할 수 있다.
	 *
	 * 3. 10만 원을 납부하면 서비스를 1개월 이용할 수 있다.
	 */

	@Test
	@DisplayName("만원을 납부하면 한달 뒤에 만료된다.")
	void test1() {
		assertExpiryDate(LocalDate.of(2025, 3, 1), 10_000, LocalDate.of(2025, 4, 1));
		assertExpiryDate(LocalDate.of(2025, 5, 5), 10_000, LocalDate.of(2025, 6, 5));
	}

	private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
		ExpiryDateCalculator cal = new ExpiryDateCalculator();
		LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
		assertThat(expiryDate).isEqualTo(expectedExpiryDate);
	}
}
