package chap02.java;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

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

	private PasswordStrengthMeter meter = new PasswordStrengthMeter();

	private void assertStrength(String password, PasswordStrength expStr) {
		// PasswordStrength result = meter.meterV1(password);
		// PasswordStrength result = meter.meterV2(password);
		PasswordStrength result = meter.meterV3(password);
		assertThat(result).isEqualTo(expStr);
	}

	@Test
	@DisplayName("모든 조건을 만족하는 경우의 테스트")
	void test1() {
		assertStrength("abc1!Add", PasswordStrength.STRONG);
		assertStrength("ab12!@AB", PasswordStrength.STRONG);
	}

	@Test
	@DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우")
	void test2() {
		assertStrength("ab12!@A", PasswordStrength.NORMAL);
	}

	@Test
	@DisplayName("입력이 null인 경우에는 INVALID를 반환")
	void test3() {
		assertStrength(null, PasswordStrength.INVALID);
	}

	@Test
	@DisplayName("입력이 빈 문자열인 경우에는 INVALID를 반환")
	void test4() {
		assertStrength("", PasswordStrength.INVALID);
	}

	@Test
	@DisplayName("대문자를 포함하지 않고 나머지를 충족하는 경우")
	void test5() {
		assertStrength("ab12!@df", PasswordStrength.NORMAL);
	}

	@Test
	@DisplayName("길이가 8글자 이상인 조건만 충족하는 경우 WEAK를 반환")
	void test6() {
		assertStrength("abdefghi", PasswordStrength.WEAK);
	}

	@Test
	@DisplayName("숫자 포함 조건만 충족하는 경우엔 WEAK")
	void test7() {
		assertStrength("12345", PasswordStrength.WEAK);
	}

	@Test
	@DisplayName("대문자 포함 조건만 충족하는 경우엔 WEAK")
	void test8() {
		assertStrength("ABZEF", PasswordStrength.WEAK);
	}

	@Test
	@DisplayName("아무 조건도 충족하지 않은 경우 WEAK")
	void test9() {
		assertStrength("abc", PasswordStrength.WEAK);
	}
}
