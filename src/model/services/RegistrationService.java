package model.services;

import static model.services.ServiceOpertationResultType.Error;
import static model.services.ServiceOpertationResultType.Success;
import static model.validations.GenericValidator.areNumeric;
import static model.validations.GenericValidator.isNumeric;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RegistrationDTO;
import model.Registration;
import model.Subject;
import model.User;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dal.CRUD;
import persistence.dal.RegistrationDAL;
import persistence.dal.SubjectDAL;
import persistence.dal.UserDAL;

public class RegistrationService {

	public static ServiceOperationResult<RegistrationDTO> getRegistrationsBySubject(String selectedSubject) {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		if (isNumeric(selectedSubject)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				RegistrationDAL dal = RegistrationDAL.getDAL(conn);
				List<Registration> subjects = dal.getBySubjectId(Integer.valueOf(selectedSubject));
				if (!subjects.isEmpty()) {
					for (Registration subject : subjects) {
						result.getQueryResults().add(subject.toDTO());
					}
					result.setResultType(Success);
					result.setResultMsg("Se leyeron los usuarios correctamente");
				} else
					result.setResultMsg("No existe la materia con código " + selectedSubject);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("El Código de Materia ingresado es inválido");
		return result;
	}

	public static ServiceOperationResult<RegistrationDTO> getRegistrations() {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		try {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<Registration> dal = RegistrationDAL.getDAL(conn);
			List<Registration> subjects = dal.getAll();
			if (!subjects.isEmpty()) {
				for (Registration subject : subjects) {
					result.getQueryResults().add(subject.toDTO());
				}
				result.setResultType(Success);
				result.setResultMsg("Se leyeron las inscripciones correctamente");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultMsg(e.getLocalizedMessage());
		}
		return result;
	}

	public static ServiceOperationResult<RegistrationDTO> addRegistration(String studentFile, List<String> subjectsIds) {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		if (isNumeric(studentFile) && areNumeric(subjectsIds)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				UserDAL userDal=UserDAL.getDAL(conn);
				SubjectDAL subjectDal=SubjectDAL.getDAL(conn);
				List<User> u=userDal.getById(Integer.valueOf(studentFile));
				List<Subject> s=getSubjectsByIds(subjectDal, subjectsIds);
				if (!u.isEmpty() && !s.isEmpty()) {
					RegistrationDAL dal=RegistrationDAL.getDAL(conn);
					for (Subject subject : s) {
						int n=dal.create(new Registration(subject, u.get(0)));
						System.out.println(n);
					}
					result.setResultType(Success);
					result.setResultMsg("La inscripción se agregó correctamente!");
				} else
					result.setResultMsg("No se puede crear la relación entre el alumno y materia indicado");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

	private static List<Subject> getSubjectsByIds(SubjectDAL dal, List<String> subjectsIds)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		List<Subject> subjects= new ArrayList<Subject>();
		for (String string : subjectsIds) {
			List<Subject> s=dal.getById(Integer.valueOf(string));
			if (s.isEmpty())
				subjects.add(s.get(0));
		}
		return subjects;
	}

}
