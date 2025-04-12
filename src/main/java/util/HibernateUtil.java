package util;

import Entity.*;
import Menu.DoctorMenu;
import Menu.PatientMenu;
import Menu.ServiceMenu;
import Menu.VisitMenu;
import Repository.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try{
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Doctor.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Visit.class)
                    .addAnnotatedClass(DoctorMenu.class)
                    .addAnnotatedClass(VisitMenu.class)
                    .addAnnotatedClass(PatientMenu.class)
                    .addAnnotatedClass(DoctorRepository.class)
                    .addAnnotatedClass(VisitRepository.class)
                    .addAnnotatedClass(PatientRepository.class)
                    .addAnnotatedClass(Service.class)
                    .addAnnotatedClass(Invoice.class)
                    .addAnnotatedClass(ServiceRepository.class)
                    .addAnnotatedClass(ServiceMenu.class)
                    .addAnnotatedClass(InvoiceRepository.class)

                    .buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}

