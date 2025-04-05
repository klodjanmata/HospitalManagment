package Repository;

import Entity.Doctor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


import java.util.List;

public class DoctorRepository {

        public void save(Doctor doctor){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(doctor);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void delete(Doctor doctor){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.remove(doctor);
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void update(Doctor doctor){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(doctor);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Doctor getDoctorById(int id){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                return session.find(Doctor.class, id);
            }
        }

        public List<Doctor> findAll(){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
                Root<Doctor> root = cq.from(Doctor.class);
                cq.select(root);

                return session.createQuery(cq).getResultList();
            }
        }

    public List<String> findAllSpecialties() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> cq = cb.createQuery(String.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root.get("specialty")).distinct(true);
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
