package persistence.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import persistence.connection.DBConnection;

public final class UserDAL extends CRUD<UserDTO> {
	
	private static UserDAL dal;
	private DBConnection conn;

	private UserDAL(DBConnection conn) {
		this.conn=conn;
		tableNameQuery = "student";
		selectAllQuery = "SELECT file, dni, firstname, lastname, email, gender FROM " + tableNameQuery;
		selectByIdQuery = selectAllQuery + " WHERE file=?";
		insertQuery = "INSERT INTO " + tableNameQuery + " (file, dni, firstname, lastname, email, gender) VALUES (?,?,?,?,?,?)";
		updateQuery = "UPDATE " + tableNameQuery + " SET dni=?, firstname=?, lastname=?, email=?, gender=? WHERE file=?";
		deleteQuery = "DELETE FROM " + tableNameQuery + " WHERE file=?";
	}

	public static UserDAL getUserDAL(DBConnection conn) {
		if(dal==null)
			dal=new UserDAL(conn);
		return dal;
	}
	
	public List<UserDTO> get(int id) throws SQLException, ClassNotFoundException, IOException {
		List<UserDTO> ts = new ArrayList<UserDTO>();
		PreparedStatement ps = this.conn.getConnection().prepareStatement(this.selectByIdQuery);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ts.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return ts;
	}

	public List<UserDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
		List<UserDTO> ts = new ArrayList<UserDTO>();
		PreparedStatement ps = this.conn.getConnection().prepareStatement(this.selectAllQuery);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ts.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return ts;
	}

	public int create(UserDTO t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.getConnection().prepareStatement(this.insertQuery);
		fromDtoToInsertStm(t, ps);
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int delete(int id) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.getConnection().prepareStatement(this.deleteQuery);
		ps.setInt(1, id);
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	public int update(int id, UserDTO newT) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.getConnection().prepareStatement(this.updateQuery);
		fromDtoToUpdateStm(id, newT, ps);
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	private UserDTO fromRsToDto(ResultSet rs) throws SQLException {
		Integer file = rs.getInt("file");
		Integer dni = rs.getInt("dni");
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		String email = rs.getString("email");
		String gender = rs.getString("gender");
		return new UserDTO(file, dni, firstname, lastname, email, gender);
	}

	private void fromDtoToInsertStm(UserDTO t, PreparedStatement ps) throws SQLException {
		ps.setInt(1, t.getFile());
		ps.setInt(2, t.getDni());
		ps.setString(3, t.getFirstname());
		ps.setString(4, t.getLastname());
		ps.setString(5, t.getEmail());
		ps.setString(6, t.getGender());
	}

	private void fromDtoToUpdateStm(int id, UserDTO t, PreparedStatement ps) throws SQLException {
		ps.setInt(1, t.getDni());
		ps.setString(2, t.getFirstname());
		ps.setString(3, t.getLastname());
		ps.setString(4, t.getEmail());
		ps.setString(5, t.getGender());
		ps.setInt(6, id);
	}

}
