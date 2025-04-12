package Menu;

import Entity.Service;
import Repository.ServiceRepository;

import java.util.List;
import java.util.Scanner;

public class ServiceMenu {

    private static final ServiceRepository serviceRepo = new ServiceRepository();

    public static void start(Scanner scanner) {
        boolean back = false;while (!back) {
            System.out.println("\n--- SERVICE MANAGEMENT ---");
            System.out.println("1. Add New Service");
            System.out.println("2. Update Service");
            System.out.println("3. Delete Service");
            System.out.println("4. View All Services");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addService(scanner);
                    break;
                case "2":
                    updateService(scanner);
                    break;
                case "3":
                    deleteService(scanner);
                    break;
                case "4":
                    viewAllServices();
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

            System.out.print("Enter service name: ");
            String name = scanner.nextLine();
            service.setName(name);

            System.out.print("Enter service price: ");
            double price = Double.parseDouble(scanner.nextLine());
            service.setPrice(price);

            System.out.println("Do you want to enter a new service? (yes/no)");
            String choice = scanner.nextLine();
            if(choice.equals("yes")){
                System.out.print("Enter service name: ");
                String name1 = scanner.nextLine();
                service.setName(name1);
                System.out.print("Enter service price: ");
                double price1 = Double.parseDouble(scanner.nextLine());
                service.setPrice(price1);
            }


            serviceRepo.save(service);
            System.out.println("Service added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding service.");
            e.printStackTrace();
        }
    }

    private static void updateService(Scanner scanner) {
        try {
            System.out.print("Enter service ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            Service service = serviceRepo.getServiceById(id);
            if (service == null) {
                System.out.println("Service not found.");
                return;
            }

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                service.setName(name);
            }
            System.out.print("Enter new price (-1 to keep current): ");
            double price = Double.parseDouble(scanner.nextLine());
            if (price >= 0) {
                service.setPrice(price);
            }

            serviceRepo.update(service);
            System.out.println("Service updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating service.");
            e.printStackTrace();
        }
    }
    private static void deleteService(Scanner scanner) {
        try {
            System.out.print("Enter service ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            Service service = serviceRepo.getServiceById(id);
            if (service == null) {
                System.out.println("Service not found.");
                return;
            }

            serviceRepo.delete(service);
            System.out.println("Service deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting service.");
            e.printStackTrace();
        }
    }

    private static void viewAllServices() {
        List<Service> services = serviceRepo.findAll();
        if (services.isEmpty()) {
            System.out.println("No services found.");
        } else {
            System.out.println("Available Services:");
            services.forEach(service -> System.out.println(service));
        }
    }
}