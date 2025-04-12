package Menu;

import Entity.Service;
import Repository.ServiceRepository;

import java.util.List;
import java.util.Scanner;

public class ServiceMenu {
    private static final ServiceRepository serviceRepo = new ServiceRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- SERVICE MANAGEMENT ---");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addService(scanner);
                    break;
                case "2":
                    break;
                case "3":
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
                Service service = new Service();



                serviceRepo.save(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        int id = Integer.parseInt(scanner.nextLine());

        }
    }

        List<Service> services = serviceRepo.findAll();
        if (services.isEmpty()) {
        } else {
        }
    }
}
