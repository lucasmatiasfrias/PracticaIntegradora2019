package persistence.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class CRUD<T> {
	
	protected String tableNameQuery;
	protected String selectAllQuery;
	protected String selectByIdQuery;
	protected String deleteQuery;
	protected String updateQuery;
	protected String insertQuery;

	public abstract List<T> get(int id) throws SQLException, ClassNotFoundException, IOException;
	
	public abstract List<T> getAll() throws SQLException, ClassNotFoundException, IOException;

	public abstract int create(T t) throws SQLException, ClassNotFoundException, IOException;

	public abstract int delete(int id) throws SQLException, ClassNotFoundException, IOException;

	public abstract int update(int id, T newT) throws SQLException, ClassNotFoundException, IOException;

}
