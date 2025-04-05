package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;


    @Override
    public String toString() {
        return
                "  " +  id  +  "\t" +
                "  " + name + "\t" +
                "  " + surname + "\t" +
                "  " + dateOfBirth + "\t" +
                "  " + phone + "\t" +
                "  " + address + "\t"  +
                "  " + email + "\t";
    }

}
