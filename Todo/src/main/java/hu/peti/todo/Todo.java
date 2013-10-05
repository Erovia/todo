package hu.peti.todo;


/**
 * Created by P on 2013.09.24..
 */
public class Todo {
    public enum Priority {LOW, MEDIUM, HIGH}
    private String title;
    private Priority priority;
    private String dueDate;
    private String description;

    public Todo(String aTitle, Priority aPriority, String aDuedate, String aDescription){
        title=aTitle;
        priority=aPriority;
        dueDate=aDuedate;
        description=aDescription;
    }

    public String getTitle(){
        return title;
    }

    public Priority getPriority(){
        return priority;
    }

    public String getDueDate(){
        return dueDate;
    }

    public String getDescription(){
        return description;
    }
}
