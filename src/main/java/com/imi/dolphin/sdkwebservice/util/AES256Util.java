package com.imi.dolphin.sdkwebservice.util;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class AES256Util {

	private Cipher ecipher;
	private Cipher dcipher;

	private SecretKey secretKey, tmp;
	private SecretKeyFactory factory;
	private KeySpec spec;

	private String secretKeySession = "", salt = "";

	public AES256Util() throws InvalidKeySpecException {
		try {

			// generate secret key
			secretKey = KeyGenerator.getInstance("AES").generateKey();

			ecipher = Cipher.getInstance("AES");
			dcipher = Cipher.getInstance("AES");

			// initialize the ciphers with the given key
			ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
			dcipher.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return;
		}

	}

	public AES256Util(String secretKeySession, String salt) throws InvalidKeySpecException {
		try {

			// generate secret key
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			spec = new PBEKeySpec(secretKeySession.toCharArray(), salt.getBytes(), 65536, 256);
			tmp = factory.generateSecret(spec);

			secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			ecipher = Cipher.getInstance("AES");
			dcipher = Cipher.getInstance("AES");

			// initialize the ciphers with the given key
			ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
			dcipher.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return;
		}

	}

	public String encrypt(String str) {

		try {
			// encode the string into a sequence of bytes using the named charset
			// storing the result into a new byte array. 
			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64
			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public String decrypt(String str) {

		try {
			// decode with base64 to get bytes
			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset
			return new String(utf8, "UTF8");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

}
