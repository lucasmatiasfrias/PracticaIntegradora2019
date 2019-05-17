package model;

import dto.SubjectDTO;

public class Subject {
	private Integer id;
	private String description;

	public Subject(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Subject(SubjectDTO subject) {
		this.id=Integer.valueOf(subject.getId());
		this.description=subject.getDescription();
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public SubjectDTO toDTO() {
		return new SubjectDTO(this.id.toString(), this.description);
	}

}
