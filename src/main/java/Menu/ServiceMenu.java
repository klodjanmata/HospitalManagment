package Menu;

import Entity.Service;
import Entity.Visit;
import Repository.ServiceRepository;
import Repository.VisitRepository;

import java.util.List;
import java.util.Scanner;

public class ServiceMenu {
    private static final ServiceRepository serviceRepo = new ServiceRepository();
    private static final VisitRepository visitRepo = new VisitRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- SERVICE MANAGEMENT ---");
            System.out.println("1. Add Analysis to Visit");
            System.out.println("2. View Analyses by Visit");
            System.out.println("3. View All Analyses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addService(scanner);
                    break;
                case "2":
                    viewByVisit(scanner);
                    break;
                case "3":
                    viewAll();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addService(Scanner scanner) {
        try {
            System.out.print("Enter Visit ID: ");
            int visitId = Integer.parseInt(scanner.nextLine());
            Visit visit = visitRepo.getVisitById(visitId);

            if (visit == null) {
                System.out.println("Visit not found.");
                return;
            }

            boolean addMore = true;

            while (addMore) {
                Service service = new Service();
                service.setVisit(visit);

                System.out.print("Enter analysis name: ");
                service.setAnalysisName(scanner.nextLine());

                System.out.print("Enter price: ");
                service.setPrice(Double.parseDouble(scanner.nextLine()));

                serviceRepo.save(service);
                System.out.println("Analysis added successfully!");

                System.out.print("Do you want to add another analysis? (yes/no): ");
                String response = scanner.nextLine();
                addMore = response.equalsIgnoreCase("yes");
            }
        } catch (Exception e) {
            System.out.println("Error adding analysis.");
            e.printStackTrace();
        }
    }

    private static void viewByVisit(Scanner scanner) {
        System.out.print("Enter Visit ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Service> services = serviceRepo.findByVisitId(id);

        if (services.isEmpty()) {
            System.out.println("No analyses found for this visit.");
        } else {
            services.forEach(System.out::println);
        }
    }

    private static void viewAll() {
        List<Service> services = serviceRepo.findAll();

        if (services.isEmpty()) {
            System.out.println("No analyses found.");
        } else {
            services.forEach(System.out::println);
        }
    }
}
