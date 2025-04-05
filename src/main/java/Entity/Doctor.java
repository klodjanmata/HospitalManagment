package Entity;



public class Doctor {
    private int id;
    private String name;
    private String surname;
    private String speciality;
    private String phone;
    private String email;

    public Doctor(){}

    public Doctor(int id, String name, String surname, String speciality, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.phone = phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
                " " + id + '\t' +
                        " " + name + '\t' +
                        " " + surname + '\t' +
                        " " + speciality + '\t' +
                        " " + phone + '\t' +
                        " " + email;
    }
}

