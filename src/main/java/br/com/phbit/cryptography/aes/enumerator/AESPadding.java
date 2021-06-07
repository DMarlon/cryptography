package br.com.phbit.cryptography.aes.enumerator;

public enum AESPadding {
	NOPADDING("NoPadding"),
	PKCS5PADDING("PKCS5Padding");

	private String name;

	private AESPadding(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static AESPadding getCryptographicAlgorithm(String padding) {
        for(AESPadding aesPadding: values()) {
			if(aesPadding.getName().equals(padding))
            	return aesPadding;
        }

        throw new IllegalArgumentException(padding+" is not a valid cryptographic padding");
    }

	@Override
	public String toString() {
		return this.getName();
	}
}
