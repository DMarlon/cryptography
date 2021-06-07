package br.com.phbit.cryptography.model;

import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import br.com.phbit.cryptography.exception.DecryptionException;
import br.com.phbit.cryptography.exception.EncryptionException;

public class CryptorCipher implements Cryptor {

	private final Cipher encrypt;
	private final Cipher decrypt;

	public CryptorCipher(Cipher encrypt, Cipher decrypt) {
		this.encrypt = encrypt;
		this.decrypt = decrypt;
	}

	public String encrypt(String text) throws EncryptionException {
		try {
		    return Base64.getEncoder().encodeToString(encrypt.doFinal(text.getBytes()));
		} catch (IllegalBlockSizeException | BadPaddingException exception) {
			throw new EncryptionException(exception.getMessage(), exception);
		}
	}

	public String decrypt(String text) throws DecryptionException {
		try {
		    return new String(decrypt.doFinal(Base64.getDecoder().decode(text)));
		} catch (IllegalBlockSizeException | BadPaddingException exception) {
			throw new DecryptionException(exception.getMessage(), exception);
		}
	}
}
