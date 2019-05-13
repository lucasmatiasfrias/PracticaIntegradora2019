package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.UserDTO;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnection;
import persistence.dao.CRUD;
import persistence.dao.UserDAL;

public class UsersService {

	public static List<UserDTO> getUsers() throws IOException, ClassNotFoundException, SQLException {
		DBConnection conn = new DBConnection(ConnectionPropertiesLoader.load());
		CRUD<UserDTO> dal = UserDAL.getUserDAL(conn);
		List<UserDTO> users= dal.getAll();
		return users;
	}

	public static void addUser(HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		UserDTO user = new UserDTO(Integer.parseInt(request.getParameter("legajo")),Integer.parseInt(request.getParameter("dni")) ,request.getParameter("nombre"),
				request.getParameter("apellido"), request.getParameter("email"),request.getParameter("genero"));
		DBConnection conn = new DBConnection(ConnectionPropertiesLoader.load());
		CRUD<UserDTO> dal = UserDAL.getUserDAL(conn);
		dal.create(user);
	}
}
