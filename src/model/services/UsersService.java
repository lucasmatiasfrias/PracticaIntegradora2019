package model.services;

import static model.services.ServiceOpertationResultType.Error;
import static model.services.ServiceOpertationResultType.Success;
import static model.validations.UserValidator.isFile;
import static model.validations.UserValidator.isUserValid;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import model.User;
import persistence.connection.ConnectionPropertiesLoader;
import persistence.connection.DBConnectionManager;
import persistence.dal.CRUD;
import persistence.dal.UserDAL;

public class UsersService {

	public static ServiceOperationResult<UserDTO> getUsers() {
		try {
			Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
			CRUD<User> dal = UserDAL.getDAL(conn);
			List<User> users = dal.getAll();
			List<UserDTO> usersDTO = new ArrayList<UserDTO>();
			for (User user : users) {
				usersDTO.add(user.toDTO());
			}
			conn.close();
			return new ServiceOperationResult<UserDTO>(Success, "Se leyeron los usuarios correctamente", usersDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceOperationResult<UserDTO>(Error, e.getLocalizedMessage());
		}
	}

	public static ServiceOperationResult<UserDTO> getUserByFile(String file) {
		ServiceOperationResult<UserDTO> result = new ServiceOperationResult<UserDTO>(Error, "");
		if (isFile(file)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<User> dal = UserDAL.getDAL(conn);
				List<User> users = dal.getById(Integer.valueOf(file));
				if(!users.isEmpty()) {
					for (User user : users) {
						result.getQueryResluts().add(user.toDTO());
					}
					result.setResultType(Success);
					result.setResultMsg("Se leyeron los usuarios correctamente");
				}else
					result.setResultMsg("No existe el usuario con legajo "+file);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("El Legajo ingresado es inválido");
		return result;
	}

	public static ServiceOperationResult<UserDTO> addUser(UserDTO user) {
		ServiceOperationResult<UserDTO> result = new ServiceOperationResult<UserDTO>(Error, "");
		if (isUserValid(user)) {
			User u = new User(user);
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<User> dal = UserDAL.getDAL(conn);
				if (dal.getById(u.getFile()).isEmpty() && dal.create(u) > 0) {
					result.setResultType(Success);
					result.setResultMsg("El usuario se agregó correctamente");
				} else
					result.setResultMsg("El legajo ingresado ya existe");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

	public static ServiceOperationResult<UserDTO> deleteUser(String file) {
		ServiceOperationResult<UserDTO> result = new ServiceOperationResult<UserDTO>(Error, "");
		if (isFile(file)) {
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<User> dal = UserDAL.getDAL(conn);
				List<User> existing=dal.getById(Integer.valueOf(file));
				if (!existing.isEmpty() && dal.delete(existing.get(0)) > 0) {
					result.setResultType(Success);
					result.setResultMsg("El usuario se eliminó correctamente");
				} else
					result.setResultMsg("El legajo ingresado NO existe");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

	public static ServiceOperationResult<UserDTO> updateUser(UserDTO user){
		ServiceOperationResult<UserDTO> result = new ServiceOperationResult<UserDTO>(Error, "");
		if (isUserValid(user)) {
			User u = new User(user);
			try {
				Connection conn = DBConnectionManager.getConnection(ConnectionPropertiesLoader.load());
				CRUD<User> dal = UserDAL.getDAL(conn);
				if (!dal.getById(u.getFile()).isEmpty() && dal.update(u) > 0) {
					result.setResultType(Success);
					result.setResultMsg("El usuario se actualizó correctamente");
				} else
					result.setResultMsg("El legajo ingresado ya existe");
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultMsg(e.getLocalizedMessage());
			}
		} else
			result.setResultMsg("Los datos ingresados son inválidos");
		return result;
	}

}
