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

@WebServlet("/Alumnos")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		List<UserDTO> alumnos;
		try {
			alumnos = UsersService.getUsers();
			request.setAttribute("ALUMNOS", alumnos);
			request.setAttribute("STATUS", request.getParameter("status"));
			getServletContext().getRequestDispatcher("/JSP/alumnos.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("EXCEPTION", e);
			getServletContext().getRequestDispatcher("/JSP/error.jsp?msg="+e.getLocalizedMessage()).forward(request, response);
			e.printStackTrace();
		}
		
	}

}
