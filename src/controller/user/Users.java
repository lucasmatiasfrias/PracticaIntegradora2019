package controller.user;

import static model.services.ServiceOpertationResultType.Success;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import model.services.ServiceOperationResult;
import model.services.UsersService;

@WebServlet("/Alumnos")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<UserDTO> res = UsersService.getUsers();
		if (res.getResultType().equals(Success)) {
			request.setAttribute("ALUMNOS", res.getQueryResults());
			getServletContext().getRequestDispatcher("/JSP/alumnos.jsp").forward(request, response);
		} else {
			request.setAttribute("ERROR", res.getResultMsg());
			getServletContext().getRequestDispatcher("/JSP/error.jsp").forward(request, response);
		}
	}

}
