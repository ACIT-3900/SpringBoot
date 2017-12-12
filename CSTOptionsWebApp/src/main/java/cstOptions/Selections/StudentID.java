package cstOptions.Selections;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		name = "Student Selections",
		description = "Save student selection servlet",
		urlPatterns = {"/saveSelections"}
	)
public class StudentID extends HttpServlet {
	
	static String studentID;
	
	@Override		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		
		studentID = request.getParameter("stuID");
	}
	
	protected static String setID(){
		return studentID;
	}
}
