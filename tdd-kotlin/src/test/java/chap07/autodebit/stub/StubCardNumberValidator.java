package chap07.autodebit.stub;

import chap07.autodebit.CardNumberValidator;
import chap07.autodebit.CardValidity;

public class StubCardNumberValidator extends CardNumberValidator {

	private String invalidNo;

	public void setInvalidNo(String invalidNo) {
		this.invalidNo = invalidNo;
	}

	@Override
	public CardValidity validate(String cardNumber) {
		if (invalidNo.equals(cardNumber)) {
			return CardValidity.INVALID;
		}
		return CardValidity.VALID;
	}
}
