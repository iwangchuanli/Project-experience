package com.email.utils;

public class SendMailException extends Exception {

	public SendMailException() {
	}

	public SendMailException(String message) {
		super(message);
	}

	public SendMailException(Throwable cause) {
		super(cause);
	}
}
