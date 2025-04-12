package Repository;

import Entity.Invoice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class InvoiceRepository {

    public void save(Invoice invoice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            invoice.calculateTotalPrice();
            Transaction transaction = session.beginTransaction();
            session.save(invoice);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Invoice invoice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            invoice.calculateTotalPrice();
            Transaction transaction = session.beginTransaction();
            session.merge(invoice);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Invoice invoice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(invoice);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Invoice getInvoiceById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Invoice.class, id);
        }
    }

    public List<Invoice> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
            Root<Invoice> root = cq.from(Invoice.class);
            cq.select(root);

            return session.createQuery(cq).getResultList();
        }
    }
}
