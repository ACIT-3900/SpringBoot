package cstOptions.Entity;

import java.util.ArrayList;

public class Options {
    private String optionName;
    private int capacity;
    private ArrayList<Student> classList = new ArrayList<>();

    public Options(String optionName, int capacity) {
        this.optionName = optionName;
        this.capacity = capacity;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setClassList(ArrayList<Student> newList){
        this.classList = newList;
    }

    public String getOptionName(){
        return optionName;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getClassList(){
        if (classList == null) {
            return null;
        } else {
            String str = "";
            for (int i = 0; i < classList.size(); i++) {
                str += classList.get(i).getID() + "\t";
            }
            return str;
        }
    }

    public int getEmptySeats() {
        if(classList == null) return capacity;
        else return capacity - classList.size();
    }

    public void addToClassList(Student stu){
        classList.add(stu);
    }
}
