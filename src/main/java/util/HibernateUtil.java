package util;

import Entity.Doctor;
import Entity.Patient;
import Entity.Visit;
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

