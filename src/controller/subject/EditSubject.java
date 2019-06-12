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

@WebServlet("/MateriaEditar")
public class EditSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServiceOperationResult<SubjectDTO> res = SubjectService.getSubjectByCode(request.getParameter("id"));
		if (!res.getQueryResults().isEmpty()) {
			request.setAttribute("MATERIA", res.getQueryResults().get(0));
			getServletContext().getRequestDispatcher("/JSP/materia_modificacion.jsp").forward(request, response);
		}else {
			request.setAttribute("RESULTADO_ABM", res);
			getServletContext().getRequestDispatcher("/JSP/materias.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubjectDTO subject = new SubjectDTO(request.getParameter("code"), request.getParameter("description"));
		ServiceOperationResult<SubjectDTO> res = SubjectService.updateSubject(subject);
		request.setAttribute("RESULTADO_ABM", res);
		getServletContext().getRequestDispatcher("/JSP/materias.jsp").forward(request, response);
	}

}
