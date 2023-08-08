import java.io.IOException;
import java.util.ArrayList;

interface SkinConsultationManager {
    void addDoctor(ArrayList<Doctor> doctors);
    void deleteDoctor(ArrayList<Doctor> doctors);
    void printthelistofDoctor(ArrayList<Doctor> doctors);
    void savefile(ArrayList<Doctor> doctors) throws IOException;
}
