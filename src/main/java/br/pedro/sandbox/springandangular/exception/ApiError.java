package br.pedro.sandbox.springandangular.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus badRequest;
	private String localizedMessage;
	private List<String> errors;

	public ApiError(HttpStatus badRequest, String localizedMessage, List<String> errors) {
		this.setBadRequest(badRequest);
		this.setLocalizedMessage(localizedMessage);
		this.setErrors(errors);
	}

	public HttpStatus getBadRequest() {
		return badRequest;
	}

	public void setBadRequest(HttpStatus badRequest) {
		this.badRequest = badRequest;
	}

	public String getLocalizedMessage() {
		return localizedMessage;
	}

	public void setLocalizedMessage(String localizedMessage) {
		this.localizedMessage = localizedMessage;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
