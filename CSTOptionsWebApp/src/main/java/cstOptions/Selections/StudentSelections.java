package cstOptions.Selections;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
public class StudentSelections extends HttpServlet {
	
	File selectionFile = new File("./selection.csv");
	@Override		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		PrintWriter selectionFileWriter = new PrintWriter(selectionFile);
		
		response.setContentType("text/html;charset=UTF-8");
		
		try{
		String firstP = request.getParameter("firstP");
		String secP = request.getParameter("secondP");
		String thirdP = request.getParameter("thirdP");
		String fourthP = request.getParameter("fourthP");
		
		selectionFileWriter.println("Student #,First Pick,Second Pick,Thrid Pick,Fourth Pick");
		selectionFileWriter.println(StudentID.setID()+","+firstP+","+secP+","+thirdP+","+fourthP);
		
		System.out.println(StudentID.setID()+","+firstP+","+secP+","+thirdP+","+fourthP);
		} finally {
		selectionFileWriter.close();
		}
	}
}
