package br.com.phbit.cryptography.model;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CryptographySecretKeyIv {

	private final SecretKey key;
	private final IvParameterSpec iv;

	public CryptographySecretKeyIv(SecretKey key, IvParameterSpec iv)  {
		if (key == null || iv == null)
			throw new IllegalArgumentException("key and iv is required");

		this.key =  key;
		this.iv = iv;
	}

	public SecretKey getSecretKey() {
		return key;
	}

	public IvParameterSpec getIV() {
		return iv;
	}
}
