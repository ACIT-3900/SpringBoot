package cstOptions.Dao;
import cstOptions.Entity.Options;
import cstOptions.Entity.Student;
import cstOptions.Entity.StudentPlacement;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;


@Repository
public class StudentDao {
    private static final String COMMA_DELIMITER = ",";

    private static ArrayList<Student> stulist = new ArrayList<>();
    private static ArrayList<Options> optionlist = new ArrayList<>();
    private static HashSet<Student> nullList = new HashSet<>();
    private static Set<String> optName = new HashSet<String>();
    private static Set<String> stuID = new HashSet<String>();
    private static Set<String> nullStuID = new HashSet<String>();



    public ArrayList<HashMap<String, String>> getAllStudents(){

        ArrayList<HashMap<String, String>> studentList = new ArrayList<>();

        try
        {
        	
        	File studentHTML = new File ("./students.csv");
        	PrintWriter pWriter = new PrintWriter (studentHTML);

        	stulist.clear();
        	optionlist.clear();
        	nullList.clear();
        	optName.clear();
        	stuID.clear();

            ReadStudentChoices(stulist, "upload-dir/StudentChoices.csv");
            ReadStudentGPA(stulist, "upload-dir/StudentGPA.csv", stuID);
            ReadOptionList(optionlist, "upload-dir/OptionSelectionControl.csv", optName);

            StudentPlacement place = new StudentPlacement(stulist, optionlist, nullList);
            place.displayGPA();
            //Print Student List
            
            pWriter.println("-Student #,First Name,Last Name,Priority list,Status,First Choice,Second Choice,Third Choice,Fourth Choice");
            
            for(Student s:stulist){
            	String studentName = s.getName();
            	String[] firstLastName = studentName.split("\\s+");
            	String studentChoices = s.printStudentChoices();
            	String[] allChoices = studentChoices.split("/n");
                
                pWriter.println(s.getID()+","+firstLastName[0]+","+firstLastName[1]+","+s.getPriority()+","+s.getStatus()+","+allChoices[0]+","+allChoices[1]+","+allChoices[2]+","+allChoices[3]);

                HashMap<String, String> studentInfo = new HashMap<>();
                studentInfo.put("ID", s.getID());
                studentInfo.put("Name", s.getName());
                studentInfo.put("GPA", String.valueOf(s.getGPA()));
                studentInfo.put("Priority", Integer.toString(s.getPriority()));
                studentInfo.put("Status", s.getStatus());
                studentInfo.put("AssignedOption", s.getAssignedOption());
                studentInfo.put("StudentChoices", s.printStudentChoices());

                studentList.add(studentInfo);
            }

            pWriter.close();
        }
        catch(Exception ee){
            ee.printStackTrace();
	        }
        return studentList;
    }

    //Reads Option CSV file and creates Option objects
    public static void ReadOptionList(ArrayList<Options> optlist, String filename, Set<String> optName)throws IOException{

        BufferedReader br;
        String line;
        br = new BufferedReader(new FileReader(filename));
        br.readLine();

        while((line = br.readLine()) != null){
            String[] optionInfo = line.split(COMMA_DELIMITER);
            if(optionInfo.length>0){
                Options opt = new Options(optionInfo[0], Integer.parseInt(optionInfo[1]));
                optlist.add(opt);
                optName.add(optionInfo[0]);
            }
        }
    }

    //Reads Student Choice CSV file and creates Student objects
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
                Student stu = new Student(studentInfo[0], studentInfo[1], studentInfo[2], Integer.parseInt(studentInfo[3]), 0, studentChoices, "", studentInfo[4], "");
                if ("".equals(stu.getStatus())){
                    stu.setStatus("Eligible");
                }
                stulist.add(stu);
            }
        }
    }

    //Reads Student GPA CSV file and adds GPA to Student objects
    public static void ReadStudentGPA(ArrayList<Student> stulist, String filename, Set<String> stuID) throws IOException {

        String line;
        BufferedReader br;
        br = new BufferedReader(new FileReader(filename));
        br.readLine();

        while ((line = br.readLine()) != null){
            String[] studentInfo = line.split(COMMA_DELIMITER);
            if(studentInfo.length>0){
                for (Student s:stulist) {
                    if(s.getID().equals(studentInfo[0])){
                        Double dbl = Double.parseDouble(studentInfo[1]);
                        s.setGPA(dbl);
                        stuID.add(s.getID());
                        break;
                    }
                }
            }
        }
    }

    //Creates a Set List of null Student IDs
    public static void CreateNullStudentIDLIst(Set<String> nullStuID, HashSet<Student> nullList){
        for(Student stu:nullList){
            nullStuID.add(stu.getID());
        }
    }

    public static Student searchById(String search){
        System.out.println("SEARCH:" + search);

            for(Student stu:stulist){
                if(search.equals(stu.getID())){
                    System.out.println("Student exists");
                    return stu;
                }
                else {
                    System.out.println("Didnt work LOL!");
                }
            }
            return null;
    }

    //Prints all Options information
    public ArrayList<Options> ViewOptions(){
        return optionlist;
    }

    public String AddStudent(String ID, String optionName){
        String status = "failed";
        if(optName.contains(optionName)){
            Student stu = searchById(ID);
            if("NOTHING".equals(stu.getAssignedOption())){
                for(Options opt:optionlist){
                    if(optionName.equals(opt.getOptionName())){
                        stu.setAssignedOption(opt.getOptionName());
                        opt.addStudentToList(stu);
                        status = "success";
                        if(nullStuID.contains(stu.getID())){
                            nullList.remove(stu);
                        }
                        break;
                    }
                }
            }else{System.out.println("Student is a part of "+stu.getAssignedOption()+" class. Please remove student first before trying again.");}
        }else{System.out.println("That is not a valid Option course");}
        return status;
    }
    public String DropStudent(String ID){
        Student stu = searchById(ID);
        String status = "failed";
        for(Options opt:optionlist){
            if(stu.getAssignedOption().equals(opt.getOptionName())){
                opt.removeStudent(stu.getName());
                stu.setAssignedOption("NOTHING");
                nullList.add(stu);
                nullStuID.add(stu.getName());
                status = "success";
                break;
            } else {
                status = "failed";
            }
        }
        return status;
    }
}
