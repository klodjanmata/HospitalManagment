package Entity;

import java.util.Date;

public class Patient {
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String phone;
    private String address;
    private String email;


    public Patient(int id, String name, String surname, Date dateOfBirth, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
