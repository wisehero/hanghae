package chap07.autodebit;

public class AutoDebitRequest {

	private String userId;
	private String cardNumber;

	public AutoDebitRequest(String userId, String cardNumber) {
		this.userId = userId;
		this.cardNumber = cardNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}
}
