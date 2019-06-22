package model;

import dto.RegistrationDTO;

public class Registration {
	private Integer id;
	private Subject subject;
	private User user;

	public Registration(Integer id, Subject subject, User user) {
		this.id = id;
		this.subject = subject;
		this.user = user;
	}
	
	public Registration(Subject subject, User user) {
		this(0,subject, user);
	}

	public Subject getSubjcet() {
		return subject;
	}

	public User getUser() {
		return user;
	}

	public int getId() {
		return id;
	}
	
	public RegistrationDTO toDTO() {
		return new RegistrationDTO(this.id.toString(), this.subject.toDTO(), this.user.toDTO());
	}

}
