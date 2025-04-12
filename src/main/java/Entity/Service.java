package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "analysis_name")
    private String analysisName;
    @Column(name = "service_name")
    private String name;

    @Column(name = "price")
    private double price;

//    @ManyToOne
//    @JoinColumn(name = "visit_id")
//        private Visit visit;

    @ManyToOne(fetch = FetchType.EAGER)  // <-- Important
    @JoinColumn(name = "visit_id")
    private Visit visit;


//    @Override
//    public String toString() {
//        return "Service{" +
//                "id=" + id +
//                ", analysisName='" + analysisName + '\'' +
//                ", price=" + price +
//                ", patient=" + visit.getPatient().getName() +
//                ", doctor=" + visit.getDoctor().getName() +
//                ", visitDate=" + visit.getVisitDate() +
//                '}';
//    }

    @Override
    public String toString() {
        String patientName = (visit != null && visit.getPatient() != null) ? visit.getPatient().getName() : "N/A";
        return "Service{" +
                "id=" + id +
                ", analysisName='" + analysisName + '\'' +
                ", price=" + price +
                ", patient=" + patientName +
                '}';
    }

}

