package persistence.dal.impl;

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
import persistence.dal.interfaces.I_RegistrationDAL;

public class RegistrationDAL implements I_RegistrationDAL {

	private static final String DB_TABLE_NAME = "registration";
	private static final String SELECT_ALL_QUERY = "SELECT id, subjectid, studentfile FROM " + DB_TABLE_NAME;
	private static final String SELECT_BY_FK_IDS = SELECT_ALL_QUERY + " WHERE subjectid=? AND studentfile=?";
	private static final String SELECT_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE id=?";
	private static final String SELECT_BY_SUBJECT_ID_QUERY = SELECT_ALL_QUERY + " WHERE subjectid=?";
	private static final String SELECT_BY_STUDENT_FILE_QUERY = SELECT_ALL_QUERY + " WHERE studentfile=?";
	private static final String INSERT_QUERY = "INSERT INTO " + DB_TABLE_NAME
			+ " (subjectid, studentfile) VALUES (?,?)";
	private static final String DELETE_QUERY = "DELETE FROM " + DB_TABLE_NAME + " WHERE id=?";

	private static RegistrationDAL dal;
	private Connection conn;

	private RegistrationDAL(Connection conn) {
		this.conn = conn;
	}

	public static RegistrationDAL getDAL(Connection conn) throws SQLException {
		if (dal == null || dal.conn.isClosed())
			dal = new RegistrationDAL(conn);
		return dal;
	}

	@Override
	public List<Registration> getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	@Override
	public List<Registration> getBySubjectId(Integer subjectId)
			throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_SUBJECT_ID_QUERY);
		ps.setInt(1, subjectId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	@Override
	public List<Registration> getByStudentFile(Integer studentFile)
			throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_STUDENT_FILE_QUERY);
		ps.setInt(1, studentFile);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	@Override
	public List<Registration> get(Registration r) throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_BY_FK_IDS);
		ps.setInt(1, r.getSubjcet().getId());
		ps.setInt(2, r.getUser().getFile());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	@Override
	public List<Registration> getAll() throws SQLException, ClassNotFoundException, IOException {
		List<Registration> regs = new ArrayList<Registration>();
		PreparedStatement ps = this.conn.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			regs.add(fromRsToDto(rs));
		}
		rs.close();
		ps.close();
		return regs;
	}

	@Override
	public int create(Registration t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(INSERT_QUERY);
		ps.setInt(1, t.getSubjcet().getId());
		ps.setInt(2, t.getUser().getFile());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	@Override
	public int delete(Registration t) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.conn.prepareStatement(DELETE_QUERY);
		ps.setInt(1, t.getId());
		int r = ps.executeUpdate();
		ps.close();
		return r;
	}

	@Override
	public int update(Registration newT) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	private Registration fromRsToDto(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		Integer id= rs.getInt("id");
		Integer studentFile = rs.getInt("studentfile");
		Integer subjectId = rs.getInt("subjectid");
		List<User> u = UserDAL.getDAL(this.conn).getById(studentFile);
		List<Subject> s = SubjectDAL.getDAL(this.conn).getById(subjectId);
		if (!u.isEmpty() && !s.isEmpty())
			return new Registration(id, s.get(0), u.get(0));
		else
			return null;
	}
}
