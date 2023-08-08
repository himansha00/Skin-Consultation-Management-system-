import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String surname;
    private int year;
    private int month;
    private int date;
    private int mobilenumber;
    private String gender;


    public Person(String name, String surname, int year, int month, int date, int mobilenumber, String gender){
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.month = month;
        this.date = date;
        this.mobilenumber = mobilenumber;
        this.gender = gender;

    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getMobilenumber() {
        return mobilenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setMobilenumber(int mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", date=" + date +
                ", mobilenumber=" + mobilenumber +
                ", gender='" + gender + '\'' +
                '}';
    }
}
