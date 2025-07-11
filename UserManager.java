package AgeCalculatorProject;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
class User {
    private String name;
    private LocalDate birthDate;
    public User(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getAgeDetails() {
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        return age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days";
    }
    @Override
    public String toString() {
        return "Name: " + name + " | DOB: " + birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                + " | Age: " + getAgeDetails();
    }
}
public class UserManager {
    private static List<User> userList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- User Manager ---");
            System.out.println("1. Add User");
            System.out.println("2. View All Users (Sorted by Name)");
            System.out.println("3. View All Users (Sorted by Age)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    viewUsersSortedByName();
                    break;
                case "3":
                    viewUsersSortedByAge();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void addUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Date of Birth (dd-MM-yyyy): ");
        String dobStr = scanner.nextLine();

        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. User not added.");
            return;
        }

        userList.add(new User(name, dob));
        System.out.println("User added successfully.");
    }

    private static void viewUsersSortedByName() {
        if (userList.isEmpty()) {
            System.out.println("No users to show.");
            return;
        }
        List<User> sortedList = new ArrayList<>(userList);
        sortedList.sort(Comparator.comparing(User::getName));

        System.out.println("\n-- Users Sorted by Name --");
        sortedList.forEach(System.out::println);
    }
    private static void viewUsersSortedByAge() {
        if (userList.isEmpty()) {
            System.out.println("No users to show.");
            return;
        }
        List<User> sortedList = new ArrayList<>(userList);
        sortedList.sort(Comparator.comparing(User::getBirthDate)); 

        System.out.println("\n-- Users Sorted by Age (Oldest to Youngest) --");
        sortedList.forEach(System.out::println);
    }
}
