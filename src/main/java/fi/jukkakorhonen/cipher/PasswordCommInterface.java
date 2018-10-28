package fi.jukkakorhonen.cipher;

import java.util.Map;

import fi.jukkakorhonen.data.PasswordData;

public interface PasswordCommInterface {

	Map<Integer, PasswordData> getAllClientData(String secretkey);
	
	void removeClient(String secretkey, Integer id);
	
	void saveClient(String secretkey, PasswordData password);

	Map<Integer, PasswordData> findByName(String secretkey, String query);

	void updatePassword(String encryptionkey, String updatableservicename, String updatablepassword);

	void saveAllClients(String newEncryptionKey, Map<Integer, PasswordData> data);
	
}
