package dto;

public class RegistrationDTO {

	private SubjectDTO subject;
	private UserDTO user;

	public RegistrationDTO(SubjectDTO subjcet, UserDTO user) {
		this.subject = subjcet;
		this.user = user;
	}

	public SubjectDTO getSubjcet() {
		return subject;
	}

	public UserDTO getUser() {
		return user;
	}
}
