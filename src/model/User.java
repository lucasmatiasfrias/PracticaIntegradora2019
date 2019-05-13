package model;

public class User {
	
	private Integer file;
	private Integer dni;
	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	private Double approvalPercentage;

	public User(Integer file, Integer dni, String firstname, String lastname, String email, String gender) {
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

	public Integer getDni() {
		return dni;
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

	public String getGender() {
		return gender;
	}

	public Double getApprovalPercentage() {
		return approvalPercentage;
	}

	public void setApprovalPercentage(Double approvalPercentage) {
		this.approvalPercentage = approvalPercentage;
	}

	@Override
	public String toString() {
		return "User [file=" + file + ", dni=" + dni + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", gender=" + gender + ", approvalPercentage=" + approvalPercentage + "]";
	}
}
