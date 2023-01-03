import java.util.List;
import java.util.*;


public class Main {


    public static void main(String[] args) {

        List<Task> allTasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Give me a task in a class and I can sort them and give a recommended schedule to complete them");

        allTasks = addTasks(allTasks);

        printOptions();

        String taskOrder = scanner.nextLine();

        boolean wantToExit = false, invalidOption = false;
        int descriptionLength = allTasks.stream().map(a -> a.getDescription().length()).max(Integer::compare).get();
        if(descriptionLength < 11){
            descriptionLength = 11;
        }
        String description = "%-" + descriptionLength + "s";

        int classNameLength = allTasks.stream().map(a -> a.getClassName().length()).max(Integer::compare).get();
        if(classNameLength < 5){
            classNameLength = 5;
        }
        String className = "%-" + classNameLength + "s";

        int assignmentNameLength = allTasks.stream().map(a -> a.getTaskName().length()).max(Integer::compare).get();
        if(assignmentNameLength < 4){
            assignmentNameLength = 4;
        }
        String assignmentName = "%-" + assignmentNameLength + "s";

        int typeNameLength = allTasks.stream().map(a -> a.getType().length()).max(Integer::compare).get();
        if(typeNameLength < 4){
            typeNameLength = 4;
        }
        String typeName = "%-" + typeNameLength + "s";

        while (!wantToExit) {
            System.out.printf(assignmentName + " | " + "%-20s" + " | " + className + " | " + typeName
                    + " | " + "%-10s | " + description + "\n", "Task", "Date Due", "Class", "Type", "Importance", "Description");
            for(int i = 0; i < typeNameLength+assignmentNameLength+classNameLength+descriptionLength+45; i++){
                System.out.printf("-");
            }

            switch (taskOrder) {
                case ("a"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getDueDate))
                            .forEach(s -> System.out.printf("\n" + assignmentName + " | " + "%-20s" + " | " + className
                                    + " | " + typeName + " | " + "%-10d" + " | " + description, s.getTaskName(),
                                    s.getFullDate(), s.getClassName(), s.getType(),
                                    s.getImportance(), s.getDescription()));
                    break;
                case ("b"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getImportance))
                            .forEach(s -> System.out.printf("\n" + assignmentName + " | " + "%-20s" + " | " + className
                                            + " | " + typeName + " | " + "%-10d" + " | " + description, s.getTaskName(),
                                    s.getFullDate(), s.getClassName(), s.getType(),
                                    s.getImportance(), s.getDescription()));
                    break;
                case ("c"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getRecommendedOrder))
                            .forEach(s -> System.out.printf("\n" + assignmentName + " | " + "%-20s" + " | " + className
                                            + " | " + typeName + " | " + "%-10d" + " | " + description, s.getTaskName(),
                                    s.getFullDate(), s.getClassName(), s.getType(),
                                    s.getImportance(), s.getDescription()));
                    break;
                case ("d"):
                    allTasks.stream().sorted(Comparator.comparing(Task::getClassName)).sorted()
                            .forEach(s -> System.out.printf("\n" + assignmentName + " | " + "%-20s" + " | " + className
                                            + " | " + typeName + " | " + "%-10d" + " | " + description, s.getTaskName(),
                                    s.getFullDate(), s.getClassName(), s.getType(),
                                    s.getImportance(), s.getDescription()));
                    break;
                case ("e"):
                    wantToExit = true;
                    break;
                case ("f"):
                    addTasks(allTasks);
                    printOptions();
                    break;
                default:
                    System.out.println("\nInvalid. Try Again");
                    invalidOption = true;
            }

            if(!wantToExit || invalidOption){
                System.out.println("\nFeel free to pick another option");
                taskOrder = null;
                while (taskOrder == null) {
                    taskOrder = scanner.nextLine();
                }
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
                if(!go.toLowerCase().equals("y") || !go.toLowerCase().equals("n")){
                    go = "";
                    System.out.println("Invalid Input");
                }
            }
            if ((go.toLowerCase().equals("n"))) {
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