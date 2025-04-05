package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "visits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "visit_date")
    @Temporal(TemporalType.DATE)
    private Date visitDate;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "prescription")
    private String prescription;

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() +
                ", visitDate=" + visitDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                '}';
    }
}

