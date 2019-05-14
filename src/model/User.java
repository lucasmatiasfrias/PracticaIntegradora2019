package model;

import dto.UserDTO;

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

	public User(UserDTO u) {
		this.file = Integer.valueOf(u.getFile());
		this.dni = Integer.valueOf(u.getDni());
		this.firstname = u.getFirstname();
		this.lastname = u.getLastname();
		this.email = u.getEmail();
		this.gender = u.getGender();
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

	public UserDTO toDTO() {
//		String s1=this.file.toString();
//		String s2=this.dni.toString();
//		String s3=this.firstname;
//		String s4=this.lastname;
//		String s5=this.email;
//		String s6=this.gender;
//		UserDTO u= new UserDTO(s1, s2, s3, s4, s5, s6);
//		return u;
		return new UserDTO(this.file.toString(), this.dni.toString(), this.firstname, this.lastname, this.email,
				this.gender);
	}
}
