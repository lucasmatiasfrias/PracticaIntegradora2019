package dto;

public class UserDTO{
	
	private Integer file;
	private Integer dni;
	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	private Double approvalPercentage;

	public UserDTO() {
		
	}
	public UserDTO(Integer file, Integer dni, String firstname, String lastname, String email, String gender) {
		this.file = file;
		this.dni = dni;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.gender = gender;
	}
	
	public Integer getFile() {
		return file;
	}
	public void setFile(Integer file) {
		this.file = file;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getApprovalPercentage() {
		return approvalPercentage;
	}
	public void setApprovalPercentage(Double approvalPercentage) {
		this.approvalPercentage = approvalPercentage;
	}
	
	@Override
	public String toString() {
		return "UserDTO [file=" + file + ", dni=" + dni + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", gender=" + gender + ", approvalPercentage=" + approvalPercentage + "]";
	}

}
