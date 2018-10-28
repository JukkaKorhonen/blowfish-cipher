package fi.jukkakorhonen.cipher.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import fi.jukkakorhonen.exception.CipherException;
import sun.misc.BASE64Encoder;

public class Encrypter {

	/**
	 * Encrypt String with Blowfish encryption
	 * 
	 * @param key
	 * @param stringToEncrypt
	 * @return encrypted String
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public static String encrypt(String key, String stringToEncrypt) throws CipherException {
		try {
			byte[] keyData = key.getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] bytes = cipher.doFinal(stringToEncrypt.getBytes());
			return new BASE64Encoder().encode(bytes);
		} catch (IllegalArgumentException | InvalidKeyException | IllegalBlockSizeException | 
				BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new CipherException(e, "Cannot encrypt file");
		}

	}
}
