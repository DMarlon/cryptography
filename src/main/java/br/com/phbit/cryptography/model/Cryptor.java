package br.com.phbit.cryptography.model;

import br.com.phbit.cryptography.exception.DecryptionException;
import br.com.phbit.cryptography.exception.EncryptionException;

public interface Cryptor {
	public String encrypt(String text) throws EncryptionException;
	public String decrypt(String text) throws DecryptionException;
}
