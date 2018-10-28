package fi.jukkakorhonen.cipher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fi.jukkakorhonen.cipher.encryption.Decrypter;
import fi.jukkakorhonen.cipher.encryption.Encrypter;
import fi.jukkakorhonen.cipher.file.FileWriter;
import fi.jukkakorhonen.cipher.file.FilesReader;
import fi.jukkakorhonen.data.PasswordData;
import fi.jukkakorhonen.exception.CipherException;
import fi.jukkakorhonen.util.DataParser;

/**
 * PasswordProcessor engine to handle data
 * 
 * <ul>
 *  <li>getAllClientData</li>
 *  <li>removeClient</li>
 *  <li>saveClient</li>
 *  <li>findByName</li>
 *  <li>updatePassword</li>
 *  <li>saveAllClients</li>
 * </ul>
 * 
 * @author jukka
 *
 */
public class PasswordProcessor implements PasswordCommInterface {

	@Override
	public Map<Integer, PasswordData> getAllClientData(String secretkey) {
		return composeClientData(secretkey);
	}

	@Override
	public void removeClient(String secretkey, Integer id) {
		if (id != null) {
			Map<Integer, PasswordData> data = composeClientData(secretkey);
			data.remove(id);
			saveClientData(secretkey, data);
		}
	}

	@Override
	public void saveClient(String secretkey, PasswordData password) {
		Map<Integer, PasswordData> data = composeClientData(secretkey);
		data = getWithId(data, password);
		saveClientData(secretkey, data);
	}
	
	@Override
	public void saveAllClients(String encryptionKey, Map<Integer, PasswordData> data) {
		saveClientData(encryptionKey, data);
	}

	@Override
	public Map<Integer, PasswordData> findByName(String encryptionkey, String query) {
		Map<Integer, PasswordData> data = composeClientData(encryptionkey);
		Map<Integer, PasswordData> returnValues = findName(query, data);
		return returnValues;
	}
	
	@Override
	public void updatePassword(String encryptionkey, String updatableservicename, String updatablepassword) {
		Map<Integer, PasswordData> data = composeClientData(encryptionkey);
		Map<Integer, PasswordData> returnValues = findName(updatableservicename, data);
		Set<Integer> values = returnValues.keySet();
		Integer onlyValue = values.iterator().next();
		PasswordData updatable = data.get(onlyValue);
		updatable.setPassword(updatablepassword);
		data.put(onlyValue, updatable);
		saveClientData(encryptionkey, data);
	}
	
	private Map<Integer, PasswordData> findName(String query, Map<Integer, PasswordData> data) {
		Map<Integer, PasswordData> returnValues = new HashMap<Integer, PasswordData>();
		data.keySet().forEach(value -> {
			if (data.get(value)
					.getService()
					.toUpperCase()
					.contains(query.toUpperCase())) {
				returnValues.put(value, data.get(value));
			}
		});
		return returnValues;
	}
	
	private void saveClientData(String secretkey, Map<Integer, PasswordData> data) {
		List<PasswordData> passwords = new ArrayList<PasswordData>();
		
		data.keySet().forEach(key-> {
			passwords.add(data.get(key));
		});
		
		try {
			FileWriter.writeFile(Encrypter.encrypt(secretkey, 
					DataParser.parseToJson(passwords)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<Integer, PasswordData> getWithId(Map<Integer, PasswordData> data, PasswordData password) {
		Set<Integer> numbers = data.keySet();
		numbers = numbers.stream().sorted().collect(Collectors.toSet());
		Integer number = numbers.size();
		password.setId(number);
		data.put(number, password);
		return data;
	}
	
	private Map<Integer, PasswordData> composeClientData(String secretkey) throws CipherException {
		Map<Integer, PasswordData> datareturn = new HashMap<Integer, PasswordData>();
		
		try {
			List<PasswordData> data = DataParser.parseFromJson(Decrypter
					.decrypt(secretkey, FilesReader.readFile()));
//			List<PasswordData> data = DataParser.parseFromJson(FilesReader.readFile());
			if (data != null) {
				data.stream().forEach(singleData-> {
					datareturn.put(singleData.getId(), singleData);
				});
			}
		} catch (CipherException e) {
			throw e;
		}
		return datareturn;
	}

	


}
