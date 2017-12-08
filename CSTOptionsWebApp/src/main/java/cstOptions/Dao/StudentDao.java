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

    //ArrayList of Student Objects
    private static ArrayList<Student> studentList = new ArrayList<>();
    //ArrayList of Options Objects
    private static ArrayList<Options> optionList = new ArrayList<>();
    //Set List of Option names
    private static Set<String> optionNameList = new HashSet<>();
    //Set List of Student IDs
    private static Set<String> studentIDList = new HashSet<>();


    public ArrayList<HashMap<String, String>> getAllStudents(){

        ArrayList<HashMap<String, String>> parsedStudentList = new ArrayList<>();

        try
        {
        	File studentHTML = new File ("./students.csv");
        	PrintWriter pWriter = new PrintWriter (studentHTML);

            //Print Student List
            pWriter.println("-Student #,First Name,Last Name,Priority list,Status,First Choice,Second Choice,Third Choice,Fourth Choice");
            
            for(Student student : studentList){
            	String studentName = student.getName();
            	String[] firstLastName = studentName.split("\\s+");
            	String studentChoices = student.printStudentChoices();
            	String[] allChoices = studentChoices.split("/n");
                
                pWriter.println(student.getID()+","+firstLastName[0]+","+firstLastName[1]+","+ student.getPriority()+","+ student.getStatus()+","+allChoices[0]+","+allChoices[1]+","+allChoices[2]+","+allChoices[3]);

                HashMap<String, String> studentInfo = new HashMap<>();
                studentInfo.put("ID", student.getID());
                studentInfo.put("Name", student.getName());
                studentInfo.put("GPA", String.valueOf(student.getGPA()));
                studentInfo.put("Priority", Integer.toString(student.getPriority()));
                studentInfo.put("Status", student.getStatus());
                studentInfo.put("AssignedOption", student.getAssignedOption());
                studentInfo.put("StudentChoices", student.printStudentChoices());

                parsedStudentList.add(studentInfo);
            }

            pWriter.close();
            CheckOverFlow(studentList, optionList);
            CheckInEligibleStudents(studentList);
        }
        catch(Exception ee){
            ee.printStackTrace();
	        }
        return parsedStudentList;
    }

    //Generate data
    public String generate(){
        studentList.clear();
        optionList.clear();
        optionNameList.clear();
        studentIDList.clear();

        try {

        //Reads files in
        ReadStudentChoices(studentList, "upload-dir/StudentChoices.csv");
        ReadStudentGPA(studentList, "upload-dir/StudentGPA.csv", studentIDList);
        ReadOptionList(optionList, "upload-dir/OptionSelectionControl.csv", optionNameList);

        //Performs all functions required to sort students into their Option course
        StudentPlacement place = new StudentPlacement(studentList, optionList);
        place.studentPlacementSort();

        return "success";
        } catch (Exception ee){
            ee.printStackTrace();
            return "failed";
        }
    }

    //Reads Option CSV file and creates Option objects
    public static void ReadOptionList(ArrayList<Options> optionList, String filename, Set<String> optionNameList)throws IOException{

        BufferedReader br;
        String line;
        br = new BufferedReader(new FileReader(filename));
        br.readLine();

        while((line = br.readLine()) != null){
            String[] optionInfo = line.split(COMMA_DELIMITER);

            //Changes Option choice "Wait for January" to "Wait a term"
            if(optionInfo[0].split(" ")[0].equals("Wait")){
                optionInfo[0] = "Wait a term";
            }

            //Save details and creates new Option object
            if(optionInfo.length>0){
                Options opt = new Options(optionInfo[0], Integer.parseInt(optionInfo[1]));
                optionList.add(opt);
                optionNameList.add(optionInfo[0]);
            }
        }
    }

    //Reads Student Choice CSV file and creates Student objects
    public static void ReadStudentChoices(ArrayList<Student> studentList, String filename) throws IOException {

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
            if(studentInfo.length>0) {

                /* Changes Student choice from "wait for January" to "wait a term" */
                /* Position includes all student information except for choices */

                //Changes Student choice from "Wait for January" to "Wait a term"
                int position = 5;
                while (position < studentInfo.length){
                    if(studentInfo[position].split(" ")[0].equals("Wait")){
                        studentInfo[position] = "Wait a term";
                    }
                    position++;
                }

                //Resets position
                position = 5;

                //Adds all student choices to an Arraylist dynamically
                while (position < studentInfo.length){
                    studentChoices.add(studentInfo[position]);
                    position++;
                }

                //Save details and creates new Student object
                Student student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], Integer.parseInt(studentInfo[3]), 0, studentChoices, "", studentInfo[4], "", 0);
                if ("".equals(student.getStatus())) {
                    student.setStatus("Eligible");
                }
                studentList.add(student);
            }
        }
    }

    //Reads Student GPA CSV file and adds GPA to Student objects
    public static void ReadStudentGPA(ArrayList<Student> studentList, String filename, Set<String> studentIDList) throws IOException {

        String line;
        BufferedReader br;
        br = new BufferedReader(new FileReader(filename));
        br.readLine();

        while ((line = br.readLine()) != null){
            String[] studentInfo = line.split(COMMA_DELIMITER);
            if(studentInfo.length>0){
                for (Student student : studentList) {
                    if(student.getID().equals(studentInfo[0])){
                        Double dbl = Double.parseDouble(studentInfo[1]);
                        student.setGPA(dbl);
                        studentIDList.add(student.getID());
                        break;
                    }
                }
            }
        }
    }

    //Checks to see if number of students is greater than capacity
    public static void CheckOverFlow(ArrayList<Student> studentList, ArrayList<Options> optionList){
        int totalCapacity = 0;
        int totalStudents = studentList.size();

        for(Options option:optionList){
            totalCapacity += option.getCapacity();
        }

        if(totalStudents > totalCapacity){
            System.out.println("Too Many Students! Need " + (totalStudents - totalCapacity) + " Space!!!");
        }else{
            System.out.println("There are just enough space for all students");
        }
    }

    //Prints out all students who are ineligible
    public static void CheckInEligibleStudents(ArrayList<Student> stulist){
        System.out.println("Ineligible Students:");
        for(Student stu:stulist){
            if(stu.getPointChecker() == 1){
                System.out.println("Student ID: " + stu.getID());
                System.out.println("Student Name: " + stu.getName());
                System.out.println("Reason: " + stu.getReason());
                System.out.println();
            }
        }
    }


    public static Student searchById(String search){
        System.out.println("SEARCH:" + search);

            for(Student student : studentList){
                if(search.equals(student.getID())){
                    System.out.println("Student exists");
                    return student;
                }
                else {
                    System.out.println("Didnt work LOL!");
                }
            }
            return null;
    }

    //Prints all Options information
    public ArrayList<Options> ViewOptions(){
        return optionList;
    }

    //Add student to Option course
    public String AddStudent(String ID, String optionName){
        String status = "failed";
        if(optionNameList.contains(optionName)){
            Student stu = searchById(ID);
            if("NOTHING".equals(stu.getAssignedOption())){
                for(Options opt: optionList){
                    if(optionName.equals(opt.getOptionName())){
                        stu.setAssignedOption(opt.getOptionName());
                        opt.addStudentToList(stu);
                        status = "success";
                        break;
                    }
                }
            }else{System.out.println("Student is a part of "+stu.getAssignedOption()+" class. Please remove student first before trying again.");}
        }else{System.out.println("That is not a valid Option course");}
        return status;
    }
    public String DropStudent(String ID){
        Student student = searchById(ID);
        String status = "failed";
        for(Options option : optionList){
            if(student.getAssignedOption().equals(option.getOptionName())){
                option.removeStudent(student.getName());
                student.setAssignedOption("NOTHING");
                status = "success";
                break;
            } else {
                status = "failed";
            }
        }
        return status;
    }
}
