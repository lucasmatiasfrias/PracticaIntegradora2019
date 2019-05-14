package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import services.UsersService;

@WebServlet("/AlumnoEditar")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			List<UserDTO> existing = UsersService.getUserByFile(request.getParameter("file"));
			if (existing.isEmpty()) {
				request.setAttribute("ALUMNO", existing.get(0));
				getServletContext().getRequestDispatcher("/alumno_modificacion.jsp").forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect("./JSP/error.jsp?msg=" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<UserDTO> existing = UsersService.getUserByFile(request.getParameter("file"));
			if (existing.isEmpty()) {
				UserDTO newUser=existing.get(0);
				newUser.setDni(request.getParameter("dni"));
				newUser.setFirstname(request.getParameter("nombre"));
				newUser.setLastname(request.getParameter("apellido"));
				newUser.setEmail(request.getParameter("email"));
				newUser.setGender(request.getParameter("genero"));
				if(UsersService.updateUser(newUser))
					response.sendRedirect("./Alumnos?status=1");
			}
		} catch (Exception e) {
			request.setAttribute("EXCEPTION", e);
			response.sendRedirect("./JSP/error.jsp?msg=" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
