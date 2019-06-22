package persistence.dal.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Subject;
import persistence.dal.interfaces.I_SubjectDAL;

public class SubjectDAL implements I_SubjectDAL {
	private static final String DB_TABLE_NAME = "subject";
	private static final String SELECT_ALL_QUERY = "SELECT id, description FROM " + DB_TABLE_NAME;
	private static final String SELECT_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE id=?";
	private static final String INSERT_QUERY = "INSERT INTO " + DB_TABLE_NAME + " (id, description) VALUES (?,?)";
	private static final String UPDATE_QUERY = "UPDATE " + DB_TABLE_NAME + " SET description=? WHERE id=?";
	private static final String DELETE_QUERY = "DELETE FROM " + DB_TABLE_NAME + " WHERE id=?";

	private static SubjectDAL dal;
	private Connection conn;

	private SubjectDAL(Connection conn) {
		this.conn = conn;
	}

	public static SubjectDAL getDAL(Connection conn) throws SQLException {
		if (dal == null || dal.conn.isClosed())
			dal = new SubjectDAL(conn);
		return dal;
	}

	@Override
	public List<Subject> getAll() throws SQLException, ClassNotFoundException, IOException {
		List<Subject> users = new ArrayList<Subject>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_ALL_QUERY);
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
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_ID_QUERY);
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
		PreparedStatement ps = this.conn.prepareStatement(INSERT_QUERY);
		ps.setInt(1, t.getId());
		ps.setString(2, t.getDescription());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int delete(Subject t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(DELETE_QUERY);
		ps.setInt(1, t.getId());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int update(Subject newT) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(UPDATE_QUERY);
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
