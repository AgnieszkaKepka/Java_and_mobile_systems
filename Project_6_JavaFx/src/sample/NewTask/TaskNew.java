package sample.NewTask;

import java.time.LocalDate;

public class TaskNew {
    private String title;
    private static Priority priority;
    private static LocalDate date;
    private String describe;

    public TaskNew(String title, Priority priority, LocalDate date, String describe){
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.describe = describe;
    }

    public String getTitle(){
        return title;
    }

    public static Priority getPriority() {
        return priority;
    }

    public static LocalDate getLocalDate() {
        return date;
    }

    public String getDescribe(){
        return describe;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setLocalDate(LocalDate localDate) {
        this.date = localDate;
    }

    public void setDescribe(String describe){
        this.describe = describe;
    }
}
