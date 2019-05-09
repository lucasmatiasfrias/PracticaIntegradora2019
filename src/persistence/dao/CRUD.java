package persistence.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class CRUD<T> {
	
	protected String tableName;
	protected String selectAll;
	protected String selectByFile;
	protected String delete;
	protected String update;
	protected String insert;

	public abstract List<T> get(int id) throws SQLException, ClassNotFoundException, IOException;
	
	public abstract List<T> getAll() throws SQLException, ClassNotFoundException, IOException;

	public abstract int create(T t) throws SQLException, ClassNotFoundException, IOException;

	public abstract int delete(int id) throws SQLException, ClassNotFoundException, IOException;

	public abstract int update(int id, T newT) throws SQLException, ClassNotFoundException, IOException;

	protected abstract T fromRsToDto(ResultSet rs) throws SQLException;

	protected abstract void fromDtoToInsertStm(T t, PreparedStatement ps) throws SQLException;
	
	protected abstract void fromDtoToUpdateStm(int id, T t, PreparedStatement ps) throws SQLException;

}
