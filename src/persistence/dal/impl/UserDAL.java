package persistence.dal.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import persistence.dal.interfaces.I_UserDAL;

public final class UserDAL implements I_UserDAL {
	private static final String DB_TABLE_NAME = "student";
	private static final String SELECT_ALL_QUERY = "SELECT file, dni, firstname, lastname, email, gender FROM "
			+ DB_TABLE_NAME;
	private static final String SELECT_BY_FILE_QUERY = SELECT_ALL_QUERY + " WHERE file=?";
	private static final String SELECT_BY_DNI_QUERY = SELECT_ALL_QUERY + " WHERE dni=?";
	private static final String INSERT_QUERY = "INSERT INTO " + DB_TABLE_NAME
			+ " (file, dni, firstname, lastname, email, gender) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE " + DB_TABLE_NAME
			+ " SET dni=?, firstname=?, lastname=?, email=?, gender=? WHERE file=?";
	private static final String DELETE_QUERY = "DELETE FROM " + DB_TABLE_NAME + " WHERE file=?";

	private static UserDAL dal;
	private Connection conn;

	private UserDAL(Connection conn) {
		this.conn = conn;
	}

	public static UserDAL getDAL(Connection conn) throws SQLException {
		if (dal == null || dal.conn.isClosed())
			dal = new UserDAL(conn);
		return dal;
	}

	public List<User> getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
		List<User> users = new ArrayList<User>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_FILE_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			users.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return users;
	}

	@Override
	public List<User> getByDNI(Integer dni) throws SQLException, ClassNotFoundException, IOException {
		List<User> users = new ArrayList<User>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_DNI_QUERY);
		ps.setInt(1, dni);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			users.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return users;
	}

	public List<User> getAll() throws SQLException, ClassNotFoundException, IOException {
		List<User> users = new ArrayList<User>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			users.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return users;
	}

	public int create(User t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(INSERT_QUERY);
		ps.setInt(1, t.getFile());
		ps.setInt(2, t.getDni());
		ps.setString(3, t.getFirstname());
		ps.setString(4, t.getLastname());
		ps.setString(5, t.getEmail());
		ps.setString(6, t.getGender());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int delete(User t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(DELETE_QUERY);
		ps.setInt(1, t.getFile());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int update(User newT) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(UPDATE_QUERY);
		ps.setInt(1, newT.getDni());
		ps.setString(2, newT.getFirstname());
		ps.setString(3, newT.getLastname());
		ps.setString(4, newT.getEmail());
		ps.setString(5, newT.getGender());
		ps.setInt(6, newT.getFile());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	private User fromRsToDto(ResultSet rs) throws SQLException {
		Integer file = rs.getInt("file");
		Integer dni = rs.getInt("dni");
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		String email = rs.getString("email");
		String gender = rs.getString("gender");
		return new User(file, dni, firstname, lastname, email, gender);
	}

}
