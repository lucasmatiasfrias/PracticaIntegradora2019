package dto;

public class UserDTO {

	private int file;
	private String firtname;
	private String lastname;
	private String email;
	private String city;
	
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public String getFirtname() {
		return firtname;
	}
	public void setFirtname(String firtname) {
		this.firtname = firtname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
