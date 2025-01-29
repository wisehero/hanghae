package chap02;

public class PasswordStrengthMeter {

	public PasswordStrength meterV1(String s) {
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

	public PasswordStrength meterV2(String s) {
		if (s == null || s.isEmpty())
			return PasswordStrength.INVALID;

		int metCounts = 0;
		if (s.length() >= 8)
			metCounts++;
		if (meetsContainingNumberCriteria(s))
			metCounts++;
		if (meetsContainingUppercaseCriteria(s))
			metCounts++;

		if (metCounts <= 1)
			return PasswordStrength.WEAK;
		if (metCounts == 2)
			return PasswordStrength.NORMAL;

		return PasswordStrength.STRONG;
	}

	public PasswordStrength meterV3(String s) {
		if (s == null || s.isEmpty())
			return PasswordStrength.INVALID;

		int metCounts = getMetCriteriaCounts(s);

		if (metCounts <= 1)
			return PasswordStrength.WEAK;
		if (metCounts == 2)
			return PasswordStrength.NORMAL;

		return PasswordStrength.STRONG;
	}

	private int getMetCriteriaCounts(String s) {
		int metCounts = 0;
		if (s.length() >= 8)
			metCounts++;
		if (meetsContainingNumberCriteria(s))
			metCounts++;
		if (meetsContainingUppercaseCriteria(s))
			metCounts++;
		return metCounts;
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
