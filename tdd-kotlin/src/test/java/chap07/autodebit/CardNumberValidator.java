package chap07.autodebit;

import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CardNumberValidator {

	public CardValidity validate(String cardNumber) {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = newBuilder()
			.uri(URI.create("https://som-external-pg.com/card"))
			.header("Content-Type", "text/plain")
			.POST(BodyPublishers.ofString(cardNumber))
			.build();

		try {
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

			switch (response.body()) {
				case "ok": return CardValidity.VALID;
				case "bad": return CardValidity.INVALID;
				case "expired": return CardValidity.EXPIRED;
				case "theft": return CardValidity.THEFT;
				default: return CardValidity.UNKNOWN;
			}
		} catch (IOException | InterruptedException e) {
			return CardValidity.ERROR;
		}
	}
}
