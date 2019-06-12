package controller.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InscripcionAlta")
public class AddRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userFile=request.getParameter("legajoAlumno");
		String subjectId=request.getParameter("idMateria");
		response.getWriter().append("Alumno: "+userFile+"\nMateria: "+subjectId);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
