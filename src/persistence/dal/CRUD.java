package persistence.dal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class CRUD<T> {

	protected String dbTableName;

	protected String selectAllQuery;
	protected String selectByIdQuery;
	protected String insertQuery;
	protected String deleteQuery;
	protected String updateQuery;

	public abstract List<T> getAll() throws SQLException, ClassNotFoundException, IOException;

	public abstract List<T> getById(Integer id) throws SQLException, ClassNotFoundException, IOException;

	public abstract int create(T t) throws SQLException, ClassNotFoundException, IOException;

	public abstract int delete(T t) throws SQLException, ClassNotFoundException, IOException;

	public abstract int update(T newT) throws SQLException, ClassNotFoundException, IOException;

}
