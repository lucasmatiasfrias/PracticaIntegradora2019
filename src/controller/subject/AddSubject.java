package controller.subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SubjectDTO;
import model.services.ServiceOperationResult;
import model.services.SubjectService;

@WebServlet("/MateriaAlta")
public class AddSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddSubject() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/JSP/materia_alta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubjectDTO subject = new SubjectDTO(request.getParameter("code"), request.getParameter("description"));
		ServiceOperationResult<SubjectDTO> res = SubjectService.addSubject(subject);
		request.setAttribute("RESULTADO", res);
		getServletContext().getRequestDispatcher("/JSP/resultadoABM.jsp").forward(request, response);
	}

}
