package dto;

public class RegistrationDTO {

	private String id;
	private SubjectDTO subject;
	private UserDTO user;

	public RegistrationDTO(String id, SubjectDTO subjcet, UserDTO user) {
		this.id = id;
		this.subject = subjcet;
		this.user = user;
	}

	public SubjectDTO getSubjcet() {
		return subject;
	}

	public UserDTO getUser() {
		return user;
	}
	
	public String getId() {
		return this.id;
	}
}
