package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistence.dao.UserDAL;

/**
 * Servlet implementation class AlumnoAlta
 */
@WebServlet("/AlumnoAlta")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("STATUS", request.getParameter("status"));
		getServletContext().getRequestDispatcher("/JSP/alumno_alta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recordar controlar los datos que recibo de request

		User user = new User(Integer.parseInt(request.getParameter("legajo")), request.getParameter("nombre"),
				request.getParameter("apellido"), request.getParameter("email"),request.getParameter("city"));
		try {
			new UserDAL().create(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/PracticaIntegradora/Alumnos?status=1");
	}

}
