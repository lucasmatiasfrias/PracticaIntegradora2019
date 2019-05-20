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

@WebServlet("/AlumnoEditar")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServiceOperationResult<UserDTO> res = UsersService.getUserByFile(request.getParameter("legajo"));
		if (!res.getQueryResluts().isEmpty()) {
			request.setAttribute("ALUMNO", res.getQueryResluts().get(0));
			request.setAttribute("GENEROS", UserDTO.avaiableGenders);
			getServletContext().getRequestDispatcher("/JSP/alumno_modificacion.jsp").forward(request, response);
		}else {
			request.setAttribute("RESULTADO", res);
			getServletContext().getRequestDispatcher("/JSP/resultadoABM.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDTO user = new UserDTO(request.getParameter("legajo"), request.getParameter("dni"),
				request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("email"),
				request.getParameter("genero"));
		ServiceOperationResult<UserDTO> res = UsersService.updateUser(user);
		request.setAttribute("RESULTADO", res);
		getServletContext().getRequestDispatcher("/JSP/resultadoABM.jsp").forward(request, response);
	}
}
