package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "Doctor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phoneNumber")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "specialization")
    @Enumerated(EnumType.STRING)
    private Specialization speciality;

    @Override
    public String toString() {
        return
                " " + id + '\t' +
                        " " + name + '\t' +
                        " " + surname + '\t' +
                        " " + phone + '\t' +
                        " " + email + '\t' +
                        " " + speciality;
    }
}
