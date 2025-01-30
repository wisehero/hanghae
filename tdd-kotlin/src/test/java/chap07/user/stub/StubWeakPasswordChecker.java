package chap07.user.stub;

import chap07.user.WeakPasswordChecker;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

	private boolean weak;

	public void setWeak(boolean weak) {
		this.weak = weak;
	}

	@Override
	public boolean checkPasswordWeak(String pw) {
		return weak;
	}
}
