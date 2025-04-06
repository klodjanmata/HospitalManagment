package Entity;

public enum Specialization {
    CARDIOLOGY,
    NEUROLOGY,
    DERMATOLOGY,
    PEDIATRICS,
    ORTHOPEDICS,
    FAMILYMEDICINE;

    public static void printChoseSpecialization() {
        for (int i = 0; i < values().length; i++) {
            System.out.println((i + 1) + ". " + values()[i]);
        }
    }
}
