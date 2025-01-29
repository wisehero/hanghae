package chap02.java;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterJavaTest {

	/**
	 * 아래의 조건을 만족하는 갯수에 따라 강도를 반환한다.
	 * 1. 8글자 이상
	 * 2. 숫자 포함
	 * 3. 대문자 포함
	 *
	 * 1개 조건만 만족하는 경우: 약함
	 * 2개 조건만 만족하는 경우: 보통
	 * 3개 조건을 모두 만족하는 경우: 강함
	 */

	@Test
	@DisplayName("모든 조건을 만족하는 경우의 테스트")
	void test1() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();

		PasswordStrength result1 = meter.meter("ab12!@AB");
		PasswordStrength result2 = meter.meter("abc1!Add");

		assertThat(result1).isEqualTo(PasswordStrength.STRONG);
		assertThat(result2).isEqualTo(PasswordStrength.STRONG);
	}

	@Test
	@DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우")
	void test2() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		PasswordStrength result = meter.meter("ab12!@A");
		assertThat(result).isEqualTo(PasswordStrength.NORMAL);
	}
}
