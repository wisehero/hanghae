package chap07.autodebit;

import java.time.LocalDateTime;

public class AutoDebitRegister {

	private CardNumberValidator validator;
	private AutoDebitInfoRepository repository;

	public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
		this.validator = validator;
		this.repository = repository;
	}

	public RegisterResult register(AutoDebitRequest request) {
		CardValidity validity = validator.validate(request.getCardNumber());
		if (validity != CardValidity.VALID) {
			return RegisterResult.error(validity);
		}
		AutoDebitInfo info = repository.findOne(request.getUserId());
		if (info != null) {
			info.changeCardNumber(request.getCardNumber());
		} else {
			AutoDebitInfo newInfo = new AutoDebitInfo(request.getUserId(), request.getCardNumber(), LocalDateTime.now());
			repository.save(newInfo);
		}

		return RegisterResult.success();
	}
}
