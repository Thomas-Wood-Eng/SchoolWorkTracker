import java.util.*;

public class Task {
    private String className;
    private String description;
    private Calendar dueDate;
    private String taskName;
    private int importance;
    private double estimatedTime;
    private String type;
    private int daysTillDue;

    public Task(String cn, String d, Calendar dd, String tn, int i, double et, String t, int dtd){
        this.className = cn;
        this.description = d;
        this.taskName = tn;
        this.dueDate = dd;
        this.importance = i;
        this.estimatedTime = et;
        this.type = t;
        this.daysTillDue = dtd;
    }

    public String getClassName(){
        return this.className;
    }
    public String getDescription(){
        return this.description;
    }
    public String getTaskName(){
        return this.taskName;
    }
    public String getType(){
        return this.type;
    }
    public Calendar getDueDate(){
        return this.dueDate;
    }
    public int getDayDue(){
        return this.dueDate.get(dueDate.DATE);
    }
    public String getMonthDue(){
        String month = null;
        switch (this.dueDate.get(dueDate.MONTH) + 1) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        return month;
    }
    public int getYearDue(){
        return this.dueDate.get(dueDate.YEAR);
    }

    public String getFullDate(){
        String suffix = "th";
        if(getDayDue() < 4) {
            switch (getDayDue()){
                case 1:
                    suffix = "st";
                    break;
                case 2:
                    suffix = "nd";
                    break;
                case 3:
                    suffix = "rd";
                    break;
            }
        }
        return getDayDue() + suffix + " " + getMonthDue() + ", " + getYearDue();
    }

    public double getRecommendedOrder(){
        return this.daysTillDue/this.importance;
    }
    public int getImportance(){
        return this.importance;
    }
    public double getEstimatedTime(){
        return this.estimatedTime;
    }
}
