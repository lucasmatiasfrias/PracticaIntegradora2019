package dto;

public class RegistrationDTO {

	private String subject;
	private String studentFile;
	private String studentDNI;
	private String studentFirstname;
	private String studentLastname;
	
	public RegistrationDTO(String subject, String studentFile, String studentDNI, String studentFirstname,
			String studentLastname) {
		super();
		this.subject = subject;
		this.studentFile = studentFile;
		this.studentDNI = studentDNI;
		this.studentFirstname = studentFirstname;
		this.studentLastname = studentLastname;
	}

	public String getSubject() {
		return subject;
	}

	public String getStudentFile() {
		return studentFile;
	}

	public String getStudentDNI() {
		return studentDNI;
	}

	public String getStudentFirstname() {
		return studentFirstname;
	}

	public String getStudentLastname() {
		return studentLastname;
	}
}
