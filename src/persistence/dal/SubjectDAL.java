package persistence.dal;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Subject;
import persistence.dal.CRUD;

public class SubjectDAL extends CRUD<Subject>{
	private static SubjectDAL dal;
	private Connection conn;
	
	private SubjectDAL(Connection conn) {
		this.conn=conn;
		dbTableName = "subject";
		selectAllQuery = "SELECT id, description FROM " + dbTableName;
		selectByIdQuery = selectAllQuery + " WHERE id=?";
		insertQuery = "INSERT INTO " + dbTableName + " (id, description) VALUES (?,?)";
		updateQuery = "UPDATE " + dbTableName + " SET description=? WHERE id=?";
		deleteQuery = "DELETE FROM " + dbTableName + " WHERE id=?";
	}

	public static SubjectDAL getDAL(Connection conn) throws SQLException {
		if(dal==null||dal.conn.isClosed())
			dal=new SubjectDAL(conn);
		return dal;
	}

	@Override
	public List<Subject> getAll() throws SQLException, ClassNotFoundException, IOException {
		List<Subject> users = new ArrayList<Subject>();
		PreparedStatement ps = this.conn.prepareStatement(this.selectAllQuery);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			users.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return users;
	}

	public List<Subject> getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
		List<Subject> subjects = new ArrayList<Subject>();
		PreparedStatement ps = this.conn.prepareStatement(this.selectByIdQuery);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			subjects.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return subjects;
	}

	public int create(Subject t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(this.insertQuery);
		ps.setInt(1, t.getId());
		ps.setString(2, t.getDescription());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int delete(Subject t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(this.deleteQuery);
		ps.setInt(1, t.getId());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int update(Subject newT) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(this.updateQuery);
		ps.setString(1, newT.getDescription());
		ps.setInt(2, newT.getId());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}
	
	private Subject fromRsToDto(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		String desc = rs.getString("description");
		return new Subject(id, desc);
	}

}
