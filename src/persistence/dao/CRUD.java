package persistence.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import persistence.connection.ConnectionProperties;
import persistence.connection.DBConnection;

public abstract class CRUD<T> {
	protected Connection conn;
	protected ConnectionProperties prop;

	public CRUD() throws IOException, ClassNotFoundException, SQLException {
		prop = ConnectionProperties.getConnProp();
		conn = new DBConnection(prop).getConnection();
	}

	public abstract List<T> getAll();

	public abstract void create(T t);

	public abstract void delete(int id);

	public abstract void update(int id, T newT);

	public abstract T get(int id);

}
