package model.services;

import static model.services.ServiceOpertationResultType.Error;
import static model.services.ServiceOpertationResultType.Success;
import static model.validations.SubjectValidator.areSubjectsCodes;
import static model.validations.SubjectValidator.isCode;
import static model.validations.UserValidator.isFile;

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
import persistence.dal.impl.RegistrationDAL;
import persistence.dal.impl.SubjectDAL;
import persistence.dal.impl.UserDAL;
import persistence.dal.interfaces.I_RegistrationDAL;

public class RegistrationService {

	public static ServiceOperationResult<RegistrationDTO> getRegistrationsById(String id) {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		if (isCode(id)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				I_RegistrationDAL dal = RegistrationDAL.getDAL(conn);
				List<Registration> regs = dal.getById(new Integer(id));
				if (!regs.isEmpty()) {
					for (Registration reg : regs) {
						result.getQueryResults().add(reg.toDTO());
					}
					result.setResultType(Success);
					result.setResultMsg("Se leyeron las inscripciones correctamente");
				} else
					result.setResultMsg("No existe la inscripción con id " + id);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("El Id ingresado es inválido");
		return result;
	}

	public static ServiceOperationResult<RegistrationDTO> deleteRegistration(String id) {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		if (isCode(id)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				I_RegistrationDAL dal = RegistrationDAL.getDAL(conn);
				List<Registration> registrations = dal.getById(Integer.valueOf(id));
				if (!registrations.isEmpty()) {
					int r = dal.delete(registrations.get(0));
					if (r > 0) {
						result.setResultType(Success);
						result.setResultMsg("Se eliminó la inscripcion correctamente");
					} else
						result.setResultMsg("No existe la inscripción con id " + id);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("El Id ingresado es inválido");
		return result;
	}

	public static ServiceOperationResult<RegistrationDTO> getRegistrationsBySubject(String selectedSubject) {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		if (isCode(selectedSubject)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				List<Registration> subjects = RegistrationDAL.getDAL(conn)
						.getBySubjectId(Integer.valueOf(selectedSubject));
				if (!subjects.isEmpty()) {
					for (Registration subject : subjects) {
						result.getQueryResults().add(subject.toDTO());
					}
					result.setResultType(Success);
					result.setResultMsg("Se leyeron las inscripciones correctamente");
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
			I_RegistrationDAL dal = RegistrationDAL.getDAL(conn);
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

	public static ServiceOperationResult<RegistrationDTO> addRegistrations(String studentFile,
			List<String> subjectsIds) {
		ServiceOperationResult<RegistrationDTO> result = new ServiceOperationResult<RegistrationDTO>(Error, "");
		if (isFile(studentFile) && areSubjectsCodes(subjectsIds)) {
			try {
				result.setResultMsg("No se puede crear la relación entre el alumno y materia indicado");
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				List<User> users = UserDAL.getDAL(conn).getById(Integer.valueOf(studentFile));
				List<Subject> subjects = getSubjectsByIds(SubjectDAL.getDAL(conn), subjectsIds);
				if (!users.isEmpty() && !subjects.isEmpty()) {
					I_RegistrationDAL dal = RegistrationDAL.getDAL(conn);
					int affectedRows = 0;
					for (Subject subject : subjects) {
						Registration r = new Registration(subject, users.get(0));
						if (dal.get(r).isEmpty())
							affectedRows = dal.create(r);
					}
					if (affectedRows > 0) {
						result.setResultType(Success);
						result.setResultMsg("Una o más inscripciones se agregaron correctamente!");
					}
				}
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
		List<Subject> subjects = new ArrayList<Subject>();
		for (String string : subjectsIds) {
			List<Subject> s = dal.getById(Integer.valueOf(string));
			if (!s.isEmpty())
				subjects.add(s.get(0));
		}
		return subjects;
	}

}
