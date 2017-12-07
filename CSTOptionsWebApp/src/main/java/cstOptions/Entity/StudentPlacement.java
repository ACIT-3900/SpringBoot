package cstOptions.Entity;

import java.util.*;

/**
 * Created by rodne on 2017-10-19.
 */
public class StudentPlacement {

    private ArrayList<Student> studentList;
    private ArrayList<Options> optionList;
    private ArrayList<Student> priorityListOne = new ArrayList<>();
    private ArrayList<Student> priorityListTwo = new ArrayList<>();
    private ArrayList<Student> priorityListThree = new ArrayList<>();
    private ArrayList<Student> priorityListFour = new ArrayList<>();


    public StudentPlacement(ArrayList<Student> studentList, ArrayList<Options> optionList){
        this.studentList = studentList;
        this.optionList = optionList;
    }

    //Sorts students by their GPA from highest to lowest
    private void sortStudentsOnGPA(ArrayList<Student> studentList){
        Collections.sort(studentList, Comparator.comparing(Student::getGPA).reversed());
    }

    //Checks student's priority level and sorts them into their priority list
    private void sortStudentsOnPriority(ArrayList<Student> studentList){
        for (Student student : studentList) {
            switch(student.getPriority()){
                case 1:
                    priorityListOne.add(student);
                    break;

                case 2:
                    priorityListTwo.add(student);
                    break;

                case 3:
                    priorityListThree.add(student);
                    break;

                case 4:
                    priorityListFour.add(student);
                    break;
            }
        }
    }

    //Eligible students have point checker of 0. Those ineligible will have point checker of 1
    private void specifyStudentPointChecker(ArrayList<Student> studentList){
        for (Student student:studentList){
            if(student.getGPA() < 0.0 || student.getGPA() > 100.0){
                student.setPointChecker(1);
                student.setReason("Unacceptable GPA");
                student.setAssignedOption("NOTHING");
            }
            if(student.getStudentChoices().size()<1){
                student.setPointChecker(1);
                student.setAssignedOption("NOTHING");
                student.setReason("No choices selected");
            }
        }
    }

    //Reads priority lists one at a time and sorts student's into their Options
    private void sortStudents(ArrayList<Student> priorityList, ArrayList<Options> optionList) {

        for(Student student:priorityList){
            if(student.getPointChecker()!=1){
                int check = 0;
                for(int i=0;i<student.getStudentChoices().size();i++){
                    if(check==0) {
                        for (Options option : optionList) {
                            if(option.getOptionName().equals("Wait a term") && student.getStudentChoices().get(i).equals(option.getOptionName())){
                                option.addStudentToList(student);
                                student.setAssignedOption(option.getOptionName());
                                check++;
                                break;
                            }
                            else if(option.getOptionName().equals(student.getStudentChoices().get(i)) && option.getEmptySeats() != 0){
                                option.addStudentToList(student);
                                student.setAssignedOption(option.getOptionName());
                                check++;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    //Performs the above functions in correct order
    public void studentPlacementSort(){
        sortStudentsOnGPA(studentList);
        sortStudentsOnPriority(studentList);
        specifyStudentPointChecker(studentList);
        sortStudents(priorityListOne, optionList);
        sortStudents(priorityListTwo, optionList);
        sortStudents(priorityListThree, optionList);
        sortStudents(priorityListFour, optionList);
    }
}
