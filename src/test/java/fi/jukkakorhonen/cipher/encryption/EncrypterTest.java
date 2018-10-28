package fi.jukkakorhonen.cipher.encryption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.jukkakorhonen.cipher.encryption.Encrypter;
import fi.jukkakorhonen.exception.CipherException;

public class EncrypterTest {

	@Test
	public void encrypter_test() throws Exception {
		String crypt = "eDp89M+izL0NEyHI5KPjRg==";
		String encryptedString = Encrypter.encrypt("Jukka","Korhonen");
		assertTrue(encryptedString != null);
		assertEquals(encryptedString, crypt);
	}
	
	@Test(expected=CipherException.class)
	public void encrypterFail_test() throws Exception {
		Encrypter.encrypt("","Korhonen");
	}
	
}
