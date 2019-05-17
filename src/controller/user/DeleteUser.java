package controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;

import services.UsersService;

@WebServlet("/AlumnoBaja")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			List<UserDTO> existing = UsersService.getUserByFile(request.getParameter("legajo"));
			if (!existing.isEmpty()) {
				request.setAttribute("ALUMNO", existing.get(0));
				getServletContext().getRequestDispatcher("/JSP/alumno_baja.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("EXCEPTION", e);
			response.sendRedirect("./JSP/error.jsp?msg=" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<UserDTO> existing = UsersService.getUserByFile(request.getParameter("legajo"));
			if (!existing.isEmpty()) {
				if(UsersService.deleteUser(existing.get(0)))
					response.sendRedirect("./Alumnos?status=1");
			}
		} catch (Exception e) {
			request.setAttribute("EXCEPTION", e);
			response.sendRedirect("./JSP/error.jsp?msg=" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
