package cstOptions.Entity;

import java.util.ArrayList;

public class Student {
    private String ID;
    private String firstName;
    private String lastName;
    private int priority;
    private String GPA;
    private ArrayList<String> studentChoices;
    private String assignedOption;
    private String status;

    public Student() {
        this.ID = "A00000000";
        this.firstName = "";
        this.lastName = "";
        this.priority = 0;
        this.GPA = "";
        this.studentChoices = null;
        this.assignedOption = "";
        this.status = "";

    }


    public Student(String id, String firstName, String lastName, int priority, String status, ArrayList<String> studentChoices) {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.priority = priority;
        if (status.equals("")) {
            this.status = "Eligible";
        }
        this.studentChoices = studentChoices;
    }

    Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAssignedOption() {
        return assignedOption;
    }

    public String getStatus() {
        return status;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getPriority() {
        return priority;
    }

    public String getGPA() {
        return GPA;
    }

    public String getStudentChoices() {
        String choices = "";
        for(int i=0; i< studentChoices.size(); i++){
            choices += studentChoices.get(i) + "\n";
        }
        return choices;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public void setStudentChoices(ArrayList<String> studentChoices) {
        this.studentChoices = studentChoices;
    }

    public void setAssignedOption(String assignedOption) {
        this.assignedOption = assignedOption;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String printStudentChoices() {
        if (studentChoices == null) {
            return null;
        } else {
            String str = "";
            for (int i = 0; i < studentChoices.size(); i++) {
                str += studentChoices.get(i) + "\t";
            }
            return str;
        }
    }
}