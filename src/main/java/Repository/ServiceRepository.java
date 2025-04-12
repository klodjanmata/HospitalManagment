package Repository;

import Entity.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ServiceRepository {

    public void save(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.save(service);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(service);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.remove(service);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Service getServiceById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Service.class, id);
        }
    }

    public List<Service> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Service> cq = cb.createQuery(Service.class);
            Root<Service> root = cq.from(Service.class);
            cq.select(root);

            return session.createQuery(cq).getResultList();
        }
    }
}
