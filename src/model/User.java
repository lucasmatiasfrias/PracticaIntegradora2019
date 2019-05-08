package model;

public class User {

	private int file;
	private String firstname;
	private String lastname;
	private String email;
	private String city;
		
	public User(int file, String firstname, String lastname, String email, String city) {
		this.file = file;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.city = city;
	}

	public int getFile() {
		return file;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}	
}
