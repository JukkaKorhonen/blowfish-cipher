package fi.jukkakorhonen.data;

public class PasswordData {

	private int id;
	private String service;
	private String username;
	private String password;
	
	public PasswordData() {}
	
	public PasswordData(String service, String username, String password) {
		this.service = service;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return this.id + " " + this.getService() + " " + this.getUsername() + " " + this.getPassword();
	}

}
