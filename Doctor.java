import javax.print.Doc;
import java.io.InputStream;
import java.io.Serializable;

public class Doctor extends Person implements Serializable {
    private int medicallicenseno;
    private String specialisation;
    private String availableday;
    private int starttime;
    private int endtime;
    public Doctor(String name, String surname, int year, int month, int date, int mobilenumber, int medicallicenseno, String specialisation, String availableday, String gender, int starttime, int endtime) {
        super(name, surname, year, month, date, mobilenumber, gender);
        this.medicallicenseno = medicallicenseno;
        this.specialisation = specialisation;
        this.availableday = availableday;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public int getMedicallicenseno() {
        return medicallicenseno;
    }

    public int getStarttime() {
        return starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setMedicallicenseno(int medicallicenseno) {
        this.medicallicenseno = medicallicenseno;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getAvailableday() {
        return availableday;
    }

    public void setAvailableday(String availableday) {
        this.availableday = availableday;
    }

    /*public int consultationtimedifference(){
        return (getEndtime() - getStarttime());
    }*/

    @Override
    public String toString() {
        return "Doctor{" +
                "medicallicenseno=" + medicallicenseno +
                ", specialisation='" + specialisation + '\'' +
                ", availableday='" + availableday + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                '}' + super.toString();
    }
}
