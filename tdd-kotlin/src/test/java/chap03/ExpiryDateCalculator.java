package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
		return billingDate.plusMonths(1);
	}

	public LocalDate calculateExpiryDate(PayData payData) {
		int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;
		if (payData.getFirstBillingDate() != null) {
			LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
			final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
			if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
				final int dayLenOfCandidateMonth = YearMonth.from(candidateExp).lengthOfMonth();
				if (dayLenOfCandidateMonth < payData.getFirstBillingDate().getDayOfMonth()) {
					return candidateExp.withDayOfMonth(dayLenOfCandidateMonth);
				}
				return candidateExp.withDayOfMonth(dayOfFirstBilling);
			} else {
				return candidateExp;
			}
		} else {
			return payData.getBillingDate().plusMonths(addedMonths);
		}
	}
}
