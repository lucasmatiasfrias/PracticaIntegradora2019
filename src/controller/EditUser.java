package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import persistence.dao.UserDAL;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.valueOf(request.getParameter("file"));
		try {
			request.setAttribute("ALUMNO", UserDAL.getUserDAL().get(id).get(0));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("STATUS", request.getParameter("status"));
		getServletContext().getRequestDispatcher("/JSP/alumno_modificacion.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("legajo"));
		int r=0;
		try {
			UserDTO user = new UserDTO(Integer.parseInt(request.getParameter("legajo")),Integer.parseInt(request.getParameter("dni")) ,request.getParameter("nombre"),
					request.getParameter("apellido"), request.getParameter("email"),request.getParameter("genero"));
			r=UserDAL.getUserDAL().update(id,user);
		} catch (IOException e) {
			response.getWriter()
					.append("Error con el archivo de configuración de la base de datos\n" + e.getLocalizedMessage());
		} catch (ClassNotFoundException | SQLException e) {
			response.getWriter().append("Error con la base de datos\n" + e.getLocalizedMessage());
		}
		response.sendRedirect("./Alumnos?status="+r);
	}
}
