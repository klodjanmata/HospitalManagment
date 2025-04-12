package Repository;

import Entity.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ServiceRepository {

    public void save(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        }
    }

    public List<Service> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

        }
    }
}
