package controller.subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SubjectDTO;
import services.SubjectService;

@WebServlet("/MateriaAlta")
public class AddSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("STATUS", request.getParameter("status"));
		getServletContext().getRequestDispatcher("/JSP/materia_alta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SubjectDTO subject = new SubjectDTO(request.getParameter("code"), request.getParameter("description"));
		try {
			if (SubjectService.addSubject(subject))
				response.sendRedirect("./Materias?status=1");
		} catch (Exception e) {
			request.setAttribute("EXCEPTION", e);
			response.sendRedirect("./JSP/error.jsp?msg="+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
