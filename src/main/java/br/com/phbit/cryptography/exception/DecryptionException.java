package br.com.phbit.cryptography.exception;

public class DecryptionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DecryptionException(String message) {
		super(message);
	}

	public DecryptionException(String message, Throwable cause) {
		super(message, cause);
	}
}
