package Entity;

public enum Specialization {
    AllergyAndImmunology,
    Cardiology,
    Dermatology,
    EmergencyMedicine,
    FamilyMedicine,
    Pediatrics;

    public static void printChoseSpecialization(){
        System.out.println(
                "1 - Allergy And Immunology\n" +
                        "2 - Cardiology\n" +
                        "3 - Dermatology\n" +
                        "4 - EmergencyMedicine\n" +
                        "5 - FamilyMedicine\n" +
                        "6 - Pediatrics");
    }


}