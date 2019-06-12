package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import model.services.ServiceOperationResult;
import model.services.UsersService;

@WebServlet("/AlumnoBaja")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServiceOperationResult<UserDTO> res = UsersService.getUserByFile(request.getParameter("legajo"));
		if (!res.getQueryResults().isEmpty()) {
			request.setAttribute("ALUMNO", res.getQueryResults().get(0));
			getServletContext().getRequestDispatcher("/JSP/alumno_baja.jsp").forward(request, response);
		}else {
			request.setAttribute("RESULTADO_ABM", res);
			getServletContext().getRequestDispatcher("/JSP/alumnos.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<UserDTO> res = UsersService.deleteUser(request.getParameter("legajo"));
		request.setAttribute("RESULTADO_ABM", res);
		getServletContext().getRequestDispatcher("/JSP/alumnos.jsp").forward(request, response);
	}
	
}
