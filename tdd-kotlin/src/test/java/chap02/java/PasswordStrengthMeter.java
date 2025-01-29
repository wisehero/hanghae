package chap02.java;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String s) {
		if (s == null || s.isEmpty()) {
			return PasswordStrength.INVALID;
		}
		boolean lengthEnough = s.length() >= 8;
		boolean containsNum = meetsContainingNumberCriteria(s);
		boolean containsUpp = meetsContainingUppercaseCriteria(s);

		// 길이만 8글자 이상인 경우
		if (lengthEnough && !containsNum && !containsUpp) {
			return PasswordStrength.WEAK;
		}

		// 숫자만 포함하는 경우
		if (!lengthEnough && containsNum && !containsUpp) {
			return PasswordStrength.WEAK;
		}

		// 대문자만 포함하는 경우
		if (!lengthEnough && !containsNum && containsUpp) {
			return PasswordStrength.WEAK;
		}

		if (!lengthEnough) {
			return PasswordStrength.NORMAL;
		}
		if (!containsNum) {
			return PasswordStrength.NORMAL;
		}
		if (!containsUpp) {
			return PasswordStrength.NORMAL;
		}
		return PasswordStrength.STRONG;
	}

	// 0~9 사이의 숫자를 포함하는지 검사하는 메서드
	private boolean meetsContainingNumberCriteria(String s) {
		for (char ch : s.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				return true;
			}
		}
		return false;
	}

	// 대문자를 포함하는지 검사하는 메서드
	private boolean meetsContainingUppercaseCriteria(String s) {
		for (char ch : s.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
	}
}
