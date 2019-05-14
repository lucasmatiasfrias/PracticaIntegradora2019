package services;

import static services.UserValidator.isDni;
import static services.UserValidator.isFile;
import static services.UserValidator.isGender;
import static services.UserValidator.isName;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import model.User;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dal.CRUD;
import persistence.dal.UserDAL;

public class UsersService {

	public static List<UserDTO> getUsers() throws IOException, ClassNotFoundException, SQLException {
		Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
		CRUD<User> dal = UserDAL.getUserDAL(conn);
		List<User> users = dal.getAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User user : users) {
			usersDTO.add(user.toDTO());
		}
		conn.close();
		return usersDTO;
	}
	
	public static List<UserDTO> getUserByFile(String file) throws IOException, ClassNotFoundException, SQLException {
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		if(isFile(file)) {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<User> dal = UserDAL.getUserDAL(conn);
			List<User> users = dal.getById(Integer.valueOf(file));
			for (User user : users) {
				usersDTO.add(user.toDTO());
			}
			conn.close();
		}
		return usersDTO;
	}

	public static boolean addUser(UserDTO user) throws IOException, ClassNotFoundException, SQLException {
		if (isUserValid(user)) {
			User u = new User(user);
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<User> dal = UserDAL.getUserDAL(conn);
			if (dal.getById(u.getFile()).isEmpty()) {
				dal.create(u);
				return true;
			}
		}
		return false;
	}
	
	public static boolean deleteUser(UserDTO user) throws IOException, ClassNotFoundException, SQLException {
		boolean b=false;
		if(isFile(user.getFile())) {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<User> dal = UserDAL.getUserDAL(conn);
			List<User> users = dal.getById(Integer.valueOf(user.getFile()));
			if(!users.isEmpty()) {
				dal.delete(users.get(0));
				b=true;
			}
			conn.close();
		}
		return b;
	}

	public static boolean updateUser(UserDTO user) throws ClassNotFoundException, SQLException, IOException {
		boolean b=false;
		if(isFile(user.getFile())) {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<User> dal = UserDAL.getUserDAL(conn);
			List<User> users = dal.getById(Integer.valueOf(user.getFile()));
			if(!users.isEmpty()) {
				b=dal.delete(new User(user))!=0;
			}
			conn.close();
		}
		return b;
	}
	
	private static boolean isUserValid(UserDTO user) {
		return isFile(user.getFile()) && isDni(user.getDni()) && isName(user.getFirstname())
				&& isName(user.getLastname()) && isGender(user.getGender());
	}

}
