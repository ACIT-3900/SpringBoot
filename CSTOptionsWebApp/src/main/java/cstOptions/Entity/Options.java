package cstOptions.Entity;

import java.util.ArrayList;

/**
 * Created by rodne on 2017-10-11.
 */
public class Options {
    private String optionName;
    private int capacity;
    private ArrayList<Student> classList = new ArrayList<>();

    //Default Constructor
    public Options(){
        this.optionName = "";
        this.capacity = 0;
    }

    //Non-Default Constructor
    public Options(String optionName, int capacity) {
        this.optionName = optionName;
        this.capacity = capacity;
    }

    /* GETTERS */

    public int getCapacity(){
        return capacity;
    }

    public String getOptionName(){
        return optionName;
    }

    public ArrayList<Student> getClassList(){
        return classList;
    }

    /* SETTERS */

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setOptionName(String optionName){
        this.optionName = optionName;
    }

    public void setClassList(ArrayList<Student> newClassList){
        this.classList = newClassList;
    }

    /* FUNCTIONS */

    //Removes Student object from Option class list
    public void removeStudent(String studentName){
        for(Student s:classList){
            if (s.getName().equals(studentName)){
                classList.remove(s);
                break;
            }
        }
    }

    //Adds Student object to Option class list
    public void addStudentToList(Student student){
        classList.add(student);
    }

    //Checks if student is inside Option class list
    String checkStudentInClass(String studentID){
        String checker="";
        for(Student s:classList){
            if(s.getID().equals(studentID)){
                checker = "pos";
                break;
            }
        }
        return checker;
    }

    //Returns an integer of empty seats in Option class list
    public int getEmptySeats() {
        if(classList == null){
            return capacity;
        }
        else{
            return capacity - classList.size();
        }
    }

}