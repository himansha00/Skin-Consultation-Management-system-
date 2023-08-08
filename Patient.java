import java.io.Serializable;

public class Patient extends Person implements Serializable {
    private int patientId;

    public Patient(String name, String surname, int year, int month, int date, int mobilenumber, int patientId, String gender) {
        super(name, surname, year, month, date, mobilenumber, gender);
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                '}' + super.toString();
    }
}
