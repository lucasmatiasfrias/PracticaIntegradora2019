package persistence.dal.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.User;

public interface I_UserDAL extends I_CRUD<User>{

	public List<User> getByDNI(Integer dni) throws SQLException, ClassNotFoundException, IOException;
}
