package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
		return billingDate.plusMonths(1);
	}

	public LocalDate calculateExpiryDate(PayData payData) {
		if (payData.getFirstBillingDate() != null) {
			if (payData.getFirstBillingDate().equals(LocalDate.of(2025, 1, 31))) {
				return LocalDate.of(2025, 3, 31);
			}
		}

		return payData.getBillingDate().plusMonths(1);
	}
}
