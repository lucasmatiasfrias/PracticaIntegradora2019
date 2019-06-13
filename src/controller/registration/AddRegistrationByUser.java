package controller.registration;

import static model.services.ServiceOpertationResultType.Success;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.RegistrationDTO;
import dto.SubjectDTO;
import dto.UserDTO;
import model.services.RegistrationService;
import model.services.ServiceOperationResult;
import model.services.SubjectService;
import model.services.UsersService;

@WebServlet("/InscripcionPorAlumnoAlta")
public class AddRegistrationByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddRegistrationByUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceOperationResult<UserDTO> res = UsersService.getUserByFile(request.getParameter("legajoAlumno"));
		ServiceOperationResult<SubjectDTO> res2 = SubjectService.getSubjects();
		if (res2.getResultType().equals(Success) && !res.getQueryResults().isEmpty()) {
			request.setAttribute("ALUMNO", res.getQueryResults().get(0));
			request.setAttribute("MATERIAS", res2.getQueryResults());
			getServletContext().getRequestDispatcher("/JSP/inscripcionPorAlumno.jsp").forward(request, response);
		} else {
			if (!res2.getResultType().equals(Success))
				request.setAttribute("ERROR", res2.getResultMsg());
			else
				request.setAttribute("ERROR", res.getResultMsg());
			getServletContext().getRequestDispatcher("/JSP/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = request.getParameter("json");
		if(json!=null&&!json.isEmpty()) {
			try {
				JsonParser parser = new JsonParser();
				JsonObject obj = (JsonObject) parser.parse(json);
				String studentFile=obj.get("studentFile").toString();
				List<String> subjectsIds= new ArrayList<String>();
				for(JsonElement e: obj.get("subjectsIds").getAsJsonArray()) {
					subjectsIds.add(e.getAsString());
				}
				ServiceOperationResult<RegistrationDTO> res=RegistrationService.addRegistration(studentFile, subjectsIds);
				if(!res.getResultType().equals(Success)) {
					response.sendRedirect("/PracticaIntegradoraUnpaz2019/Inscripciones");
				}
			}catch (Exception e) {
				
			}
		}
	}

}
