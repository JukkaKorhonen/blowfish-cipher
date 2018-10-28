package fi.jukkakorhonen.cipher;
import java.util.Map;
import java.util.Scanner;

import fi.jukkakorhonen.data.PasswordData;
import fi.jukkakorhonen.exception.CipherException;

/**
 * Commandline version to use encryption 
 * @author jukka
 *
 */
public class App  {
	
	private static String encryptionkey;
	
    public static void main( String[] args ) {

    	try {
    		System.out.println("Encryption key?");
    		Scanner s = new Scanner(System.in);
    		encryptionkey = s.nextLine();
    		System.out.println("Encryption key: " + encryptionkey);
    		
    		PasswordProcessor processor = new PasswordProcessor();
    		writeMenu();
    		
    		do {
    		    String value = s.nextLine();
    		
    			switch (value) {
    			case "1"://List all data
    				Map<Integer, PasswordData> datalist = processor.getAllClientData(encryptionkey);
    				datalist.keySet().forEach(x -> {
    					System.out.println(datalist.get(x));
    				});
    				writeMenu();
    				break;
    			case "2"://List by name
    				System.out.println("Write name...");
    				String query = s.nextLine();
    				if (!query.isEmpty()) {
    					Map<Integer, PasswordData> datalistByName = processor.findByName(encryptionkey, query);
    					if (!datalistByName.isEmpty()) {
    						datalistByName.keySet().forEach(x -> {
            					System.out.println(datalistByName.get(x));
            				});
    					} else {
    						System.out.println("No result were found");
    					}
    					writeMenu();
        				break;
    				}
    			case "3": //Add new username
    				System.out.println("Write service name...");
    				String servicename = s.nextLine();
    				System.out.println("Service name : " + servicename + ", write username");
    				String username = s.nextLine();
    				System.out.println("Username : " + username + ", write password");
    				String password = s.nextLine();
    				System.out.println(password + " saving...");
    				
    				PasswordData passwordData = new PasswordData(servicename, username, password);
    				processor.saveClient(encryptionkey, passwordData);
    				System.out.println("Saved!");
    				writeMenu();
    				break;
    			case "4": //Update password
    				System.out.println("Write service name...");
    				String updatableservicename = s.nextLine();
    				System.out.println("Write a new password...");
    				String updatablepassword = s.nextLine();
    				
    				Map<Integer, PasswordData> uptadabledatalist = processor.findByName(encryptionkey, updatableservicename);
    				
    				if (uptadabledatalist.size() == 1) {
    					processor.updatePassword(encryptionkey, updatableservicename, updatablepassword);
    					System.out.println("updated!");
    				} else if (uptadabledatalist.size() > 1) {
    					System.out.println("Found several matches");
    					uptadabledatalist.keySet().forEach(x -> {
        					System.out.println(uptadabledatalist.get(x));
        				});
    				} else {
    					System.out.println("No results were found.");
    				}
    				writeMenu();
    				break;
    			case "5": //Delete service
    				System.out.println("Write service name...");
    				String deleteservicename = s.nextLine();
    				Map<Integer, PasswordData> datalistByName = 
    						processor.findByName(encryptionkey, deleteservicename);
    				
    				if (datalistByName.size() == 1) {
    					processor.removeClient(encryptionkey, datalistByName
    							.keySet().stream().findFirst().get());
    					System.out.println("Removed");
    				} else if (datalistByName.size() > 1) {
    					System.out.println("Found several matches");
    					datalistByName.keySet().forEach(x -> {
        					System.out.println(datalistByName.get(x));
        				});
    				} else {
    					System.out.println("No results were found.");
    				}
    				writeMenu();
    				break;
    			case "6": //update encryption key
    				System.out.println("Write current encryption key...");
    				String oldEncryptionKey = s.nextLine();
    				if (oldEncryptionKey.equals(encryptionkey)) {
    					System.out.println("New encryption key...");
    					String newEncryptionKey = s.nextLine();
    					Map<Integer, PasswordData> datatoupdate 
    						= processor.getAllClientData(encryptionkey);
    					processor.saveAllClients(newEncryptionKey, datatoupdate);
    				} else {
    					System.out.println("Wrong encryption key");
    				}
    				writeMenu();
    				break;
    			case "x":
    				s.close();
    				return;
    			}
    			
    		} while (s.hasNext());
    		s.close();
		} catch (CipherException e) {
			System.out.println(e.getExceptionmessage());
		}
        
    }

	private static void writeMenu() {
		System.out.println("Actions: \n"
				+ " 1. List all data \n"
				+ " 2. Search by name \n"
				+ " 3. Add new username \n"
				+ " 4. Update password \n"
				+ " 5. Delete service \n"
				+ " 6. Update encryption key \n"
				+ " x. Quit");
	}
    
    
     
    
}
