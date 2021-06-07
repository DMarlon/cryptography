package br.com.phbit.cryptography.enumerator;

public enum SecretKeyFactoryAlgorithm {

	PBEWITHMD5ANDDES("PBEWithMD5AndDES", "Password Based Encryption with MD5 and DES"),
	PBKDF2WITHHMACSHA256("PBKDF2WithHmacSHA256", "Password Based Encryption Derivation Function 2 with HMAC and SHA256");

	private String name;
	private String description;

	private SecretKeyFactoryAlgorithm(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public static SecretKeyFactoryAlgorithm getCryptographicAlgorithm(String name) {
        for(SecretKeyFactoryAlgorithm algorithm : values()) {
			if(algorithm.getName().equals(name))
            	return algorithm;
        }

        throw new IllegalArgumentException(name+" is not a valid secret key factory algorithm");
    }

	@Override
	public String toString() {
		return this.getDescription();
	}
}
