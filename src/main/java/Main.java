import java.util.Scanner;
import Menu.PatientMenu;
import Menu.DoctorMenu;
import Menu.VisitMenu;
import Menu.ImportExportMenu;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== HOSPITAL MANAGEMENT SYSTEM ======");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Visit Management");
            System.out.println("4. Import/Export Data");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    PatientMenu.start(scanner);
                    break;
                case "2":
                    DoctorMenu.start(scanner);
                    break;
                case "3":
                    VisitMenu.start(scanner);
                    break;
                case "4":
                    ImportExportMenu.start(scanner);
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

