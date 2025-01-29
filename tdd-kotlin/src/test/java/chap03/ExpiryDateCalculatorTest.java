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
		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2025, 3, 1))
				.payAmount(10_000)
				.build(),
			LocalDate.of(2025, 4, 1));

		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2025, 5, 5))
				.payAmount(10_000)
				.build(),
			LocalDate.of(2025, 6, 5));
	}

	@Test
	@DisplayName("납부일과 한달 뒤 일자가 같지 않음")
	void test2() {
		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2025, 1, 31))
				.payAmount(10_000)
				.build(),
			LocalDate.of(2025, 2, 28));

		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2024, 1, 31))
				.payAmount(10_000)
				.build(),
			LocalDate.of(2024, 2, 29));

		// 윤년
		assertExpiryDate(
			PayData.builder()
				.billingDate(LocalDate.of(2020, 1, 31))
				.payAmount(10_000)
				.build(),
			LocalDate.of(2020, 2, 29));
	}

	@Test
	@DisplayName("첫 납부일과 만료일 일자가 다를때 만원 납부")
	void test3() {
		PayData payData1 = PayData.builder()
			.firstBillingDate(LocalDate.of(2025, 1, 31))
			.billingDate(LocalDate.of(2025, 2, 28))
			.payAmount(10_000)
			.build();

		assertExpiryDate(payData1, LocalDate.of(2025, 3, 31));

		PayData payData2 = PayData.builder()
			.firstBillingDate(LocalDate.of(2025, 1, 30))
			.billingDate(LocalDate.of(2025, 2, 28))
			.payAmount(10_000)
			.build();

		assertExpiryDate(payData2, LocalDate.of(2025, 3, 30));

		PayData payData3 = PayData.builder()
			.firstBillingDate(LocalDate.of(2025, 5, 31))
			.billingDate(LocalDate.of(2025, 6, 30))
			.payAmount(10_000)
			.build();

		assertExpiryDate(payData3, LocalDate.of(2025, 7, 31));
	}

	private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
		ExpiryDateCalculator cal = new ExpiryDateCalculator();
		LocalDate expiryDate = cal.calculateExpiryDate(payData);
		assertThat(expiryDate).isEqualTo(expectedExpiryDate);
	}
}
