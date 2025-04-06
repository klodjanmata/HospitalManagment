package Menu;

import Entity.Patient;
import Repository.PatientRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PatientMenu {
    private static final PatientRepository patientRepo = new PatientRepository();
    private List<Patient> patients;

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
        System.out.println("\n--- PATIENT MANAGEMENT ---");
        System.out.println("1. Add New Patient");
        System.out.println("2. Update Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. View All Patients");
        System.out.println("5. Search Patient by ID");
        System.out.println("0. Back to Main Menu");
        System.out.print("Your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addPatient(scanner);
                break;
            case "2":
                updatePatient(scanner);
                break;
            case "3":
                deletePatient(scanner);
                break;
            case "4":
                viewAllPatients();
                break;
            case "5":
                searchPatientById(scanner);
                break;
            case "0":
                back = true;
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }
}

    private static void addPatient(Scanner scanner) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter surname: ");
            String surname = scanner.nextLine();

            System.out.print("Enter date of birth (yyyy-MM-dd): ");
            String dateOfBirthStr = scanner.nextLine();
            Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthStr);

            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter address: ");
            String address = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            Patient patient = new Patient();
            patient.setName(name);
            patient.setSurname(surname);
            patient.setDateOfBirth(dateOfBirth);
            patient.setPhone(phone);
            patient.setAddress(address);
            patient.setEmail(email);

            patientRepo.save(patient);
            System.out.println("Patient added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding patient.");
            e.printStackTrace();
        }
    }

    private static void updatePatient(Scanner scanner) {
        try {
            System.out.print("Enter patient ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            Patient patient = patientRepo.getPatientById(id);
            if (patient == null) {
                System.out.println("Patient not found.");
                return;
            }

            System.out.print("Enter new name (" + patient.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) patient.setName(name);

            System.out.print("Enter new surname (" + patient.getSurname() + "): ");
            String surname = scanner.nextLine();
            if (!surname.isEmpty()) patient.setSurname(surname);

            System.out.print("Enter new date of birth (" + patient.getDateOfBirth() + ") [yyyy-MM-dd]: ");
            String dateOfBirthStr = scanner.nextLine();
            if (!dateOfBirthStr.isEmpty()) {
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthStr);
                patient.setDateOfBirth(dob);
            }

            System.out.print("Enter new phone (" + patient.getPhone() + "): ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) patient.setPhone(phone);

            System.out.print("Enter new address (" + patient.getAddress() + "): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) patient.setAddress(address);

            System.out.print("Enter new email (" + patient.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) patient.setEmail(email);

            patientRepo.update(patient);
            System.out.println("Patient updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating patient.");
            e.printStackTrace();
        }
    }

    private static void deletePatient(Scanner scanner) {
        System.out.print("Enter patient ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Patient patient = patientRepo.getPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patientRepo.delete(patient);
        System.out.println("Patient deleted successfully.");
    }

    private static void viewAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            patients.forEach(System.out::println);
        }
    }

    private static void searchPatientById(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Patient patient = patientRepo.getPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println(patient);
        }
    }


    public void printAllPatient () {
            System.out.println("ID\tName\tSurname\tDateOfBirth\t\tPhone\tAddress\tEmail");
            if (patients != null) {
                patients.forEach(System.out::println);
            }
        }
    }