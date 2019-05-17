package controller.subject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SubjectDTO;
import services.SubjectService;

@WebServlet("/Materias")
public class Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Subjects() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		List<SubjectDTO> materias;
		try {
			materias = SubjectService.getSubjects();
			request.setAttribute("MATERIAS", materias);
			request.setAttribute("STATUS", request.getParameter("status"));
			getServletContext().getRequestDispatcher("/JSP/materias.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("EXCEPTION", e);
			getServletContext().getRequestDispatcher("/JSP/error.jsp?msg="+e.getLocalizedMessage()).forward(request, response);
			e.printStackTrace();
		}
		
	}

}
