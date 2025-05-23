package Menu;

import Entity.Specialization;
import Repository.DoctorRepository;
import Entity.Doctor;
import java.util.List;
import java.util.Scanner;

public class DoctorMenu {
    private static final DoctorRepository doctorRepo = new DoctorRepository();
    private List<Doctor> doctors;

    public static void start(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- DOCTOR MANAGEMENT ---");
            System.out.println("1. Add New Doctor");
            System.out.println("2. Update Doctor Details");
            System.out.println("3. Delete Doctor");
            System.out.println("4. View Doctor List");
            System.out.println("5. View Doctor Specialties");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addDoctor(scanner);
                    break;
                case "2":
                    updateDoctor(scanner);
                    break;
                case "3":
                    deleteDoctor(scanner);
                    break;
                case "4":
                    viewDoctorList();
                    break;
                case "5":
                    viewDoctorSpecialties(scanner);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addDoctor(Scanner scanner) {
        try {
            Doctor doctor = new Doctor();
            System.out.print("Enter doctor name: ");
            String name = scanner.nextLine();
            doctor.setName(name);

            System.out.print("Enter doctor surname: ");
            String surname = scanner.nextLine();
            doctor.setSurname(surname);

            System.out.print("Enter the phone number:");
            String phone = scanner.nextLine();
            doctor.setPhone(phone);

            System.out.print("Enter the email:");
            String email = scanner.nextLine();
            doctor.setEmail(email);

            System.out.print("Enter doctor specialty: Choose a number from 1 to 6:\n");
            Specialization.printChoseSpecialization();
            int choice = scanner.nextInt();
            doctor.setSpeciality(Specialization.values()[choice - 1]);

            doctorRepo.save(doctor);
            System.out.println("Doctor added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding doctor.");
            e.printStackTrace();
        }
    }

    private static void updateDoctor(Scanner scanner) {
        try {
            System.out.print("Enter doctor ID to update: ");
            int doctorId = Integer.parseInt(scanner.nextLine());

            Doctor doctor = doctorRepo.getDoctorById(doctorId);
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                doctor.setName(name);
            }

            System.out.println("Enter the surname");
            String surname = scanner.nextLine();
            if (!surname.isEmpty()) {
                doctor.setSurname(surname);
            }

            System.out.print("Enter new specialty,choose a number from 1 to 6: ");
            Specialization.printChoseSpecialization();
            int choice = scanner.nextInt();
            doctor.setSpeciality(Specialization.values()[choice - 1]);


            System.out.print("Enter new contact information (leave blank to keep current): ");
            String phone = scanner.nextLine();
            String email = scanner.nextLine();
            if (!phone.isEmpty() || !email.isEmpty()) {
                doctor.setPhone(phone);
                doctor.setEmail(email);
            }

            doctorRepo.update(doctor);
            System.out.println("Doctor updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating doctor.");
            e.printStackTrace();
        }
    }

    private static void deleteDoctor(Scanner scanner) {
        try {
            System.out.print("Enter doctor ID to delete: ");
            int doctorId = Integer.parseInt(scanner.nextLine());

            Doctor doctor = doctorRepo.getDoctorById(doctorId);
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }

            doctorRepo.delete(doctor);
            System.out.println("Doctor deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting doctor.");
            e.printStackTrace();
        }
    }

    private static void viewDoctorList() {
        List<Doctor> doctors = doctorRepo.findAll();

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            doctors.forEach(doctor -> System.out.println(doctor));
        }
    }

    private static void viewDoctorSpecialties(Scanner scanner) {
        System.out.println("Doctor Specialties:");
        List<Specialization> specialties = doctorRepo.findAllSpecialties();

        if (specialties.isEmpty()) {
            System.out.println("No specialties found.");
        } else {
            specialties.forEach(System.out::println);
        }
    }

    public void printAllDoctors(){
        System.out.println("ID\tName\tSurname\tSpeciality\tPhone\tEmail");
        if ( doctors != null) {
            doctors.forEach(System.out::println);
        }
    }
}