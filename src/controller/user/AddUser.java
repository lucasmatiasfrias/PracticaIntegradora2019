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

@WebServlet("/AlumnoAlta")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("GENEROS", UserDTO.avaiableGenders);
		getServletContext().getRequestDispatcher("/JSP/alumno_alta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDTO user = new UserDTO(request.getParameter("legajo"), request.getParameter("dni"),
				request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("email"),
				request.getParameter("genero"));
		ServiceOperationResult<UserDTO> res = UsersService.addUser(user);
		request.setAttribute("RESULTADO_ABM", res);
		getServletContext().getRequestDispatcher("/JSP/alumnos.jsp").forward(request, response);
	}

}
