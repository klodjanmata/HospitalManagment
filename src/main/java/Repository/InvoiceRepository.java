package Repository;

import Entity.Invoice;
import Entity.Visit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
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

    public void exportInvoiceToCSV(List<Invoice> invoices, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("PatientID,VisitID,ServicesId,TotalPrice\n");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            for (Invoice  invoice : invoices) {
                String line = String.format(
                        "%d,%s,%s,%s,%s\n",
                        invoice.getId(),
                        invoice.getPatient().getName(),
                        invoice.getVisit().getId(),
                        invoice.getServices(),
                        invoice.getTotalPrice()

                );
                writer.write(line);
            }

            System.out.println("Invoice exported successfully to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Invoice> findByPatientId(int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
            Root<Invoice> root = cq.from(Invoice.class);
            cq.select(root).where(cb.equal(root.get("patient").get("id"), patientId));

        return session.createQuery(cq).getResultList();
        }

    }

    public List<Invoice> findByDoctorId(int doctorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
            Root<Invoice> root = cq.from(Invoice.class);
            cq.select(root).where(cb.equal(root.get("doctor").get("id"), doctorId));
            return session.createQuery(cq).getResultList();
        }
    }
}
