package persistence.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAL extends CRUD<User> {

	private final String SELECT_ALL_USERS = "SELECT id,firstname, lastname, email, city FROM mdl_user";
	private final String INSERT_USER = "INSERT INTO mdl_user (id,firstname,lastname,email,city) VALUES (?,?,?,?,?)";

	public UserDAL() throws IOException, ClassNotFoundException, SQLException {
		super();
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("email"), rs.getString("city")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void create(User alumno) {
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_USER);
			ps.setInt(1, alumno.getFile());
			ps.setString(2, alumno.getFirstname());
			ps.setString(3, alumno.getLastname());
			ps.setString(4, alumno.getEmail());
			ps.setString(5, alumno.getCity());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int id, User newT) {
		// TODO Auto-generated method stub

	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
