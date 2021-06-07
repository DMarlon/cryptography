# Cryptography
Implementation written to simplify the use of cryptography, creating builders using fluent interface to create more clarity and simplicity in reading and using the code.
Implemented only AES so far, to use it can be done as follows:

## Example:
```java
public class Cryptography {

	public static void main(String... args) {
		System.out.println("Starting...");

		try {
			CryptographySecretKeyIv secretKeyIv = AESCryptographySecretKeyIvBuilder.read("yourfile.properties").build();

			Cryptor cryptorAES = AESCryptographyBuilder
						.usingMode(AESVariation.CBC)
						.withPadding(AESPadding.PKCS5PADDING)
						.usingSecretKey(secretKeyIv.getSecretKey())
						.withIV(secretKeyIv.getIV())
					     .build();

			String encrypt = cryptorAES.encrypt("AESCryptography Test");
			String decrypt = cryptorAES.decrypt(encrypt);

			System.out.println("Encrypted: "+encrypt);
			System.out.println("Decrypted: "+decrypt);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		System.out.println("End!");
	}
}
```

_if you don't want to use property file you can create your secret and IV yourself._

**if you found a bug or an improvement, please send a push request or let me know, I will be very grateful.**

