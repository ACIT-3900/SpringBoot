package cstOptions.Entity;

import java.util.*;

/**
 * Created by rodne on 2017-10-19.
 */
public class StudentPlacement {

    private ArrayList<Student> stulist;
    private ArrayList<Options> optlist;
    private HashSet<Student> nullList;
    ArrayList<Student> onePriorityList = new ArrayList<>();
    ArrayList<Student> twoPriorityList = new ArrayList<>();
    ArrayList<Student> threePriorityList = new ArrayList<>();
    ArrayList<Student> fourPriorityList = new ArrayList<>();

    //What is the average GPA of all students
    public void averageGPA(ArrayList<Student> stulist){
        Double gpa = 0.0;
        int numberOfStudents = 0;
        double totalGPA = 0.0;
        for(Student stu:stulist){
            gpa += stu.getGPA();
            numberOfStudents++;
        }
        totalGPA = Math.round((gpa/numberOfStudents)*100);
        System.out.println("Average GPA of All Students: "+ totalGPA/100+"%");
    }

    public void lowestGPA(ArrayList<Student> stulist){
        double lowestGPA = 100.0;
        for(Student stu:stulist) {
            if (stu.getGPA() < lowestGPA) {
                lowestGPA = stu.getGPA();
            }
        }
        System.out.println("Lowest GPA: "+ lowestGPA+"%");
    }

    public void highestGPA(ArrayList<Student> stulist){
        double highestGPA = 0.0;
        for(Student stu:stulist) {
            if (stu.getGPA() > highestGPA) {
                highestGPA = stu.getGPA();
            }
        }
        System.out.println("Highest GPA: "+ highestGPA+"%");
    }

    public void totalStudents(ArrayList<Student> stulist){
        int numberOfStudents = 0;
        for(Student stu:stulist){
            numberOfStudents++;
        }
        System.out.println("Total Number of Students: "+ numberOfStudents);
    }

    public void groupPriority(ArrayList<Student> stulist){
            int priorityA = 0;
            int priorityB = 0;
            int otherPriority = 0;
            for(Student stu:stulist) {
                if (stu.getPriority() == 1) {
                    priorityA++;
                } else if (stu.getPriority() == 2) {
                    priorityB++;
                } else {
                    otherPriority = 0;
                }
            }
            System.out.println("Students with Priority Level 1: " +priorityA);
            System.out.println("Students with Priority Level 2: " +priorityB);
            //System.out.println("Students with No Priority Level: " +otherPriority);
    }

    public StudentPlacement(ArrayList<Student> stulist, ArrayList<Options> optlist, HashSet<Student> nullList){
        this.stulist = stulist;
        this.optlist = optlist;
        this.nullList = nullList;
    }

    private void sortStudentsOnGPA(ArrayList<Student> stulist){
        Collections.sort(stulist, Comparator.comparing(Student::getGPA).reversed());
    }

    private void sortStudentsOnPriority(ArrayList<Student> stulist){
        for (Student s:stulist) {
            switch(s.getPriority()){
                case 1:
                    onePriorityList.add(s);
                    break;

                case 2:
                    twoPriorityList.add(s);
                    break;

                case 3:
                    threePriorityList.add(s);
                    break;

                case 4:
                    fourPriorityList.add(s);
                    break;
            }
        }
    }

    private void createNullList(ArrayList<Student> onePriorityList, ArrayList<Student> twoPriorityList, ArrayList<Student> threePriorityList, ArrayList<Student> fourPriorityList){
        ArrayList<Student> one = onePriorityList;
        ArrayList<Student> two = twoPriorityList;
        ArrayList<Student> three = threePriorityList;
        ArrayList<Student> four = fourPriorityList;

        for(Student stu:one){
            if(stu.getAssignedOption().equals("NOTHING")){
                nullList.add(stu);
            }
        }
        for(Student stu:two){
            if(stu.getAssignedOption().equals("NOTHING")){
                nullList.add(stu);
            }
        }
        for(Student stu:three){
            if(stu.getAssignedOption().equals("NOTHING")){
                nullList.add(stu);
            }
        }
        for(Student stu:four){
            if(stu.getAssignedOption().equals("NOTHING")){
                nullList.add(stu);
            }
        }
    }

    private void placePriorityLists(ArrayList<Student> priorityList, ArrayList<Options> optlist) {
        for(Student stu:priorityList){
            if(stu.getStudentChoices().size()>0){
                if("Eligible".equals(stu.getStatus())){
                    int check = 0;
                    for(int i=0;i<stu.getStudentChoices().size();i++){
                        if(check ==0){
                            for(Options opt:optlist){
                                if (opt.getOptionName().equals(stu.getStudentChoices().get(i)) && opt.getEmptySeats()!= 0) {
                                    opt.addStudentToList(stu);
                                    stu.setAssignedOption(opt.getOptionName());
                                    check++;
                                    break;
                                }
                            }
                        }
                    }
                    if(check == 0 && stu.getAssignedOption() != null){
                        stu.setReason("All choice classes are full");
                        stu.setAssignedOption("NOTHING");
                    }
                }else{
                    stu.setReason("Has a status");
                    stu.setAssignedOption("NOTHING");
                }
            }else{
                stu.setReason("No selection was made");
                stu.setAssignedOption("NOTHING");
            }
        }
    }

    public void displayGPA(){
        sortStudentsOnGPA(stulist);
        sortStudentsOnPriority(stulist);
        placePriorityLists(onePriorityList, optlist);
        placePriorityLists(twoPriorityList, optlist);
        placePriorityLists(threePriorityList, optlist);
        placePriorityLists(fourPriorityList, optlist);
        createNullList(onePriorityList, twoPriorityList, threePriorityList, fourPriorityList);
    }


}
