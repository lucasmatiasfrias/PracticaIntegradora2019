package controller.registration;

import static model.services.ServiceOpertationResultType.Success;

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

@WebServlet("/Inscripciones")
public class Resitrations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Resitrations() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<SubjectDTO> res = SubjectService.getSubjects();
		if (res.getResultType().equals(Success)) {
			String selectedSubject=request.getParameter("materiaSeleccionada");
			ServiceOperationResult<RegistrationDTO> res2;
			if ( selectedSubject!= null && !selectedSubject.equals("0")) {
				request.setAttribute("materiaSeleccionada", selectedSubject);
				res2=RegistrationService.getRegistrationsBySubject(selectedSubject);
			}else {
				res2=RegistrationService.getRegistrations();
			}
			request.setAttribute("MATERIAS", res.getQueryResults());
			request.setAttribute("INSCRIPCIONES", res2.getQueryResults());
			getServletContext().getRequestDispatcher("/JSP/inscripciones.jsp").forward(request, response);
		} else {
			request.setAttribute("ERROR", res.getResultMsg());
			getServletContext().getRequestDispatcher("/JSP/error.jsp").forward(request, response);
		}
	}

}
