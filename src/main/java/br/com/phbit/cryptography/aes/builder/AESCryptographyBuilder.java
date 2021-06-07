package br.com.phbit.cryptography.aes.builder;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import br.com.phbit.cryptography.aes.enumerator.AESPadding;
import br.com.phbit.cryptography.aes.enumerator.AESVariation;
import br.com.phbit.cryptography.enumerator.CryptographyAlgorithm;
import br.com.phbit.cryptography.exception.CreateCryptorException;
import br.com.phbit.cryptography.model.Cryptor;
import br.com.phbit.cryptography.model.CryptorCipher;

public class AESCryptographyBuilder {

	private AESVariation mode;
	private AESPadding padding;
	private SecretKey secretKey;
	private IvParameterSpec iv;

	private AESCryptographyBuilder(AESVariation mode) {
		this.mode = mode;
	}

	public static AESCryptographyBuilder usingMode(AESVariation mode) {
		return new AESCryptographyBuilder(mode);
	}

	public AESCryptographyBuilder withPadding(AESPadding padding) {
		this.padding = padding;
		return this;
	}

	public AESCryptographyBuilder usingSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
		return this;
	}

	public AESCryptographyBuilder withIV(IvParameterSpec iv) {
		this.iv = iv;
		return this;
	}

	public Cryptor build() throws CreateCryptorException {
		if (!isSecretKeyValid())
			throw new CreateCryptorException("Secret invalid!");

		if (!isIvValid())
			throw new CreateCryptorException(" IV invalid!");

	    return new CryptorCipher(createEncrypt(), createDecrypt());
	}

	private Cipher createEncrypt() throws CreateCryptorException {
	    try {
	    	Cipher cipher = Cipher.getInstance(getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

		    return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException exception) {
			throw new CreateCryptorException(exception.getMessage(), exception);
		}
	}

	private Cipher createDecrypt() throws CreateCryptorException {
		try {
			Cipher cipher = Cipher.getInstance(getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

		    return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException  exception) {
			throw new CreateCryptorException(exception.getMessage(), exception);
		}
	}

	private boolean isSecretKeyValid() {
		return secretKey != null;
	}

	private boolean isIvValid() {
		return iv != null;
	}

	private String getAlgorithm() {
		return CryptographyAlgorithm.AES.getInitials()+"/"+(mode == null ? AESVariation.CBC : mode).getInitials()+"/"+(padding == null ? AESPadding.PKCS5PADDING : padding).getName();
	}
}
