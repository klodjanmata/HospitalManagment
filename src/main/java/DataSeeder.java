import Entity.Doctor;
import Entity.Patient;
import Entity.Specialization;
import Entity.Visit;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataSeeder {
    public static void seedDoctors(int count) {
        String[] names = {
                "James", "Mary", "John", "Patricia", "Robert",
                "Jennifer", "Michael", "Linda", "William", "Elizabeth",
                "David", "Barbara", "Richard", "Susan", "Joseph",
                "Jessica", "Thomas", "Sarah", "Charles", "Karen",
                "Christopher", "Nancy", "Daniel", "Lisa", "Matthew",
                "Margaret", "Anthony", "Betty", "Donald", "Sandra",
                "Mark", "Ashley", "Paul", "Kimberly", "Steven",
                "Emily", "Andrew", "Donna", "Joshua", "Michelle"
        };

        String[] surnames = {
                "Smith", "Johnson", "Williams", "Brown", "Jones",
                "Garcia", "Miller", "Davis", "Martinez", "Hernandez",
                "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas",
                "Taylor", "Moore", "Jackson", "Martin", "Lee",
                "Perez", "Thompson", "White", "Harris", "Sanchez"
        };

        String[] emails = {
                "doc1@example.com", "doc2@example.com", "doc3@example.com",
                "med.johnson@clinic.com", "sarah.brown@hospital.org",
                "dr.taylor@medmail.com", "n.wilson@docworld.com",
                "a.martinez@healthcare.net", "helen.moore@medline.com",
                "george.lee@clinicpro.com", "j.smith@hospitalhub.com",
                "k.jones@docplus.org", "m.miller@mdmail.com",
                "d.lopez@doctorline.net", "e.jackson@healingcenter.org",
                "chris.white@medix.com", "l.harris@cliniczone.com",
                "victor.perez@hospitalpro.org", "r.thompson@medconnect.com",
                "x.taylor@medicalzone.net", "z.harris@healthlink.org"
        };

        String[] phones = {
                "123456789", "987654321", "555666777",
                "111222333", "444555666", "777888999",
                "999888777", "666555444", "222333444",
                "101010101", "202020202", "303030303",
                "404040404", "505050505", "606060606",
                "707070707", "808080808", "909090909",
                "312456789", "213987654", "415555777"
        };


        Random rand = new Random();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            for (int i = 0; i < count; i++) {
                Doctor doctor = new Doctor();
                doctor.setName(names[rand.nextInt(names.length)]);
                doctor.setSurname(surnames[rand.nextInt(surnames.length)]);
                doctor.setEmail(emails[rand.nextInt(emails.length)]);
                doctor.setPhone(phones[rand.nextInt(phones.length)]);
                doctor.setSpeciality(Specialization.values()[rand.nextInt(Specialization.values().length)]);

                session.persist(doctor);
            }

            tx.commit();
        }
    }

    public static void seedPatients(int count) {
        String[] names = {
                "James", "Mary", "John", "Patricia", "Robert",
                "Jennifer", "Michael", "Linda", "William", "Elizabeth",
                "David", "Barbara", "Richard", "Susan", "Joseph",
                "Jessica", "Thomas", "Sarah", "Charles", "Karen",
                "Christopher", "Nancy", "Daniel", "Lisa", "Matthew",
                "Margaret", "Anthony", "Betty", "Donald", "Sandra",
                "Mark", "Ashley", "Paul", "Kimberly", "Steven",
                "Emily", "Andrew", "Donna", "Joshua", "Michelle"
        };

        String[] surnames = {
                "Smith", "Johnson", "Williams", "Brown", "Jones",
                "Garcia", "Miller", "Davis", "Martinez", "Hernandez",
                "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas",
                "Taylor", "Moore", "Jackson", "Martin", "Lee",
                "Perez", "Thompson", "White", "Harris", "Sanchez"
        };

        String[] emails = {
                "doc1@example.com", "doc2@example.com", "doc3@example.com",
                "med.johnson@clinic.com", "sarah.brown@hospital.org",
                "dr.taylor@medmail.com", "n.wilson@docworld.com",
                "a.martinez@healthcare.net", "helen.moore@medline.com",
                "george.lee@clinicpro.com", "j.smith@hospitalhub.com",
                "k.jones@docplus.org", "m.miller@mdmail.com",
                "d.lopez@doctorline.net", "e.jackson@healingcenter.org",
                "chris.white@medix.com", "l.harris@cliniczone.com",
                "victor.perez@hospitalpro.org", "r.thompson@medconnect.com",
                "x.taylor@medicalzone.net", "z.harris@healthlink.org"
        };

        String[] phones = {
                "123456789", "987654321", "555666777",
                "111222333", "444555666", "777888999",
                "999888777", "666555444", "222333444",
                "101010101", "202020202", "303030303",
                "404040404", "505050505", "606060606",
                "707070707", "808080808", "909090909",
                "312456789", "213987654", "415555777"
        };

        String[] addresses = {
                "123 Maple Street, New York, NY",
                "456 Oak Avenue, Boston, MA",
                "789 Pine Road, Los Angeles, CA",
                "321 Elm Drive, Chicago, IL",
                "654 Birch Blvd, Houston, TX",
                "987 Cedar Lane, Miami, FL",
                "159 Spruce Way, Seattle, WA",
                "753 Walnut St, Denver, CO",
                "852 Chestnut Ave, Phoenix, AZ",
                "951 Aspen Court, Atlanta, GA",
                "147 Palm Street, San Diego, CA",
                "369 Redwood Road, Orlando, FL",
                "258 Magnolia Blvd, Austin, TX",
                "654 Poplar Drive, Detroit, MI",
                "963 Dogwood Lane, Portland, OR",
                "741 Cypress Way, Minneapolis, MN",
                "357 Hickory Ave, San Jose, CA",
                "159 Sycamore St, Charlotte, NC",
                "951 Willow Blvd, Tampa, FL",
                "123 Acorn Street, Las Vegas, NV",
                "789 Alder Court, San Antonio, TX",
                "246 Juniper Way, Sacramento, CA",
                "135 Fir Avenue, Nashville, TN",
                "579 Bay Laurel Road, Salt Lake City, UT",
                "864 Eucalyptus Street, Baltimore, MD",
                "462 Olive Street, Columbus, OH",
                "302 Sequoia Lane, Indianapolis, IN",
                "610 Beech Drive, Raleigh, NC",
                "908 Tamarack Ave, Kansas City, MO",
                "104 Pecan Blvd, Cincinnati, OH"
        };


        Random rand = new Random();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            for (int i = 0; i < count; i++) {
                Patient patient = new Patient();
                patient.setName(names[rand.nextInt(names.length)]);
                patient.setSurname(surnames[rand.nextInt(surnames.length)]);
                patient.setEmail(emails[rand.nextInt(emails.length)]);
                patient.setAddress(addresses[rand.nextInt(addresses.length)]);
                patient.setPhone(phones[rand.nextInt(phones.length)]);
                patient.setDateOfBirth(new Date());

                session.persist(patient);
            }

            tx.commit();
        }
    }
    public static void seedVisits(int count) {
        String[] diagnoses = {
                "Flu", "Back Pain", "Headache", "Allergy", "Infection",
                "High Blood Pressure", "Cold", "Bronchitis", "Asthma", "Migraine",
                "Chest Pain", "Stomach Ache", "Sprain", "Fatigue", "Anxiety"
        };

        String[] prescriptions = {
                "Paracetamol", "Ibuprofen", "Antibiotics", "Cough Syrup", "Inhaler",
                "Aspirin", "Rest and Hydration", "Vitamin D", "Nasal Spray", "Painkillers",
                "Antihistamines", "Cream", "Antacids", "Eye Drops", "Multivitamins"
        };

        Random rand = new Random();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<Doctor> doctors = session.createQuery("from Doctor", Doctor.class).getResultList();
            List<Patient> patients = session.createQuery("from Patient", Patient.class).getResultList();

            if (doctors.isEmpty() || patients.isEmpty()) {
                System.out.println("Please seed doctors and patients before seeding visits.");
                return;
            }

            for (int i = 0; i < count; i++) {
                Visit visit = new Visit();

                visit.setDoctor(doctors.get(rand.nextInt(doctors.size())));
                visit.setPatient(patients.get(rand.nextInt(patients.size())));
                visit.setDiagnosis(diagnoses[rand.nextInt(diagnoses.length)]);
                visit.setPrescription(prescriptions[rand.nextInt(prescriptions.length)]);

                // Random visit date within the last 2 years
                long now = System.currentTimeMillis();
                long past = now - (rand.nextInt(730) * 24L * 60L * 60L * 1000L); // up to 730 days ago
                visit.setVisitDate(new Date(past));

                session.persist(visit);
            }

            tx.commit();
        }
    }

    public static void main(String[] args) {
        seedDoctors(5);
        seedPatients(10);
        seedVisits(50);
    }
}
