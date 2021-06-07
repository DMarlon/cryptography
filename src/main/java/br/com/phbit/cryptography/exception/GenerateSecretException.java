package br.com.phbit.cryptography.exception;

public class GenerateSecretException extends Exception {

	private static final long serialVersionUID = 1L;

	public GenerateSecretException(String message) {
		super(message);
	}

	public GenerateSecretException(String message, Throwable cause) {
		super(message, cause);
	}
}
