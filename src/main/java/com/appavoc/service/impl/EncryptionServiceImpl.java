/**
 * *
o	 * Copyright (c) 2019 KhydmaShore Solutions Inc.
o	 *
o	 * All rights reserved.
o	 *
o	 *****************************************************************************
 */

/**
 * @author ZEYNAB
 *
 */

package com.appavoc.service.impl;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.appavoc.service.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	public static final String UTF_8 = "UTF-8";
	public static final String CRYPTO_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final String SHA_1_PRNG = "SHA1PRNG";
	public static final int iterations = 20 * 1000;
	public static final int saltLen = 32;
	public static final int desiredKeyLen = 256;
	static final byte[] SALT = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
			(byte) 0xE3, (byte) 0x03 };


	@Value("${appavoc.security.encryption.key}")
	String key;

	@Value("${appavoc.security.encryption.vector}")
	String initVector;

	private SecretKeySpec secret = null;

	@Override
	public String encrypt(String value) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(EncryptionServiceImpl.UTF_8));
		Cipher cipher = Cipher.getInstance(EncryptionServiceImpl.CRYPTO_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getSecret(), iv);
		byte[] encrypted = cipher.doFinal(value.getBytes());
		return Base64.encodeBase64String(encrypted);
	}

	@Override
	public String decrypt(String encrypted) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(EncryptionServiceImpl.UTF_8));
		Cipher cipher = Cipher.getInstance(EncryptionServiceImpl.CRYPTO_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getSecret(), iv);
		byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
		return new String(original);
	}

	/**
	 * Computes a salted PBKDF2 hash of given plaintext password suitable for
	 * storing in a database. Empty passwords are not supported.
	 */
	@Override
	public String getSaltedHash(String password) throws Exception {
		byte[] salt = SecureRandom.getInstance(EncryptionServiceImpl.SHA_1_PRNG)
				.generateSeed(EncryptionServiceImpl.saltLen);
		// store the salt with the password
		return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
	}

	/**
	 * Checks whether given plaintext password corresponds to a stored salted
	 * hash of the password.
	 */
	@Override
	public boolean check(String password, String stored) throws Exception {
		String[] saltAndPass = stored.split("\\$");
		if (saltAndPass.length != 2) {
			throw new IllegalStateException("The stored password have the form 'salt$hash'");
		}
		String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
		return hashOfInput.equals(saltAndPass[1]);
	}

	// using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
	// cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
	private String hash(String password, byte[] salt) throws Exception {
		if (password == null || password.length() == 0)
			throw new IllegalArgumentException("Empty passwords are not supported.");
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, EncryptionServiceImpl.iterations,
				EncryptionServiceImpl.desiredKeyLen));
		return Base64.encodeBase64String(key.getEncoded());
	}

	SecretKeySpec getSecret() throws Exception {
		if (secret == null) {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(key.toCharArray(), EncryptionServiceImpl.SALT,
					EncryptionServiceImpl.iterations, EncryptionServiceImpl.desiredKeyLen);
			SecretKey tmp = factory.generateSecret(spec);
			secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		}
		return secret;
	}
}
