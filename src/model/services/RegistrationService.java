package model.services;

import static model.services.ServiceOpertationResultType.Error;
import static model.services.ServiceOpertationResultType.Success;
import static model.validations.GenericValidator.isNumeric;

import java.sql.Connection;
import java.util.List;

import dto.RegistrationDTO;
import model.Registration;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dal.CRUD;
import persistence.dal.RegistrationDAL;

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

}
