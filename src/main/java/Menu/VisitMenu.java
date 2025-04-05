package Menu;

import Repository.VisitRepository;
import Repository.PatientRepository;
import Repository.DoctorRepository;
import Entity.Visit;
import Entity.Patient;
import Entity.Doctor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VisitMenu {
    private static final VisitRepository visitRepo = new VisitRepository();
    private static final PatientRepository patientRepo = new PatientRepository();
    private static final DoctorRepository doctorRepo = new DoctorRepository();

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- VISIT MANAGEMENT ---");
            System.out.println("1. Record New Visit");
            System.out.println("2. View Visit History by Patient");
            System.out.println("3. View Visit History by Doctor");
            System.out.println("4. Export Visit History to CSV");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    recordVisit(scanner);
                    break;
                case "2":
                    viewVisitsByPatient(scanner);
                    break;
                case "3":
                    viewVisitsByDoctor(scanner);
                    break;
                case "4":
                    exportVisitsToCSV(scanner);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void recordVisit(Scanner scanner) {
        try {
            System.out.print("Enter patient ID: ");
            int patientId = Integer.parseInt(scanner.nextLine());
            Patient patient = patientRepo.getPatientById(patientId);
            if (patient == null) {
                System.out.println("Patient not found.");
                return;
            }

            System.out.print("Enter doctor ID: ");
            int doctorId = Integer.parseInt(scanner.nextLine());
            Doctor doctor = doctorRepo.getDoctorById(doctorId);
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }

            System.out.print("Enter diagnosis: ");
            String diagnosis = scanner.nextLine();

            System.out.print("Enter prescription (optional): ");
            String prescription = scanner.nextLine();

            Date visitDate = new Date(); // current date

            Visit visit = new Visit();
            visit.setPatient(patient);
            visit.setDoctor(doctor);
            visit.setDiagnosis(diagnosis);
            visit.setPrescription(prescription);
            visit.setVisitDate(visitDate);

            visitRepo.save(visit);
            System.out.println("Visit recorded successfully.");
        } catch (Exception e) {
            System.out.println("Error recording visit.");
            e.printStackTrace();
        }
    }

    private static void viewVisitsByPatient(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Visit> visits = visitRepo.findByPatientId(id);

        if (visits.isEmpty()) {
            System.out.println("No visits found for this patient.");
        } else {
            visits.forEach(System.out::println);
        }
    }

    private static void viewVisitsByDoctor(Scanner scanner) {
        System.out.print("Enter doctor ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Visit> visits = visitRepo.findByDoctorId(id);

        if (visits.isEmpty()) {
            System.out.println("No visits found for this doctor.");
        } else {
            visits.forEach(System.out::println);
        }
    }

    private static void exportVisitsToCSV(Scanner scanner) {
        System.out.println("Export visit history to CSV:");
        System.out.println("1. By Patient ID");
        System.out.println("2. By Doctor ID");
        System.out.print("Choose an option: ");
        String option = scanner.nextLine();

        List<Visit> visits = null;
        switch (option) {
            case "1":
                System.out.print("Enter patient ID: ");
                int pid = Integer.parseInt(scanner.nextLine());
                visits = visitRepo.findByPatientId(pid);
                break;
            case "2":
                System.out.print("Enter doctor ID: ");
                int did = Integer.parseInt(scanner.nextLine());
                visits = visitRepo.findByDoctorId(did);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        if (visits == null || visits.isEmpty()) {
            System.out.println("No visits found to export.");
            return;
        }

        System.out.print("Enter CSV file path (e.g., visits.csv): ");
        String path = scanner.nextLine();

        visitRepo.exportVisitsToCSV(visits, path);
    }
}
