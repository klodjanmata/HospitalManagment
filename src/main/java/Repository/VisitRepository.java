package Repository;

import Entity.Visit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class VisitRepository {
public void save(Visit visit) {
    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction tx = session.beginTransaction();
        session.save(visit);
        tx.commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public List<Visit> findByPatientId(int patientId){
    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Visit> cq = cb.createQuery(Visit.class);
        Root<Visit> root = cq.from(Visit.class);
        cq.select(root).where(cb.equal(root.get("patientId"), patientId));

    return session.createQuery(cq).getResultList();}
}

public List<Visit> findByDoctorId(int doctorId){
    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Visit> cq = cb.createQuery(Visit.class);
        Root<Visit> root = cq.from(Visit.class);
        cq.select(root).where(cb.equal(root.get("doctorId"), doctorId));

        return session.createQuery(cq).getResultList();
    }
}

    public void exportVisitsToCSV(List<Visit> visits, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("VisitID,PatientName,DoctorName,Diagnosis,VisitDate\n");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            for (Visit visit : visits) {
                String line = String.format(
                        "%d,%s,%s,%s,%s\n",
                        visit.getId(),
                        visit.getPatient().getName(),
                        visit.getDoctor().getName(),
                        visit.getDiagnosis(),
                        formatter.format(visit.getVisitDate())
                );
                writer.write(line);
            }

            System.out.println("Visit history exported successfully to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
