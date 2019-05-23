package persistence.dal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Registration;
import model.Subject;
import model.User;

public class RegistrationDAL extends CRUD<Registration> {
	private static RegistrationDAL dal;
	private Connection conn;

	private RegistrationDAL(Connection conn) {
		this.conn = conn;
		dbTableName = "registration";
		selectAllQuery = "SELECT id, subjectid, studentfile FROM " + dbTableName;
		selectByIdQuery = selectAllQuery + " WHERE subjectid=?";
		insertQuery = "INSERT INTO " + dbTableName + " (id, subjectid, studentfile) VALUES (?,?,?)";
		updateQuery = "UPDATE " + dbTableName + " SET subjectid=?, studentfile=? WHERE id=?";
		deleteQuery = "DELETE FROM " + dbTableName + " WHERE id=?";
	}

	public static RegistrationDAL getDAL(Connection conn) throws SQLException {
		if (dal == null || dal.conn.isClosed())
			dal = new RegistrationDAL(conn);
		return dal;
	}

	public List<Registration> getBySubjectId(Integer id) throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(this.selectByIdQuery);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	public List<Registration> getAll() throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(this.selectAllQuery);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	private Registration fromRsToDto(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		Integer studentFile = rs.getInt("studentfile");
		Integer subjectId = rs.getInt("subjectid");
		List<User> u = UserDAL.getDAL(this.conn).getById(studentFile);
		List<Subject> s = SubjectDAL.getDAL(this.conn).getById(subjectId);
		if (!u.isEmpty() && !s.isEmpty())
			return new Registration(s.get(0), u.get(0));
		else
			return null;
	}

	@Override
	public int create(Registration t) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Registration t) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Registration newT) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Registration> getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
