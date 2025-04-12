package Menu;

import Entity.Invoice;
import Entity.Patient;
import Entity.Visit;
import Entity.Service;
import Repository.InvoiceRepository;
import Repository.PatientRepository;
import Repository.VisitRepository;
import Repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceMenu {

    private static final InvoiceRepository invoiceRepo = new InvoiceRepository();
    private static final PatientRepository patientRepo = new PatientRepository();
    private static final VisitRepository visitRepo = new VisitRepository();
    private static final ServiceRepository serviceRepo = new ServiceRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- INVOICE MANAGEMENT ---");
            System.out.println("1. Create New Invoice");
            System.out.println("2. View All Invoices");
            System.out.println("3. Delete Invoice");
            System.out.println("4. Export Invoices to CSV file");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createInvoice(scanner);
                    break;
                case "2":
                    viewAllInvoices();
                    break;
                case "3":
                    deleteInvoice(scanner);
                    break;
                case  "4":
                    exportInvoiceToCSV(scanner);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void exportInvoiceToCSV(Scanner scanner) {
        System.out.println("Export visit history to CSV:");
        System.out.println("1. By Patient ID");
        System.out.println("2. By Doctor ID");
        System.out.print("Choose an option: ");
        String option = scanner.nextLine();

        List<Invoice> invoices = null;
        switch (option) {
            case "1":
                System.out.print("Enter patient ID: ");
                int pid = Integer.parseInt(scanner.nextLine());
                invoices = invoiceRepo.findByPatientId(pid);
                break;
            case "2":
                System.out.print("Enter doctor ID: ");
                int did = Integer.parseInt(scanner.nextLine());
                invoices = invoiceRepo.findByDoctorId(did);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        if (invoices == null || invoices.isEmpty()) {
            System.out.println("No visits found to export.");
            return;
        }

        System.out.print("Enter CSV file path (e.g., visits.csv): ");
        String path = scanner.nextLine();

        invoiceRepo.exportInvoiceToCSV(invoices, path);
    }

//    private static void createInvoice(Scanner scanner) {
//        try {
//            System.out.print("Enter patient ID: ");
//            int patientId = Integer.parseInt(scanner.nextLine());
//            Patient patient = patientRepo.getPatientById(patientId);
//            if (patient == null) {
//                System.out.println("Patient not found.");
//                return;
//            }
//
//            System.out.print("Enter visit ID for the patient: ");
//            int visitId = Integer.parseInt(scanner.nextLine());
//            Visit visit = visitRepo.getVisitById(visitId);
//            if (visit == null) {
//                System.out.println("Visit not found.");
//                return;
//            }
//
//            System.out.println("Select services for this invoice:");
//            List<Service> services = serviceRepo.findAll();
//            if (services.isEmpty()) {
//                System.out.println("No services available.");
//                return;
//            }
//
//            services.forEach(service -> System.out.println(service.getId() + ". " + service.getName() + " - " + service.getPrice() + " EUR"));
//
//            System.out.print("Enter service IDs (comma separated): ");
//            String[] serviceIds = scanner.nextLine().split(",");
//            List<Service> selectedServices = new ArrayList<>();
//            double totalPrice = 0;
//
//            for (String id : serviceIds) {
//                Service service = serviceRepo.getServiceById(Integer.parseInt(id.trim()));
//                if (service != null) {
//                    selectedServices.add(service);
//                    totalPrice += service.getPrice();
//                }
//            }
//
//            Invoice invoice = new Invoice();
//            invoice.setPatient(patient);
//            invoice.setVisit(visit);
//            invoice.setServices(selectedServices);
//            invoice.setTotalPrice(totalPrice);
//
//
//
//            invoiceRepo.save(invoice);
//
//            System.out.println("Invoice created successfully with total: " + totalPrice + " EUR.");
//        } catch (Exception e) {
//            System.out.println("Error creating invoice.");
//            e.printStackTrace();
//        }
//    }
private static void createInvoice(Scanner scanner) {
    try {
        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());
        Patient patient = patientRepo.getPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter visit ID for the patient: ");
        int visitId = Integer.parseInt(scanner.nextLine());
        Visit visit = visitRepo.getVisitById(visitId);
        if (visit == null) {
            System.out.println("Visit not found.");
            return;
        }

        List<Service> allServices = serviceRepo.findAll();
        if (allServices.isEmpty()) {
            System.out.println("No services available.");
            return;
        }

        System.out.println("\nAvailable Services:");
        allServices.forEach(service ->
                System.out.println(service.getId() + ". " + service.getName() + " - " + service.getPrice() + " EUR"));

        List<Service> selectedServices = new ArrayList<>();
        double totalPrice = 0;

        boolean adding = true;
        while (adding) {
            System.out.print("\nEnter the ID of a service the patient has done: ");
            String idStr = scanner.nextLine();
            try {
                int serviceId = Integer.parseInt(idStr.trim());
                Service service = serviceRepo.getServiceById(serviceId);
                if (service != null) {
                    selectedServices.add(service);
                    totalPrice += service.getPrice();
                    System.out.println("Added: " + service.getName() + " - " + service.getPrice() + " EUR");
                } else {
                    System.out.println("Service with ID " + serviceId + " not found.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a number.");
            }

            System.out.print("Do you want to add another service? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            adding = response.equals("yes");
        }

        if (selectedServices.isEmpty()) {
            System.out.println("No services selected. Invoice not created.");
            return;
        }

        Invoice invoice = new Invoice();
        invoice.setPatient(patient);
        invoice.setVisit(visit);
        invoice.setServices(selectedServices);
        invoice.setTotalPrice(totalPrice);

        invoiceRepo.save(invoice);
        System.out.printf("\nInvoice created successfully! Total amount: %.2f EUR\n", totalPrice);

    } catch (Exception e) {
        System.out.println("Error creating invoice.");
        e.printStackTrace();
    }
}



    private static void viewAllInvoices() {
        List<Invoice> invoices = invoiceRepo.findAll();
        if (invoices.isEmpty()) {
            System.out.println("No invoices found.");
        } else {
            System.out.println("Invoices List:");
            invoices.forEach(System.out::println);
        }
    }

    private static void deleteInvoice(Scanner scanner) {
        try {
            System.out.print("Enter invoice ID to delete: ");
            int invoiceId = Integer.parseInt(scanner.nextLine());

            Invoice invoice = invoiceRepo.getInvoiceById(invoiceId);
            if (invoice == null) {
                System.out.println("Invoice not found.");
                return;
            }

            invoiceRepo.delete(invoice);
            System.out.println("Invoice deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting invoice.");
            e.printStackTrace();
        }
    }
}
