package persistence.dal.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Registration;

public interface I_RegistrationDAL extends I_CRUD<Registration> {

	public List<Registration> getBySubjectId(Integer subjectId)
			throws SQLException, ClassNotFoundException, IOException;

	public List<Registration> getByStudentFile(Integer studentFile)
			throws SQLException, ClassNotFoundException, IOException;

	public List<Registration> get(Registration r) throws SQLException, ClassNotFoundException, IOException;

}
