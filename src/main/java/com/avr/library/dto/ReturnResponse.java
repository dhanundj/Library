package com.avr.library.dto;

public class ReturnResponse {
	private String message;

	public ReturnResponse(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ReturnResponse [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ReturnResponse() {
		super();

	}

}