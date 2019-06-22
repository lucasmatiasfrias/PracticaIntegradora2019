package persistence.dal.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface I_CRUD<T> { //Interface padre de los I_DAL

	public List<T> getAll() throws SQLException, ClassNotFoundException, IOException;

	public List<T> getById(Integer id) throws SQLException, ClassNotFoundException, IOException;

	public int create(T t) throws SQLException, ClassNotFoundException, IOException;

	public int delete(T t) throws SQLException, ClassNotFoundException, IOException;

	public int update(T newT) throws SQLException, ClassNotFoundException, IOException;

}
