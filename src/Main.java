import java.util.List;
import java.util.*;
import java.util.stream.*;

public class Main {


    public static void main(String[] args) {

        List<Task> allTasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Give me a task in a class and I can sort them and give a recommended schedule to complete them");

        allTasks = addTasks(allTasks);

        printOptions();

        String taskOrder = scanner.nextLine();

        boolean wantToExit = false, invalidOption = false;

        while (!wantToExit) {

            switch (taskOrder) {
                case ("a"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getDueDate))
                            .forEach(s -> System.out.println(s.getTaskName() + " " + s.getDayDue()
                                    + "th " + s.getMonthDue() + " " + s.getYearDue() + " " + s.getClassName()
                                    + " " + s.getType() + " "+  s.getImportance() + " " + s.getDescription()));
                    break;
                case ("b"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getImportance))
                            .forEach(s -> System.out.println(s.getTaskName() + " " + s.getDayDue()
                                    + "th " + s.getMonthDue() + " " + s.getYearDue() + " " + s.getClassName()
                                    + " " + s.getType() + " "+  s.getImportance() + " " + s.getDescription()));
                    break;
                case ("c"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getRecommendedOrder))
                            .forEach(s -> System.out.println(s.getTaskName() + " " + s.getDayDue()
                                    + "th " + s.getMonthDue() + " " + s.getYearDue() + " " + s.getClassName()
                                    + " " + s.getType() + " "+  s.getImportance() + " " + s.getDescription()));
                    break;
                case ("d"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getClassName)).sorted()
                            .forEach(s -> System.out.println(s.getTaskName() + " " + s.getDayDue()
                                    + "th " + s.getMonthDue() + " " + s.getYearDue() + " " + s.getClassName()
                                    + " " + s.getType() + " "+  s.getImportance() + " " + s.getDescription()));
                    break;
                case ("e"):
                    wantToExit = true;
                    break;
                case ("f"):
                    addTasks(allTasks);
                    printOptions();
                    break;
                default:
                    System.out.println("Invalid. Try Again");
                    taskOrder = scanner.nextLine();
                    invalidOption = true;
            }

            if(!wantToExit || invalidOption){
                System.out.println("Feel free to pick another option");
                taskOrder = scanner.nextLine();
                invalidOption = false;
            }
        }
    }

    private static List<Task> addTasks(List<Task> currentTasks) {

        boolean keepGoing = true;

        Scanner scanner = new Scanner(System.in);

        String className = "", assignmentName = "", description = "", letter = "", go = "", type = null;

        Integer importance = null, daysTillDue = null;

        Double estimatedTime = null;

        Calendar dueDate = Calendar.getInstance();

        while (keepGoing) {
            System.out.println("Give the name of the class");
            while (className.equals("")){
                className = scanner.nextLine();
            }

            System.out.println("Give the name of the Assignment");
            while (assignmentName.equals("")) {
                assignmentName = scanner.nextLine();
            }
            System.out.println("A short description");
            while(description.equals("")){
                description = scanner.nextLine();
            }

            System.out.println("How many days till its due?");
            while(daysTillDue == null){
                daysTillDue = scanner.nextInt();
            }
            dueDate.add(dueDate.DATE, daysTillDue);

            System.out.println("Rank its importance from 1-10");
            while (importance == null) {
                importance = scanner.nextInt();
            }

            System.out.println("How long do you think it will take in hours?");
            while (estimatedTime == null) {
                estimatedTime = scanner.nextDouble();
            }

            System.out.println("What category does it fall under?");
            System.out.println("a) Exam");
            System.out.println("b) Test/Quiz");
            System.out.println("c) Homework/Assignment");
            System.out.println("d) Project/Presentation");
            System.out.println("e) Reading");
            System.out.println("f) Other");

            while (letter.equals("")){
                letter = scanner.next();
            }

            while (type == null) {

                letter.toLowerCase();

                switch (letter) {
                    case ("a"):
                        type = "Exam";
                        break;
                    case ("b"):
                        type = "Test/Quiz";
                        break;
                    case ("c"):
                        type = "Homework/Assignment";
                        break;
                    case ("d"):
                        type = "Project/Presentation";
                        break;
                    case ("e"):
                        type = "Reading";
                        break;
                    case ("f"):
                        type = "Other";
                        break;
                    default:
                        System.out.println("Invalid. Try Again");
                        letter = scanner.next();
                }
            }
            System.out.println("Would you like to add another task? (y/n)");
            while (go.equals("")) {
                go = scanner.next();
            }
            //TODO make this have a try again etc
            if (!(go.toLowerCase().equals("y"))) {
                keepGoing = false;
            }
            currentTasks.add(new Task(className, description, dueDate, assignmentName, importance, estimatedTime, type, daysTillDue));
            type = null;
            className = "";
            assignmentName = "";
            description = "";
            letter = "";
            go = "";
            importance = null;
            daysTillDue = null;
            estimatedTime = null;
            dueDate = Calendar.getInstance();
        }
        return currentTasks;
    }

    public static void printOptions(){
        System.out.println("How would you like the tasks listed?");
        System.out.println("a) Due Date");
        System.out.println("b) Importance");
        System.out.println("c) Recommended");
        System.out.println("d) By Class");
        System.out.println("e) Exit");
        System.out.println("f) Add More Classes");
    }

}