package services;

import static services.RegexValidator.isAlphabeticalString;
import static services.RegexValidator.isNumeric;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.UserDTO;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dao.CRUD;
import persistence.dao.UserDAL;

public class UsersService {

	public static List<UserDTO> getUsers() throws IOException, ClassNotFoundException, SQLException {
		Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
		CRUD<UserDTO> dal = UserDAL.getUserDAL(conn);
		List<UserDTO> users= dal.getAll();
		return users;
	}

	public static void addUser(UserDTO user) throws IOException, ClassNotFoundException, SQLException {
		isUserValid(user);
		
		DBConnection conn = new DBConnection(ConnectionPropertiesLoader.load());
		CRUD<UserDTO> dal = UserDAL.getUserDAL(conn);
		dal.create(user);
	}

	private static boolean isUserValid(UserDTO user) {
		return isNumeric(user.getFile())&&isNumeric(user.getDni())&&isAlphabeticalString(user.getFirstname())&&isAlphabeticalString(user.getLastname())&&isAlphabeticalString(user.);
	}
	
}
