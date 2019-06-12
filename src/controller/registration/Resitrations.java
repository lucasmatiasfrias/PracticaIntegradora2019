package controller.registration;

import static model.services.ServiceOpertationResultType.Success;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

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
			if ( selectedSubject!= null && !selectedSubject.equals("0")) {
				request.setAttribute("materiaSeleccionada", selectedSubject);
			}
			request.setAttribute("MATERIAS", res.getQueryResults());
			getServletContext().getRequestDispatcher("/JSP/inscripciones.jsp").forward(request, response);
		} else {
			request.setAttribute("ERROR", res.getResultMsg());
			getServletContext().getRequestDispatcher("/JSP/error.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<RegistrationDTO> res;
		String selectedSubject=request.getParameter("materiaSeleccionada");
		if ( selectedSubject!= null && !selectedSubject.equals("0")) {
			res=RegistrationService.getRegistrationsBySubject(selectedSubject);
		}else {
			res=RegistrationService.getRegistrations();
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(res.getQueryResults()));
	}

}
