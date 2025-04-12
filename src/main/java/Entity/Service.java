package Entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "service_name")
    private String name;

    @Column(name = "price")
    private double price;

    @Override
    public String toString() {
        return "Service: " +
                "id=" + id +
                ", name='" + name + '\t' +
                ", price=" + price ;
    }
}






