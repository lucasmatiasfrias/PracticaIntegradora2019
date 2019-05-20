package model.services;

import static model.services.ServiceOpertationResultType.Error;
import static model.services.ServiceOpertationResultType.Success;
import static model.services.validations.GenericValidator.isNumeric;
import static model.services.validations.SubjectValidator.isSubjectValid;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dto.SubjectDTO;
import model.Subject;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dal.CRUD;
import persistence.dal.SubjectDAL;

public class SubjectService {

	public static ServiceOperationResult<SubjectDTO> getSubjects() {
		try {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<Subject> dal = SubjectDAL.getDAL(conn);
			List<Subject> subjects = dal.getAll();
			List<SubjectDTO> subjectDTO = new ArrayList<SubjectDTO>();
			for (Subject subject : subjects) {
				subjectDTO.add(subject.toDTO());
			}
			conn.close();
			return new ServiceOperationResult<SubjectDTO>(Success, "Se leyeron los usuarios correctamente", subjectDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceOperationResult<SubjectDTO>(Error, e.getLocalizedMessage());
		}
	}

	public static ServiceOperationResult<SubjectDTO> getSubjectByCode(String code) {
		ServiceOperationResult<SubjectDTO> result = new ServiceOperationResult<SubjectDTO>(Error, "");
		if (isNumeric(code)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<Subject> dal = SubjectDAL.getDAL(conn);
				List<Subject> subjects = dal.getById(Integer.valueOf(code));
				for (Subject subject : subjects) {
					result.getQueryResluts().add(subject.toDTO());
				}
				result.setResultType(Success);
				result.setResultMsg("Se leyeron los usuarios correctamente");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("El Código de Materia ingresado es inválido");
		return result;
	}

	public static ServiceOperationResult<SubjectDTO> addSubject(SubjectDTO subject) {
		ServiceOperationResult<SubjectDTO> result = new ServiceOperationResult<SubjectDTO>(Error, "");
		if (isSubjectValid(subject)) {
			Subject s = new Subject(subject);
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<Subject> dal = SubjectDAL.getDAL(conn);
				if (dal.getById(s.getId()).isEmpty() && dal.create(s) > 0) {
					result.setResultType(Success);
					result.setResultMsg("La materia se agregó correctamente");
				} else
					result.setResultMsg("El código de materia ingresado ya existe");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

	public static ServiceOperationResult<SubjectDTO> deleteSubject(String code) {
		ServiceOperationResult<SubjectDTO> result = new ServiceOperationResult<SubjectDTO>(Error, "");
		if (isNumeric(code)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<Subject> dal = SubjectDAL.getDAL(conn);
				List<Subject> existing = dal.getById(Integer.valueOf(code));
				if (!existing.isEmpty() && dal.delete(existing.get(0)) > 0) {
					result.setResultType(Success);
					result.setResultMsg("La materia se eliminó correctamente");
				} else
					result.setResultMsg("El código de materia ingresado NO existe");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

	public static ServiceOperationResult<SubjectDTO> updateSubject(SubjectDTO subject) {
		ServiceOperationResult<SubjectDTO> result = new ServiceOperationResult<SubjectDTO>(Error, "");
		if (isSubjectValid(subject)) {
			Subject s = new Subject(subject);
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<Subject> dal = SubjectDAL.getDAL(conn);
				if (!dal.getById(s.getId()).isEmpty() && dal.update(s) > 0) {
					result.setResultType(Success);
					result.setResultMsg("La materia se actualizó correctamente");
				} else
					result.setResultMsg("El código de materia ingresado ya existe");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

}
