package persistence.dao;

import static persistence.connection.ConnectionProperties.getConnProp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import persistence.connection.DBConnection;

public final class UserDAL extends CRUD<UserDTO> {
	
	private static UserDAL dal;

	private UserDAL() {
		tableName = "student";
		selectAll = "SELECT file, dni, firstname, lastname, email, gender FROM " + tableName;
		selectByFile = selectAll + " WHERE file=?";
		insert = "INSERT INTO " + tableName + " (file, dni, firstname, lastname, email, gender) VALUES (?,?,?,?,?,?)";
		update = "UPDATE " + tableName + " SET dni=?, firstname=?, lastname=?, email=?, gender=? WHERE file=?";
		delete = "DELETE FROM " + tableName + " WHERE file=?";
	}

	public static UserDAL getUserDAL() {
		if(dal==null)
			dal=new UserDAL();
		return dal;
	}
	
	public List<UserDTO> get(int id) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = new DBConnection(getConnProp()).getConnection();
		List<UserDTO> ts = new ArrayList<UserDTO>();
		PreparedStatement ps = conn.prepareStatement(this.selectByFile);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ts.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		conn.close();
		return ts;
	}

	public List<UserDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
		Connection conn = new DBConnection(getConnProp()).getConnection();
		List<UserDTO> ts = new ArrayList<UserDTO>();
		PreparedStatement ps = conn.prepareStatement(this.selectAll);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ts.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		conn.close();
		return ts;
	}

	public int create(UserDTO t) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = new DBConnection(getConnProp()).getConnection();
		PreparedStatement ps = conn.prepareStatement(this.insert);
		fromDtoToInsertStm(t, ps);
		int r = ps.executeUpdate();
		ps.close();
		conn.close();
		return r;
	}

	public int delete(int id) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = new DBConnection(getConnProp()).getConnection();
		PreparedStatement ps = conn.prepareStatement(this.delete);
		ps.setInt(1, id);
		int r = ps.executeUpdate();
		ps.close();
		conn.close();
		return r;
	}

	public int update(int id, UserDTO newT) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = new DBConnection(getConnProp()).getConnection();
		PreparedStatement ps = conn.prepareStatement(this.update);
		fromDtoToUpdateStm(id, newT, ps);
		int r = ps.executeUpdate();
		ps.close();
		conn.close();
		return r;
	}

	@Override
	protected UserDTO fromRsToDto(ResultSet rs) throws SQLException {
		Integer file = rs.getInt("file");
		Integer dni = rs.getInt("dni");
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		String email = rs.getString("email");
		String gender = rs.getString("gender");
		return new UserDTO(file, dni, firstname, lastname, email, gender);
	}

	@Override
	protected void fromDtoToInsertStm(UserDTO t, PreparedStatement ps) throws SQLException {
		ps.setInt(1, t.getFile());
		ps.setInt(2, t.getDni());
		ps.setString(3, t.getFirstname());
		ps.setString(4, t.getLastname());
		ps.setString(5, t.getEmail());
		ps.setString(6, t.getGender());
	}

	protected void fromDtoToUpdateStm(int id, UserDTO t, PreparedStatement ps) throws SQLException {
		ps.setInt(1, t.getDni());
		ps.setString(2, t.getFirstname());
		ps.setString(3, t.getLastname());
		ps.setString(4, t.getEmail());
		ps.setString(5, t.getGender());
		ps.setInt(6, id);
	}

}
