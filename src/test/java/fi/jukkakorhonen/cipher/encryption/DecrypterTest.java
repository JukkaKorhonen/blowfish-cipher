package fi.jukkakorhonen.cipher.encryption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.jukkakorhonen.cipher.encryption.Decrypter;
import fi.jukkakorhonen.exception.CipherException;


public class DecrypterTest {

	@Test
	public void decrypt_test() throws Exception {
		String encrypted = "eDp89M+izL0NEyHI5KPjRg==";
		String decrypted = "Korhonen";
		
		String output = Decrypter.decrypt("Jukka", encrypted);
		assertTrue(output != null);
		assertEquals(output, decrypted);
	}
	
	@Test
	public void decryptFail_test() {
		String encrypted = "eDp89M+izL0NEyHI5KPjRg==";
		
		try {
			Decrypter.decrypt("Jukka1", encrypted);
		} catch (Exception e) {
			assertTrue(e instanceof CipherException);
		}
	}
}
