package controller.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.RegistrationDTO;
import dto.SubjectDTO;
import model.services.RegistrationService;
import model.services.ServiceOperationResult;
import model.services.SubjectService;

@WebServlet("/InscripcionBaja")
public class DeleteRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServiceOperationResult<RegistrationDTO> res = RegistrationService
				.getRegistrationsById(request.getParameter("id"));
		if (!res.getQueryResults().isEmpty()) {
			request.setAttribute("ID_INSCRIPCION", res.getQueryResults().get(0).getId());
			request.setAttribute("ALUMNO", res.getQueryResults().get(0).getUser());
			request.setAttribute("MATERIA", res.getQueryResults().get(0).getSubjcet());
			getServletContext().getRequestDispatcher("/JSP/inscripcion_baja.jsp").forward(request, response);
		} else {
			request.setAttribute("RESULTADO_ABM", res);
			getServletContext().getRequestDispatcher("/JSP/inscripciones.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<RegistrationDTO> res = RegistrationService.deleteRegistration(request.getParameter("id"));
		ServiceOperationResult<SubjectDTO> res2 = SubjectService.getSubjects();
		request.setAttribute("MATERIAS", res2.getQueryResults());
		request.setAttribute("RESULTADO_ABM", res);
		getServletContext().getRequestDispatcher("/JSP/inscripciones.jsp").forward(request, response);
	}

}
