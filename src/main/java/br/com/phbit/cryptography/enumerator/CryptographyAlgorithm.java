package br.com.phbit.cryptography.enumerator;

public enum CryptographyAlgorithm {
	AES("AES", "Advanced Encryption Standard"),
	DES("DES", "Data Encryption Standart"),
	RSA("RSA", "Rivest Shamir Adleman");

	private String initials;
	private String description;

	private CryptographyAlgorithm(String initials, String description) {
		this.initials = initials;
		this.description = description;
	}

	public String getInitials() {
		return initials;
	}

	public String getDescription() {
		return description;
	}

	public static CryptographyAlgorithm getCryptographicAlgorithm(String initials) {
        for(CryptographyAlgorithm algorithm : values()) {
			if(algorithm.getInitials().equals(initials))
            	return algorithm;
        }

        throw new IllegalArgumentException(initials+" is not a valid cryptographic algorithm");
    }

	@Override
	public String toString() {
		return this.getDescription();
	}
}
