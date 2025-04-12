package Repository;

import Entity.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServiceRepository {

    public void save(Service service) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(service);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Service> findByVisitId(int visitId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Service WHERE visit.id = :visitId", Service.class)
                    .setParameter("visitId", visitId)
                    .getResultList();
        }
    }

    public List<Service> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Service", Service.class).list();
        }
    }

    public void exportServicesToCSV(List<Service> services, String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println("Service ID,Visit ID,Service Name,Price");

            for (Service service : services) {
                writer.println(service.getId() + "," + service.getVisit().getId() + "," + service.getAnalysisName() + "," + service.getPrice());
            }

            System.out.println("Services exported successfully to " + path);

        } catch (IOException e) {
            System.out.println("Error exporting services to CSV.");
            e.printStackTrace();
        }
    }
}
