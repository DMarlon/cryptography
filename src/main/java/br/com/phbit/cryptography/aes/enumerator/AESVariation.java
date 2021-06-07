package br.com.phbit.cryptography.aes.enumerator;

public enum AESVariation {

    ECB("ECB", "Electronic Code Book"),
    CBC("CBC", "Cipher Block Chaining"),
    CFB("CFB", "Cipher FeedBack"),
    OFB("OFB", "Output FeedBack"),
    CTR("CTR", "Counter"),
    GCM("GCM", "Galois/Counter Mode");

    private String initials;
	private String description;

	private AESVariation(String initials, String description) {
		this.initials = initials;
		this.description = description;
	}

	public String getInitials() {
		return initials;
	}

	public String getDescription() {
		return description;
	}

	public static AESVariation getVariation(String initials) {
        for(AESVariation variation : values()) {
			if(variation.getInitials().equals(initials))
            	return variation;
        }

        throw new IllegalArgumentException(initials+" is not a valid AES variant");
    }

	@Override
	public String toString() {
		return this.getDescription();
	}
}
