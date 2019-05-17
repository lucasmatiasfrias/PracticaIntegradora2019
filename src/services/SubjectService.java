package services;

import static services.validations.GenericValidator.isNumeric;
import static services.validations.SubjectValidator.isSubjectValid;
import static services.validations.UserValidator.isFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.SubjectDTO;
import model.Subject;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dal.CRUD;
import persistence.dal.SubjectDAL;

public class SubjectService {

	public static List<SubjectDTO> getSubjects() throws IOException, ClassNotFoundException, SQLException {
		Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
		CRUD<Subject> dal = SubjectDAL.getSubjectDAL(conn);
		List<Subject> subjects = dal.getAll();
		List<SubjectDTO> usersDTO = new ArrayList<SubjectDTO>();
		for (Subject subject : subjects) {
			usersDTO.add(subject.toDTO());
		}
		conn.close();
		return usersDTO;
	}

	public static List<SubjectDTO> getSubjectByCode(String file)
			throws IOException, ClassNotFoundException, SQLException {
		List<SubjectDTO> subjectDTO = new ArrayList<SubjectDTO>();
		if (isFile(file)) {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<Subject> dal = SubjectDAL.getSubjectDAL(conn);
			List<Subject> subjects = dal.getById(Integer.valueOf(file));
			for (Subject subject : subjects) {
				subjectDTO.add(subject.toDTO());
			}
			conn.close();
		}
		return subjectDTO;
	}

	public static boolean addSubject(SubjectDTO subject) throws IOException, ClassNotFoundException, SQLException {
		if (isSubjectValid(subject)) {
			Subject s = new Subject(subject);
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<Subject> dal = SubjectDAL.getSubjectDAL(conn);
			if (dal.getById(s.getId()).isEmpty()) {
				dal.create(s);
				return true;
			}
		}
		return false;
	}

	public static boolean deleteSubject(SubjectDTO subject) throws IOException, ClassNotFoundException, SQLException {
		boolean b = false;
		if (isNumeric(subject.getId())) {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<Subject> dal = SubjectDAL.getSubjectDAL(conn);
			List<Subject> subjects = dal.getById(Integer.valueOf(subject.getId()));
			if (!subjects.isEmpty()) {
				dal.delete(subjects.get(0));
				b = true;
			}
			conn.close();
		}
		return b;
	}

	public static boolean updateUser(SubjectDTO subject) throws ClassNotFoundException, SQLException, IOException {
		boolean b = false;
		if (isSubjectValid(subject)) {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<Subject> dal = SubjectDAL.getSubjectDAL(conn);
			List<Subject> subjects = dal.getById(Integer.valueOf(subject.getId()));
			if (!subjects.isEmpty()) {
				b = dal.update(new Subject(subject)) != 0;
			}
			conn.close();
		}
		return b;
	}

}
