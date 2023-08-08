
import java.io.*;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    private File docList = new File("Doctors57.txt");
    private FileInputStream finput;
    private ObjectInputStream oinput;
    private FileOutputStream fout;
    private ObjectOutputStream oout;

    //Doctor doc = new Doctor(adddoctor, lastname, Birthyear, BirthMont, BirthDate, MobileNo, mln, spec);
    //ArrayList<Doctor> doctors = new ArrayList<>();

    static Scanner Sc = new Scanner(System.in);
    static ArrayList<Doctor> doctors = new ArrayList<>();
    public void manager() throws IOException, ClassNotFoundException {
        WestminsterSkinConsultationManager west = new WestminsterSkinConsultationManager();
        System.out.println("\n***************************************");
        System.out.println("***** " + "\033[1;93m" + "MANAGER CONSOLE" + "\033[0m" + " *****");
        System.out.println("***************************************\n");
        loadfile();
        String Useroption;
        do {
            System.out.println("Choose a option, which mentioned below\n");
            System.out.println("If you want to Add a doctor please enter\"A\"");
            System.out.println("If you want to Delete a doctor please enter\"D\"");
            System.out.println("If you want to Print the list of Doctors please enter\"P\"");
            System.out.println("If you want to Save the information please enter\"S\"");
            System.out.println("If you want to Sort the list please enter\"O\"");
            System.out.println("If you want to Exit the Programme please enter\"Q\"");

            System.out.println("Prompt your option: ");
            Useroption = Sc.next();
            switch (Useroption){
                case "A":
                case "a":
                    west.addDoctor(doctors);
                    break;
                case "D":
                case "d":
                    west.deleteDoctor(doctors);
                    break;
                case "P":
                case "p":
                    west.printthelistofDoctor(doctors);
                    break;
                case "S":
                case "s":
                    west.savefile(doctors);
                    break;
                case "O":
                case "o":
                    west.sortingmanager();
                    break;
                case "Q":
                case "q":
                    break;
                default:
                    System.out.println("\nYou have entered a Invalid Input");
                    System.out.println("------------------------------------");
            }
        }
        while (!Useroption.equalsIgnoreCase("Q"));
    }

    @Override
    public void addDoctor(ArrayList<Doctor> doctors) {
        String lastname ="", spec = "", adddoctor = "", availableday = "", gender = "";
        int Birthyear = 0, BirthMont = 0, BirthDate = 0, MobileNo = 0, mln = 0, starttime = 0, endtime = 0;

        // initializing  CONSTRUCTOR

        Doctor doc = new Doctor(adddoctor, lastname, Birthyear, BirthMont, BirthDate, MobileNo, mln, spec, availableday, gender, starttime, endtime);
        System.out.println("\n***************************************");
        System.out.println("***** " + "\033[1;93m" + "ADDING A DOCTOR" + "\033[0m" + " *****");
        System.out.println("***************************************\n");
        // inititalizing the strings and integers
        while (true){
            boolean Isthere = false; // used boolean flag to check the validation of the doctor
            System.out.println("Enter the medical licesnce no of the doctor: ");
            mln = Sc.nextInt();
            for (Doctor doctor : doctors) {
                if (doctor.getMedicallicenseno() == mln) {
                    System.out.println("Doctor is already added");
                    Isthere = true;
                }
            }
            if (!Isthere){
                doc.setMedicallicenseno(mln);
                break;
            }
        }
        System.out.println("Enter the first name of the Doctor you want to add: ");
        adddoctor = Sc.next();
        adddoctor = adddoctor.toLowerCase(Locale.ROOT);
        System.out.println("Enter the Last name of the Doctor you want to add: ");
        lastname = Sc.next().toLowerCase(Locale.ROOT);

        while (true){
            boolean Isthere = false;
            boolean Iscomplete = true;
            int count = 0;
            System.out.println("Enter the Contact Number of the Doctor you want to add: ");
            MobileNo = Sc.nextInt();
            int countno = MobileNo;
            while(countno != 0){
                Iscomplete = true;
                countno = countno / 10;
                count++;
                if (count != 9){
                    Iscomplete = false;
                    if ( countno == 0){
                        System.out.println("Please enter a valid contact number: ");
                    }
                }
            }
            if (Iscomplete){
                for (Doctor doctor : doctors) {
                    if (doctor.getMobilenumber() == MobileNo) {
                        System.out.println("Entered no is already here please enter a valid number");
                        Isthere = true;
                    }
                }
                if (!Isthere){
                    doc.setMobilenumber(mln);
                    break;
                }
            }
        }
        System.out.println("Enter the Birth Year of the Doctor you want to add: ");
        Birthyear = Sc.nextInt();
        System.out.println("Enter the Birth Month of the Doctor you want to add: ");
        BirthMont = Sc.nextInt();
        System.out.println("Enter the Birthdate of the Doctor you want to add: ");
        BirthDate = Sc.nextInt();
        System.out.println("Enter the Specialisation of the Doctor: ");
        spec = Sc.next();
        System.out.println("Enter the available day of the week");
        availableday = Sc.next();
        System.out.println("Enter the gender of the doctor: ");
        gender = Sc.next();
        System.out.println("Consultation start time: ");
        starttime = Sc.nextInt();
        System.out.println("Consultation end time: ");
        endtime = Sc.nextInt();
        doc.setStarttime(starttime);
        doc.setEndtime(endtime);
        doc.setAvailableday(availableday);
        doc.setName(adddoctor);
        doc.setSurname(lastname);
        doc.setMobilenumber(MobileNo);
        doc.setYear(Birthyear);
        doc.setMonth(BirthMont);
        doc.setDate(BirthDate);
        doc.setGender(gender);
        doc.setSpecialisation(spec);
        boolean flag = false;
        if (!flag){
            doctors.add(doc);
            System.out.println(doctors);
        }
    }

    @Override
    public void deleteDoctor(ArrayList<Doctor> doctors) {
        System.out.println("\n***************************************");
        System.out.println("***** " + "\033[1;93m" + "DELETING A DOCTOR" + "\033[0m" + " *****");
        System.out.println("***************************************\n");
        System.out.println("Enter the name of the Doctor you want to Delete: ");
        String deletedoctor = Sc.next();
        deletedoctor = deletedoctor.toLowerCase(Locale.ROOT);
        boolean flag = false;
        for (int i = 0; i < doctors.size(); i++){
            if (deletedoctor.equalsIgnoreCase(doctors.get(i).getName())){
                System.out.println("\n***************************************");
                System.out.println("***** " + "\u001B[31m"+ " Deleting the Following Information of the Doctor" + "\u001B[0m" +" *****");
                System.out.println("***************************************\n");
                System.out.println("Name of the Doctor: " + doctors.get(i).getName().substring(0,1).toUpperCase() +
                        doctors.get(i).getName().substring(1)+ " " + doctors.get(i).getSurname().substring(0,1).toUpperCase() +
                        doctors.get(i).getSurname().substring(1));
                System.out.println("Contact number of the Doctor: " + doctors.get(i).getMobilenumber());
                System.out.println("Birthdate of the Doctor: " + doctors.get(i).getYear() + "/" + doctors.get(i).getMonth()
                        + "/" + doctors.get(i).getDate());
                System.out.println("Medical license number of the Doctor: " + doctors.get(i).getMedicallicenseno());
                System.out.println("Specialisation of the Doctor: " + doctors.get(i).getSpecialisation().substring(0,1).toUpperCase() +
                        doctors.get(i).getSpecialisation().substring(1));
                String yorn;
                System.out.println("Do you want to remove the above mentioned Doctor if yes press Y or if not press N: ");
                yorn = Sc.next();
                if (yorn.equalsIgnoreCase("y")){
                    flag = true;
                    doctors.remove(i);
                    System.out.println("Doctor removed");
                }
                else {
                    System.out.println("Process canceled ");
                }
            }
        }
        if (!flag){
            System.out.println("Please enter a Valid Name");
        }
    }

    @Override
    public void printthelistofDoctor(ArrayList<Doctor> doctors) {
        /*String lastname ="", spec = "", adddoctor = "", availableday = "", gender = "";
        int Birthyear = 0, BirthMont = 0, BirthDate = 0, MobileNo = 0, mln = 0, starttime = 0, endtime = 0;
        Doctor doc = new Doctor(adddoctor, lastname, Birthyear, BirthMont, BirthDate, MobileNo, mln, spec, availableday, gender, starttime, endtime);
        // doesnt store on arrray
        System.out.println("\n***************************************");
        System.out.println("***** " + "\033[1;93m" + "PRINTING THE LIST OF DOCTOR" + "\033[0m" + " *****");
        System.out.println("***************************************\n");
        if (doctors.size() > 0){
            for (Doctor doctor : doctors) {
                System.out.println("Name of the Doctor: " + doctor.getName().substring(0, 1).toUpperCase() +
                        doctor.getName().substring(1) + " " + doctor.getSurname().substring(0, 1).toUpperCase() +
                        doctor.getSurname().substring(1));
                System.out.println("Contact number of the Doctor: " + doctor.getMobilenumber());
                System.out.println("Birthdate of the Doctor: " + doctor.getYear() + "/" + doctor.getMonth()
                        + "/" + doctor.getDate());
                System.out.println("Medical license number of the Doctor: " + doctor.getMedicallicenseno());
                System.out.println("Specialisation of the Doctor: " + doctor.getSpecialisation().substring(0, 1).toUpperCase() +
                        doctor.getSpecialisation().substring(1));
                System.out.println("Available day of the week: " + doctor.getAvailableday().substring(0, 1).toUpperCase() +
                        doctor.getAvailableday().substring(1));
                System.out.println("Consultation time: " + "Start from: " + doctor.getStarttime() + " -" + doctor.getEndtime());
            }
        }
        else {
            System.out.println("There is no doctors in the list");
        }*/
        if (doctors.size() > 0){
            sortingmanager1();
            System.out.println(doctors);
        }
        else {
            System.out.println("There is no doctors in the list");
        }
    }

    @Override
    public void savefile(ArrayList<Doctor> doctors) throws IOException {
        try {

            //--clear existing data version--
            FileWriter clearFile = new FileWriter(docList);
            clearFile.write("");
            clearFile.close();

            fout=new FileOutputStream(docList,true);
            oout=new ObjectOutputStream(fout);

            //--write new data--
            Iterator itr = doctors.iterator();
            System.out.println(doctors);
            while (itr.hasNext()){
                oout.writeObject((Doctor)itr.next());
            }

            fout.close();
            oout.close();

        }catch (FileNotFoundException e){
            System.out.println("Error Found");
        }
        catch (IOException e){
            System.out.println("Error found");
        }
    }

    public void loadfile() throws IOException {
        try {
            finput =new FileInputStream(docList);
            oinput =new ObjectInputStream(finput);

            while (true){
                try {
                    Doctor newdoccc = (Doctor) oinput.readObject();
                    doctors.add(newdoccc);
                }catch (IOException | ClassNotFoundException e){
                    break;
                }
            }


            finput.close();
            oinput.close();

        }catch (FileNotFoundException e){}
        catch (IOException e) {}

    }

    public List<Doctor> getlistiterator(){
        return(doctors);
    }

    public int arrayiterate(){
        return (doctors.size());
    }

    public String arraydoctors(){
        String displaydoc = null;
        for (Doctor doctor : doctors) {
            displaydoc = ("Dr " + doctor.getName().substring(0, 1).toUpperCase() + doctor.getName().substring(1));
        }

        return displaydoc;
    }

    public void sortingmanager(){
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        System.out.println("Sorted list");
        for (int i = 0; i < doctors.size(); i++){
            System.out.println(doctors.get(i).getName());
        }
    }
    public void sortingmanager1(){
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        System.out.println("Sorted list");
        for (Doctor doctor : doctors) {
            System.out.println("Name of the Doctor: " + doctor.getName().substring(0, 1).toUpperCase() +
                    doctor.getName().substring(1) + " " + doctor.getSurname().substring(0, 1).toUpperCase() +
                    doctor.getSurname().substring(1));
            System.out.println("Contact number of the Doctor: " + doctor.getMobilenumber());
            System.out.println("Birthdate of the Doctor: " + doctor.getYear() + "/" + doctor.getMonth()
                    + "/" + doctor.getDate());
            System.out.println("Medical license number of the Doctor: " + doctor.getMedicallicenseno());
            System.out.println("Specialisation of the Doctor: " + doctor.getSpecialisation().substring(0, 1).toUpperCase() +
                    doctor.getSpecialisation().substring(1));
            System.out.println("Available day of the week: " + doctor.getAvailableday().substring(0, 1).toUpperCase() +
                    doctor.getAvailableday().substring(1));
            System.out.println("Consultation time: " + "Start from: " + doctor.getStarttime() + " -" + doctor.getEndtime());
        }
    }
}
