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
	
	@Test
	public void decryptlong_test() {
		String json = "[{\"id\":1,\"service\":\"service\",\"username\":\"keke@gmail.com\",\"password\":\"passu1\"},{\"id\":2,\"service\":\"service2\",\"username\":\"keke@gmail.com\",\"password\":\"passu2\"}]";
		String output = Encrypter.encrypt("jukka", json);
		System.out.println(output);
		assertTrue(output != null);
	}
	
	
	@Test(expected=CipherException.class)
	public void encrypterFail_test() throws Exception {
		Encrypter.encrypt("","Korhonen");
	}
	
}
