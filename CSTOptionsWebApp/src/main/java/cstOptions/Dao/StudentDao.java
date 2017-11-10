package cstOptions.Dao;
import cstOptions.Entity.Options;
import cstOptions.Entity.Student;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.PrintWriter;


@Repository
public class StudentDao {
    private static final String COMMA_DELIMITER = ",";

    private static ArrayList<Student> stulist;
    private static ArrayList<Options> optionlist;


    public void getAllStudents() {

    public ArrayList<HashMap<String, String>> getAllStudents(){

        ArrayList<HashMap<String, String>> studentList = new ArrayList<>();

        try
        {
        	
        	File studentHTML = new File ("C:/Users/Dikson/Desktop/Work/springTest/CSTOptionsWebApp/students.csv");
        	PrintWriter pWriter = new PrintWriter (studentHTML);
        	
      

            ArrayList<Student> stulist = new ArrayList<>();
            ArrayList<Options> optionlist = new ArrayList<>();
            ReadStudentChoices(stulist, "upload-dir/StudentChoices.csv");
//            ReadStudentGPA(stulist, "upload-dir/StudentGPA.csv");
//            ReadOptionList(optionlist, "upload-dir/OptionSelectionControl.csv");

            //Print Student List
            
            pWriter.println("-Student #,First Name,Last Name,Priority list,Status,First Choice,Second Choice,Third Choice,Fourth Choice");
            
            for(Student s:stulist){
            	String studentName = s.getName();
            	String[] firstLastName = studentName.split("\\s+");
            	String studentChoices = s.printStudentChoices();
            	String[] allChoices = studentChoices.split("\t");
            	
            	
                System.out.println(s.getID()+"\n"+s.getName()+"\nGPA: "+s.getGPA()+"\n"+s.getPriority()+"\n"+s.getStatus()+"\n"+s.printStudentChoices());
                System.out.println("---------***--------");
                
                pWriter.println(s.getID()+","+firstLastName[0]+","+firstLastName[1]+","+s.getPriority()+","+s.getStatus()+","+allChoices[0]+","+allChoices[1]+","+allChoices[2]+","+allChoices[3]);

                HashMap<String, String> studentInfo = new HashMap<>();
                studentInfo.put("ID", s.getID());
                studentInfo.put("Name", s.getName());
                studentInfo.put("GPA", s.getGPA());
                studentInfo.put("Priority", Integer.toString(s.getPriority()));
                studentInfo.put("Status", s.getStatus());
                studentInfo.put("AssignedOption", s.getAssignedOption());
                studentInfo.put("StudentChoices", s.getStudentChoices());

                studentList.add(studentInfo);
            }
            pWriter.close();
        }
        catch(Exception ee){
            ee.printStackTrace();
	        }
	    }

        }
        return studentList;
    }

    public static void ReadStudentChoices(ArrayList<Student> stulist, String filename) throws IOException {

        BufferedReader br;
        String line;
        //Reading CSV file
        br = new BufferedReader(new FileReader(filename));

        //Read to skip the header
        br.readLine();

        //Reading from second line
        while ((line = br.readLine()) != null){
            String[] studentInfo = line.split(COMMA_DELIMITER);
            ArrayList<String> studentChoices = new ArrayList<>();
            if(studentInfo.length>0){

                //save option choices made by student
                studentChoices.add(studentInfo[5]);
                studentChoices.add(studentInfo[6]);
                studentChoices.add(studentInfo[7]);
                studentChoices.add(studentInfo[8]);

                //Save details
                Student stu = new Student(studentInfo[0], studentInfo[1], studentInfo[2], Integer.parseInt(studentInfo[3]), studentInfo[4], studentChoices);
                stulist.add(stu);
            }
        }
    }
    private static void ReadStudentGPA(ArrayList<Student> stulist, String filename) throws IOException {

        String line;
        BufferedReader br;
        br = new BufferedReader(new FileReader(filename));
        br.readLine();

        while ((line = br.readLine()) != null){
            String[] studentInfo = line.split(COMMA_DELIMITER);
            if(studentInfo.length>0){

                for (Student s : stulist) {
                    if(s.getID().equals(studentInfo[0])){
                        s.setGPA(studentInfo[1]);
                        break;
                    }
                }
            }
        }
    }
    public static void ReadOptionList(ArrayList<Options> optionList, String filename) throws IOException {

        String line;
        BufferedReader br;
        br = new BufferedReader(new FileReader(filename));
        br.readLine();

        while ((line = br.readLine()) != null){
            String[] optionInfo = line.split(COMMA_DELIMITER);
            if(optionInfo.length>0){
                Options opt = new Options(optionInfo[0], Integer.parseInt(optionInfo[1]));
                optionList.add(opt);
            }
        }
    }
}
