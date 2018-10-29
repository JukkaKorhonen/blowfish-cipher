package fi.jukkakorhonen.cipher.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import fi.jukkakorhonen.exception.CipherException;

public class Encrypter {

	/**
	 * Encrypt String with Blowfish encryption
	 * 
	 * @param key
	 * @param stringToEncrypt
	 * @return encrypted String
	 * @throws Exception
	 */
	public static String encrypt(String key, String stringToEncrypt) throws CipherException {
		try {
			byte[] keyData = key.getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] bytes = cipher.doFinal(stringToEncrypt.getBytes());
			return Base64.getEncoder().encodeToString(bytes);
		} catch (IllegalArgumentException | InvalidKeyException | IllegalBlockSizeException | 
				BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new CipherException(e, "Cannot encrypt file");
		}

	}
}
