package controller.subject;

import static model.services.ServiceOpertationResultType.Success;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SubjectDTO;
import model.services.ServiceOperationResult;
import model.services.SubjectService;

@WebServlet("/Materias")
public class Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<SubjectDTO> res = SubjectService.getSubjects();
		if (res.getResultType().equals(Success)) {
			request.setAttribute("MATERIAS", res.getQueryResluts());
			getServletContext().getRequestDispatcher("/JSP/materias.jsp").forward(request, response);
		} else {
			request.setAttribute("ERROR", res.getResultMsg());
			getServletContext().getRequestDispatcher("/JSP/error.jsp").forward(request, response);
		}
	}

}
