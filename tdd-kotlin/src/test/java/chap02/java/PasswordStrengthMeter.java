package chap02.java;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String s) {
		if (s == null || s.isEmpty()) {
			return PasswordStrength.INVALID;
		}

		if (s.length() < 8) {
			return PasswordStrength.NORMAL;
		}

		boolean containsNum = meetsContainingNumberCriteria(s);
		if (!containsNum) {
			return PasswordStrength.NORMAL;
		}

		boolean containsUpp = meetsContainingUppercaseCriteria(s);
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
