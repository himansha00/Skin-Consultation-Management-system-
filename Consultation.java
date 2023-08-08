import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Consultation implements Serializable {
    private static final int noofpatients = 4;
    private int cost;
    private String notes;
    private String aDate;
    private boolean isfull = false;
    private int consultationID;
    private int starttime;
    private int endtime;
    private String relevantdoc;
    private String name;
    private String surname;
    private int year;
    private int month;
    private int date;
    private int mobilenumber;
    private String gender;
    private int patientId;
    static ArrayList<Consultation> consultationsstore = new ArrayList<>();
    private ArrayList<Patient> patientstore;
    private int consultationtime;

    public Consultation(int cost, String notes, String aDate, boolean isfull, int consultationID, int starttime, int endtime, String relevantdoc, ArrayList<Patient> patientstore, int consultationtime) {
        this.cost = cost;
        this.notes = notes;
        this.aDate = aDate;
        this.isfull = isfull;
        this.consultationID = consultationID;
        this.starttime = starttime;
        this.endtime = endtime;
        this.relevantdoc = relevantdoc;
        this.patientstore = patientstore;
        this.consultationtime = consultationtime;
    }

    public int getConsultationtime() {
        return consultationtime;
    }

    public void setConsultationtime(int consultationtime) {
        this.consultationtime = consultationtime;
    }

    public ArrayList<Patient> getPatientstore() {
        return patientstore;
    }

    public void setPatientstore(ArrayList<Patient> patientstore) {
        this.patientstore = patientstore;
    }

    public int getStarttime() {
        return starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public String getRelevantdoc() {
        return relevantdoc;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public void setRelevantdoc(String relevantdoc) {
        this.relevantdoc = relevantdoc;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    public String getaDate() {
        return aDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void appointmentdate() throws Exception{
        Date date = new SimpleDateFormat().parse(getaDate());
    }

    public boolean isIsfull() {
        return isfull;
    }

    public void setIsfull(boolean isfull) {
        this.isfull = isfull;
    }

    public int getConsultationID() {
        return consultationID;
    }

    public void setConsultationID(int consultationID) {
        this.consultationID++;
        //this.consultationID = consultationID;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "cost=" + cost +
                ", notes='" + notes + '\'' +
                ", aDate='" + aDate + '\'' +
                ", isfull=" + isfull +
                ", consultationID=" + consultationID +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", relevantdoc='" + relevantdoc + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", date=" + date +
                ", mobilenumber=" + mobilenumber +
                ", gender='" + gender + '\'' +
                ", patientId=" + patientId +
                ", patientstore=" + patientstore +
                ", consultationtime=" + consultationtime +
                '}';
    }
}