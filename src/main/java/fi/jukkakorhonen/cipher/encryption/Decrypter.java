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

public class Decrypter {
	
	/**
	 * Decrypt given String with the encryption key
	 * @param key
	 * @param stringToDecrypt
	 * @return decrypted String
	 */
	public static String decrypt(String key, String stringToDecrypt) {
        byte[] keyData = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");

        try {
        	Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		    byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt));
		    return new String(bytes);
		} catch (InvalidKeyException | IllegalBlockSizeException | 
				BadPaddingException | NoSuchAlgorithmException | 
				NoSuchPaddingException e) {
			throw new CipherException(e, "Cannot decrypt file");
		}
    
       
    }
}
