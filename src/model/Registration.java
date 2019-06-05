package model;

import dto.RegistrationDTO;

public class Registration {
	private Subject subject;
	private User user;

	public Registration(Subject subjcet, User user) {
		super();
		this.subject = subjcet;
		this.user = user;
	}

	public Subject getSubjcet() {
		return subject;
	}

	public User getUser() {
		return user;
	}

	public RegistrationDTO toDTO() {
		return new RegistrationDTO(this.subject.toDTO(), this.user.toDTO());
	}
}
