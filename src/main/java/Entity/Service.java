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

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "visit_id")
        private Visit visit;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", analysisName='" + analysisName + '\'' +
                ", price=" + price +
                ", patient=" + visit.getPatient().getName() +
                ", doctor=" + visit.getDoctor().getName() +
                ", visitDate=" + visit.getVisitDate() +
                '}';
    }
}

