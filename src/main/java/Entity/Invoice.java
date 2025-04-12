package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @ManyToMany
    @JoinTable(
            name = "invoice_services",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

    @Column(name = "total_price")
    private double totalPrice;

    public void calculateTotalPrice() {
        double sum = 0;
        for (Service service : services) {
            sum += service.getPrice();
        }
        this.totalPrice = sum;
    }


    @Override
    public String toString() {
        return "Invoice: " +
                "id=" + id +
                ", patient=" + patient +
                ", visit=" + visit +
                ", services=" + services +
                ", totalPrice=" + totalPrice ;
    }
}
