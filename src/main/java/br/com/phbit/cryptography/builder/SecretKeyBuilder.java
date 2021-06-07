package br.com.phbit.cryptography.builder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import br.com.phbit.cryptography.enumerator.CryptographyAlgorithm;
import br.com.phbit.cryptography.enumerator.SecretKeyFactoryAlgorithm;
import br.com.phbit.cryptography.exception.GenerateSecretException;

public class SecretKeyBuilder {

	private String salt;
	private int keyLength;
	private int iterationCount;
	private CryptographyAlgorithm keyAlgorithm;
	private SecretKeyFactoryAlgorithm algorithm;

	private SecretKeyBuilder(SecretKeyFactoryAlgorithm algorithm) {
		this.salt = null;
		this.keyLength = 0;
		this.iterationCount = 0;
		this.keyAlgorithm = null;
		this.algorithm = algorithm;
	}

	public static SecretKeyBuilder usingAlgorithm(SecretKeyFactoryAlgorithm algorithm) {
		return new SecretKeyBuilder(algorithm);
	}

	public SecretKeyBuilder usingSalt(String salt) {
		this.salt = salt;

		return this;
	}

	public SecretKeyBuilder withKeyLength(int keyLength) {
		this.keyLength = keyLength;

		return this;
	}

	public SecretKeyBuilder withIterationCount(int iterationCount) {
		this.iterationCount = iterationCount;

		return this;
	}

	public SecretKeyBuilder toAlgorithm(CryptographyAlgorithm algorithm) {
		this.keyAlgorithm = algorithm;

		return this;
	}

	public SecretKey build(String secret) throws GenerateSecretException {
		checkRequiredFields();

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm.getName());

			PBEKeySpec spec;
			if (salt == null)
				spec = new PBEKeySpec(secret.toCharArray());
			else if (keyLength > 0)
				spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), iterationCount, keyLength);
			else
				spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), iterationCount);

			return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), keyAlgorithm.getInitials());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			throw new GenerateSecretException(exception.getMessage(), exception);
		}
	}

	private void checkRequiredFields() throws GenerateSecretException {
		if (keyAlgorithm == null)
			throw new GenerateSecretException("It is necessary to inform the interactions count when salt informed!");
		if (salt != null && iterationCount == 0)
			throw new GenerateSecretException("It is necessary to inform the interactions count when salt informed!");
		if (salt == null && (iterationCount > 0 && keyLength > 0))
			throw new GenerateSecretException("When not has salt informed iteration count and key length is ignored!");
	}
}
