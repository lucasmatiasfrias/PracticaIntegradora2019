package dto;

public class UserDTO{
	
	public static String[] avaiableGenders= {"masculino", "femenino", "no declara","macho alfa lomo plateado"};
	
	private String file;
	private String dni;
	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	private String approvalPercentage;

	public UserDTO(String file, String dni, String firstname, String lastname, String email, String gender) {
		this.file = file;
		this.dni = dni;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.gender = gender;
	}
	
	public String getFile() {
		return file;
	}
	
	public String getDni() {
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
	
	public String getApprovalPercentage() {
		return approvalPercentage;
	}
	public void setApprovalPercentage(String approvalPercentage) {
		this.approvalPercentage = approvalPercentage;
	}
	
	@Override
	public String toString() {
		return "UserDTO [file=" + file + ", dni=" + dni + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", gender=" + gender + ", approvalPercentage=" + approvalPercentage + "]";
	}

}
