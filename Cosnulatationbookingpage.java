import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import gui.DatePicker;
import javax.crypto.Cipher;


public class Cosnulatationbookingpage extends JFrame implements ActionListener {
    private File consultList = new File("Consult.txt");
    private int noofbtns;
    private File docList = new File("Doctors57.txt");
    private FileInputStream finput;
    private ObjectInputStream oinput;
    private FileInputStream fpatinput;
    private ObjectInputStream opatinpu;
    private FileOutputStream fout;
    private ObjectOutputStream oout;
    private FileOutputStream fpatout;
    private ObjectOutputStream fpatobjout;
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Consultation> consultations = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    private Object[] tableobject;
    private Object[] tableobject1;
    private String name;
    private String surname;
    private int year;
    private int month;
    private int date;
    private int mln;
    private int mobileno;
    private String specialisation;
    private String availableday;
    private String gender;
    private int cost;
    private String notes;
    private String aDate;
    private String valuegender;
    private boolean isFull;
    private int consultationID;
    private int patientId;
    private int starttime;
    private int endtime;
    private String relevantdoc;
    private JComboBox<String> dropdowncomboboxgender;
    private ArrayList<Patient> patientstore;
    private int cosultationtime;
    private int consultationIDadd;
    private int doctortime = 0; // Initializing the fully used doctor time so we can add altogether later
    JButton sortbutton;
    DefaultTableModel tableModel;
    DefaultTableModel tableModel1;
    JLabel Pfirstname;
    JLabel Doctor;
    JLabel Plastname;
    JLabel Pbirthdyear;
    JLabel Pbirthmonth;
    JLabel Pbirthdate;
    JLabel Pmobileno;
    JLabel Phoursofconsultation;
    JLabel Pchanneldate;
    JLabel Pgender;
    JLabel Pidnumber;
    JLabel Pnotes;
    JTextField Pfirstnametext;
    JTextField Plastnametext;
    JTextField Pbirthdyeartext;
    JTextField Pbirthmonthtext;
    JTextField PbirthdateText;
    JTextField Pmobilenotext;
    JTextField PidnumberText;
    JTextArea PnotesText;
    JButton addpatient;
    JButton updatepatient;
    JButton deletepatient;
    JButton savepatients;
    JButton loadpatients;
    JButton searchpatient;
    public void consulatationexecute() throws IOException {

        // creating manager object
        WestminsterSkinConsultationManager west = new WestminsterSkinConsultationManager();

        // creating doctor object
        Doctor doctor = new Doctor(name, surname, year, month, date, mobileno, mln, specialisation, availableday, gender, starttime, endtime);

        // ---- Loading saved data to arraylists ----

        //loadfilepat(consultations);


        // loading the data into arraylist
        loadfile(doctors);

        // --- initialising the button ---
        sortbutton = new JButton("Sort");

        JFrame jFrame = new JFrame();
        JLabel jLabel = new JLabel();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel3 = new JPanel(new GridLayout(1,1));

        jPanel1.setBackground(Color.CYAN);

        //jPanel3.setBackground(Color.MAGENTA);
        //jPanel1.setPreferredSize(new Dimension(100,300));
        //jPanel3.setPreferredSize(new Dimension(100,300));
        //jLabel.setFont(new Font("Poppins", Font.BOLD, 20));

        // Creating a Jtable
        String[] colomnames = {"Name of the Doctor", "Gender of the Doctor", "Mobile no", "Available day of the week", "Consultation Start time", "Consultation End time"};


        tableModel = new DefaultTableModel(colomnames,0);
        JTable jTable = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable.getModel());
        jTable.setRowSorter(sorter);
        // adding data in to arraylist using for loop

        for (Doctor value : doctors) {
            String name1 = value.getName().substring(0,1).toUpperCase() + value.getName().substring(1) + " " + value.getSurname().substring(0,1).toUpperCase() +
                    value.getSurname().substring(1);
            String gender1 = value.getGender().substring(0,1).toUpperCase() + value.getGender().substring(1);
            int Mobileno1 = value.getMobilenumber();
            String availableday1 = value.getAvailableday().substring(0,1).toUpperCase() + value.getAvailableday().substring(1);
            int consultationstarttime = value.getStarttime();
            int consultationendtime = value.getEndtime();

            tableobject = new Object[]{name1, gender1, Mobileno1, availableday1, consultationstarttime, consultationendtime};
            tableModel.addRow(tableobject);
        }

        //--- Action listner ---
        sortbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ---- Getting an Action ----

                if (e.getSource() == sortbutton){

                    // points colomn in the model converted to the view colomn

                    int viewcolomn = jTable.convertColumnIndexToView(0);

                    // Set up colomns you want to sort on

                    List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                    sortKeys.add(new RowSorter.SortKey(viewcolomn, SortOrder.ASCENDING));

                    // Do the Sorting

                    TableRowSorter sorter = (TableRowSorter)jTable.getRowSorter();
                    sorter.setSortKeys(sortKeys);
                }
            }
        });

        // ---- set colomn margin ----

        jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        //--- Setting topic ---
        jLabel.setText("Book Your Doctor");
        jLabel.setFont(new Font("Poppins", Font.BOLD, 15));
        jFrame.add(jLabel, BorderLayout.NORTH);

        //----- Creating sub panels -----

        jFrame.add(new JScrollPane(jTable));
        jTable.setPreferredSize(new Dimension(550,300));
        jTable.setBounds(0,0,100,100);

        // ---- Button and Table alingment ----

        //jFrame.add(jTable, BorderLayout.NORTH);
        JPanel twoplanenorth = new JPanel(new GridLayout(1,2));
        twoplanenorth.add(jTable);
        twoplanenorth.add(sortbutton);
        jFrame.add(twoplanenorth, BorderLayout.NORTH);


        //jPanel1.add(jLabel);

        // ---- Creating the form on Center Panel ----
        // ---- Creating name label ----

        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(Color.MAGENTA);
        Pfirstname = new JLabel("Firstname: ");
        Pfirstname.setFont(new Font("Poppins", Font.BOLD, 15));


        // ---- Creating name Text fields ----

        Pfirstnametext = new JTextField();
        Pfirstnametext.setFont(new Font("Poppins", Font.BOLD, 15));
        System.out.println(Pfirstnametext.getText());

        // ---- Creating Doctor Scroll down ----

        Doctor = new JLabel("Doctor");
        Doctor.setFont(new Font("Poppins", Font.BOLD, 15));
        jPanel2.add(Doctor);

        //ArrayList<String> dropdowndoctors = new ArrayList<>();
        String[] dropdowndoctors = new String[doctors.size()];
        for (int i = 0; i < doctors.size(); i++){
            dropdowndoctors[i] = doctors.get(i).getName() + " " + doctors.get(i).getSurname();
        }
        JComboBox<String> comboBox = new JComboBox<>(dropdowndoctors);
        comboBox.setBounds(0,750,50,10);
        jPanel2.add(comboBox);


        // ---- Lastname label ----

        Plastname = new JLabel("Lastname");
        Plastname.setFont(new Font("Poppins", Font.BOLD,15));

        // ---- Lastname Textfield ----

        Plastnametext = new JTextField();
        Plastnametext.setFont(new Font("Poppins", Font.BOLD, 15));


        // ---- Birthdyear Scroll down ----

        Pbirthdyear = new JLabel("Birth Year");
        Pbirthdyear.setFont(new Font("Poppins", Font.BOLD,15));

        // ---- Birth year Text Field ----

        Pbirthdyeartext = new JTextField();
        Pbirthdyeartext.setFont(new Font("Poppins", Font.BOLD, 15));
        //patient.setYear(Integer.parseInt(String.valueOf(Pbirthdyeartext.getText())));

        // ---- Birth month ----

        Pbirthmonth = new JLabel("Birth Month");
        Pbirthmonth.setFont(new Font("Poppins", Font.BOLD,15));

        // ---- Birth Month text field ----

        Pbirthmonthtext = new JTextField();
        Pbirthmonthtext.setFont(new Font("Poppins", Font.BOLD, 15));
        //patient.setMonth(Integer.parseInt(String.valueOf(Pbirthmonthtext.getText())));

        // ---- Birth Date Label ----

        Pbirthdate = new JLabel("Birth Date");
        Pbirthdate.setFont(new Font("Poppins", Font.BOLD,15));

        // ---- Birth Date text field ----

        PbirthdateText = new JTextField();
        Pbirthmonthtext.setFont(new Font("Poppins", Font.BOLD, 15));
        //patient.setDate(Integer.parseInt(String.valueOf(PbirthdateText.getText())));

        // ---- Mobile No Label ----

        Pmobileno = new JLabel("Mobile no");
        Pmobileno.setFont(new Font("Poppins", Font.BOLD,15));
        jPanel2.add(Pmobileno);

        // ---- Mobile no text Field ----

        Pmobilenotext = new JTextField();
        Pmobilenotext.setFont(new Font("Poppins", Font.BOLD, 15));
        //patient.setMobilenumber(Integer.parseInt(String.valueOf(Pmobilenotext.getText())));
        jPanel2.add(Pmobilenotext);

        // ---- Id No ----

        Pidnumber = new JLabel("ID Number");
        Pidnumber.setFont(new Font("Poppins", Font.BOLD,15));
        jPanel2.add(Pidnumber);

        // ---- Id No text ----

        PidnumberText = new JTextField();
        PidnumberText.setFont(new Font("Poppins", Font.BOLD, 15));
        //patient.setPatientId(Integer.parseInt(String.valueOf(PidnumberText)));
        jPanel2.add(PidnumberText);

        // ---- Hours of consultation ----

        Phoursofconsultation = new JLabel("Hours of consultation");
        Phoursofconsultation.setFont(new Font("Poppins", Font.BOLD,15));
        jPanel2.add(Phoursofconsultation);

        // ---- Hours of consultation scroll down ----

        String[] hoursdropdown = {"1", "2", "3", "4"};
        JComboBox<String> dropdowncombobox = new JComboBox<String>(hoursdropdown);
        dropdowncombobox.setBounds(10,750,50,10);
        jPanel2.add(dropdowncombobox);

        // ---- Channel date label ----

        Pchanneldate = new JLabel("Channel Date");
        Pchanneldate.setFont(new Font("Poppins", Font.BOLD,15));
        jPanel2.add(Pchanneldate);

        // ---- Channel date picker ----

        String[] Dayofweekdropdown = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox<String> dropdowncomboboxdaysofweek = new JComboBox<String>(Dayofweekdropdown);
        jPanel2.add(dropdowncomboboxdaysofweek);

        // ---- Gender Label ----

        Pgender = new JLabel("Gender of the Patient");
        Pgender.setFont(new Font("Poppins", Font.BOLD,15));
        jPanel2.add(Pgender);

        // ---- Gender drop down ----

        String[] genderdropdown = {"Male", "Female"};
        dropdowncomboboxgender = new JComboBox<String>(genderdropdown);
        //patient.setGender(Objects.requireNonNull(dropdowncomboboxgender.getSelectedItem()).toString());
        jPanel2.add(dropdowncomboboxgender);

        dropdowncomboboxgender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // ---- Notes of the patient ---

        Pnotes = new JLabel("Notes");
        Pnotes.setFont(new Font("Poppins", Font.BOLD,15));
        jPanel2.add(Pnotes);

        // ---- Text area of the notes ----

        PnotesText = new JTextArea();
        PnotesText.setFont(new Font("Poppins", Font.PLAIN, 10));
        JScrollPane notesscrollpane = new JScrollPane(PnotesText);
        jPanel2.add(notesscrollpane, BorderLayout.PAGE_START);

        // ---- Using getters setters



        // ---- Adding the Labels to the new panel ----

        JPanel labelpanel = new JPanel(new GridLayout(6,2, 25,30));
        labelpanel.add(Pfirstname);
        labelpanel.add(Pfirstnametext);
        labelpanel.add(Plastname);
        labelpanel.add(Plastnametext);
        labelpanel.add(Pbirthdyear);
        labelpanel.add(Pbirthdyeartext);
        labelpanel.add(Pbirthmonth);
        labelpanel.add(Pbirthmonthtext);
        labelpanel.add(Pbirthdate);
        labelpanel.add(PbirthdateText);
        labelpanel.add(Pgender);
        labelpanel.add(dropdowncomboboxgender);
        jPanel2.add(labelpanel, BorderLayout.EAST);

        // ---- Adding labels and fields to pane 2 ----

        JPanel labelpanel2 = new JPanel(new GridLayout(6,2,25,30));
        labelpanel2.add(Doctor);
        labelpanel2.add(comboBox);
        labelpanel2.add(Phoursofconsultation);
        labelpanel2.add(dropdowncombobox);
        labelpanel2.add(Pchanneldate);
        labelpanel2.add(dropdowncomboboxdaysofweek);
        labelpanel2.add(Pnotes);
        labelpanel2.add(notesscrollpane);
        labelpanel2.add(Pmobileno);
        labelpanel2.add(Pmobilenotext);
        labelpanel2.add(Pidnumber);
        labelpanel2.add(PidnumberText);
        jPanel2.add(labelpanel2,BorderLayout.WEST); // Adding the label 2 components to the main jframe



        // --- Data graph layout ----

        // ---- Initializing no of variables according to the length of arraylist ----

        /*for (int i = 0; i < doctors.size(); i++){
            patientconsultation.setConsultationtime((doctors.get(i).getEndtime() - doctors.get(i).getStarttime()) / 100);
        }*/


        // --- Adding -  Adding, Update, Deleting buttons ----

        addpatient = new JButton(); // Adding the Patient
        addpatient.setText("ADD");
        addpatient.setFont(new Font("Poppins", Font.BOLD, 20));
        addpatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean Isthere = false;
                boolean Iscomplete = true;
                Patient patient = new Patient(name, surname, year, month, date, mobileno, patientId, gender);

                // ---- Validate the patients name ----
                boolean Isvalid = true;

                // ---- Checking whether the user has input details or not ----

                if(Pfirstnametext.getText().equalsIgnoreCase(" ") || Pfirstnametext.getText().equalsIgnoreCase("") || Plastnametext.getText().equalsIgnoreCase(" ") ||
                Plastnametext.getText().equalsIgnoreCase("") || Integer.parseInt(Pbirthdyeartext.getText()) == 0 ||
                Integer.parseInt(Pbirthmonthtext.getText()) == 0 || Integer.parseInt(PbirthdateText.getText()) == 0 || Integer.parseInt(Pmobilenotext.getText()) == 0 ||
                Integer.parseInt(PidnumberText.getText()) == 0){
                    JOptionPane.showMessageDialog(null, "Please input valid details", "WARNING", JOptionPane.WARNING_MESSAGE);
                    Isvalid = false;
                }
                if (Isvalid){

                    // ---- Checking whether the user has input valid details or not ----

                    patient.setName(Pfirstnametext.getText());
                    patient.setSurname(Plastnametext.getText());
                    patient.setYear(Integer.parseInt(Pbirthdyeartext.getText()));
                    patient.setMonth(Integer.parseInt(Pbirthmonthtext.getText()));
                    patient.setDate(Integer.parseInt(PbirthdateText.getText()));

                    // ---- Validating phone number ----
                    // ---- firstly checking whether there are 10 numbers in it ----


                    /*int count = 0;
                    int patMobileno = Integer.parseInt(Pmobilenotext.getText());
                    while (patMobileno !=0){
                        Iscomplete = true;
                        patMobileno = patMobileno / 10;
                        count++;
                        if (count != 9){
                            Iscomplete = false;
                            if ( patMobileno == 0){
                                JOptionPane.showMessageDialog(null, "Your contact number is invalid \n Please enter a valid contact number", "WARNING", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                    if (Iscomplete){
                        for (int i =0; i < patients.size(); i++){
                            if (patient.getMobilenumber() == patMobileno){
                                Isthere = true;
                                JOptionPane.showMessageDialog(null, "Your contact number is already added \n Please enter another contact number", "WARNING", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        if (!Isthere){
                            patient.setMobilenumber(Integer.parseInt(Pmobilenotext.getText()));
                        }
                    }*/
                    patient.setMobilenumber(Integer.parseInt(Pmobilenotext.getText()));
                    patient.setGender(dropdowncomboboxgender.getItemAt(dropdowncomboboxgender.getSelectedIndex()));

                    // ---- Need to validate the Id numbers ----

                    boolean IsthereIdno = false;


                    patient.setPatientId(Integer.parseInt(PidnumberText.getText())); // make text more than 10 no
                    patients.add(patient);
                }

                // ---- Adding patient consultation to it's arraylist as an objects

                // Creating Consultation object

                Consultation patientconsultation = new Consultation(cost, notes, aDate, isFull, consultationID, starttime, endtime, relevantdoc, patientstore, cosultationtime);

                patientconsultation.setRelevantdoc(comboBox.getItemAt(comboBox.getSelectedIndex()));
                patientconsultation.setaDate(dropdowncomboboxdaysofweek.getItemAt(dropdowncomboboxdaysofweek.getSelectedIndex()));
                patientconsultation.setNotes(PnotesText.getText());
                patientconsultation.setPatientstore(patients);

                // ---- Add only when all the variables are applied ----
                if (!Isthere && Iscomplete){
                    consultations.add(patientconsultation);
                }

                // ---- check whether the consultation time is valid ----

                for (int i = 0; i < doctors.size(); i++){
                    patientconsultation.setIsfull(true);
                    String fullname = doctors.get(i).getName() + " " + doctors.get(i).getSurname();
                    if (fullname.equalsIgnoreCase(patientconsultation.getRelevantdoc())){
                        patientconsultation.setConsultationtime((doctors.get(i).getEndtime() - doctors.get(i).getStarttime()) / 100);
                        //System.out.println(patientconsultation.getConsultationtime());
                        if (patientconsultation.getConsultationtime() >= Integer.parseInt(dropdowncombobox.getItemAt(dropdowncombobox.getSelectedIndex()))){
                            patientconsultation.setIsfull(false);
                        }
                        if (patientconsultation.isIsfull()){
                            JOptionPane.showMessageDialog(null, "Doctor time is not enough \n Assigning you to another doctor", "Doctor is full", JOptionPane.WARNING_MESSAGE);
                            while (true){
                                int Index = (int) (Math.random() * doctors.size());
                                String newdoc = doctors.get(Index).getName() + " " + doctors.get(Index).getSurname();
                                if (!newdoc.equalsIgnoreCase(patientconsultation.getRelevantdoc())){
                                    if((doctors.get(Index).getEndtime() - doctors.get(Index).getStarttime()) >= Integer.parseInt(dropdowncombobox.getItemAt(dropdowncombobox.getSelectedIndex()))){
                                        patientconsultation.setRelevantdoc(newdoc);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }

                // ---- Check whether the doctor have enough time to consult the patient ----

                for (int i = 0; i < consultations.size(); i++){
                    patientconsultation.setIsfull(true);
                    for (int j = 0; j < doctors.size(); j++){
                        int docconsultitime = doctors.get(j).getEndtime() - doctors.get(i).getStarttime();
                        int patconsultationtime = Integer.parseInt(dropdowncombobox.getItemAt(dropdowncombobox.getSelectedIndex()));
                        doctortime = docconsultitime - doctortime;
                        if (doctortime >= patconsultationtime){
                            patientconsultation.setIsfull(false);
                            doctortime = doctortime - patconsultationtime;
                            break;
                        }
                        if (patientconsultation.isIsfull()){
                            JOptionPane.showMessageDialog(null, "Doctor is Currently full \n Assigning you to another doctor", "Doctor is full", JOptionPane.WARNING_MESSAGE);
                            while (true){
                                int Index = (int) (Math.random() * doctors.size());
                                String newdoc = doctors.get(Index).getName() + " " + doctors.get(Index).getSurname();
                                if (!newdoc.equalsIgnoreCase(patientconsultation.getRelevantdoc())){
                                    if((doctors.get(Index).getEndtime() - doctors.get(Index).getStarttime()) >= Integer.parseInt(dropdowncombobox.getItemAt(dropdowncombobox.getSelectedIndex()))){
                                        patientconsultation.setRelevantdoc(newdoc);
                                        doctortime = doctortime - patconsultationtime;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                // ---- Getting cost for the patients ----

                for (int i = 0; i < consultations.size(); i++){
                    int patconsulttimecost = Integer.parseInt(dropdowncombobox.getItemAt(dropdowncombobox.getSelectedIndex()));
                    if (patconsulttimecost == 1){
                        patientconsultation.setCost(25);
                    }
                    else if (patconsulttimecost > 1){
                        patientconsultation.setCost(25 + (patconsulttimecost - 1) * 15);
                    }
                }
            }
        });

        //jPanel3.add(jTable1);
        updatepatient = new JButton(); // Viewing the patients
        updatepatient.setText("VIEW");
        updatepatient.setFont(new Font("Poppins", Font.BOLD, 20));
        updatepatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (consultations.size() > 0){
                    table(consultations, jFrame, jPanel3);
                }
                else{
                    JOptionPane.showMessageDialog(null, "There are no patients added to the System", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deletepatient = new JButton(); // Deleting a existing patient
        deletepatient.setText("DELETE");
        deletepatient.setFont(new Font("Poppins", Font.BOLD, 20));
        deletepatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFound = false;
                int Idno = Integer.parseInt(JOptionPane.showInputDialog("Enter the Id no: "));
                for (int i = 0; i < consultations.size(); i++){
                    for (int j = 0; j < patients.size(); j++){
                        if (Idno == patients.get(j).getPatientId()){
                            System.out.println("working");
                            isFound = true;
                            JOptionPane.showMessageDialog(null, "Patient is being deleted", " Deleting ", JOptionPane.WARNING_MESSAGE);
                            consultations.remove(i);
                            patients.remove(j);
                        }
                    }
                    if (!isFound){
                        JOptionPane.showMessageDialog(null, "Please enter a valid Id Number", "Wrong ", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });

        // ---- Creating a save button ----

        savepatients = new JButton();
        savepatients.setText("Save");
        savepatients.setFont(new Font("Poppins", Font.BOLD, 20));
        savepatients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savefilepat(consultations);
            }
        });
        // ---- loadpatients ----

        loadpatients = new JButton();
        loadpatients.setText("Load");
        loadpatients.setFont(new Font("Poppins", Font.BOLD, 20));
        loadpatients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadfilepat(consultations);
                //table(consultations, jFrame, jPanel3);
                System.out.println(consultations);

            }
        });

        // ---- Search the patient Button ---

        searchpatient = new JButton();
        searchpatient.setText("Search Patient");
        searchpatient.setFont(new Font("Poppins", Font.BOLD, 20));
        searchpatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ---- Declrearing the isFound variable ----

                boolean isFound = false;

                // ---- creating a joption pane for reaading the id number and revealing patient details ----

                int Idno = Integer.parseInt(JOptionPane.showInputDialog("Enter the Id no: "));
                for (int i = 0; i < consultations.size(); i++){
                    if (Idno == patients.get(i).getPatientId()){
                        System.out.println("Working");
                        isFound = true;
                        //JOptionPane.showMessageDialog(null, Joptionpane(consultations, patients), "Details of the requested patient", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Full name of the patient: " + patients.get(i).getName() + " "+ patients.get(i).getSurname() +
                                "\n Birthdate of the Patient: " + patients.get(i).getYear() + "-" + patients.get(i).getMonth() + "-" + patients.get(i).getDate() +
                                "\n Gender of the Patient: " + patients.get(i).getGender() + " " +
                                "\n Prefered doctor of the Patient: " + consultations.get(i).getRelevantdoc() + " " +
                                "\n Channeling date of the Patient: " + consultations.get(i).getaDate() + " " +
                                "\n Notes of the Patient: " + consultations.get(i).getNotes() + " " +
                                "\n Mobile No of the Patient: " + patients.get(i).getMobilenumber() + " " +
                                "\n Id No of the Patient: " + patients.get(i).getPatientId() + " " +
                                "\n Cost for the Consultation: " + consultations.get(i).getCost());
                    }
                }
                if (!isFound){
                    JOptionPane.showMessageDialog(null, "Please enter valid Id number", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // ---- Adding the Adding, Update, Deleting Buttons to the Gridlayout ----

        JPanel threebuttons = new JPanel(new GridLayout(3,2));
        threebuttons.add(addpatient);
        threebuttons.add(updatepatient);
        threebuttons.add(deletepatient);
        threebuttons.add(savepatients);
        threebuttons.add(loadpatients);
        threebuttons.add(searchpatient);
        jPanel2.add(threebuttons, BorderLayout.WEST);

        // ---- Adding planes symmetrically ----

        JPanel threepanelcontainer = new JPanel(new GridLayout(2,1));
        threepanelcontainer.add(jPanel2);
        threepanelcontainer.add(jPanel3, BorderLayout.SOUTH);
        jFrame.add(threepanelcontainer);

        // ---- Adding Components to the frame ----

        jFrame.setTitle(" Consultation booking window");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(2048,1920);
        //jPanel3.setBackground(Color.blue);
        jPanel1.setBounds(0,0,1500, 350);
        jFrame.setVisible(true);

    }

    // ---- load method for doctor arraylist ----

    public void loadfile(ArrayList<Doctor> doctors) throws IOException {
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

        } catch (IOException e){
            System.out.println("Error occured");
        }

    }
    private void btnSelectDateDOBActionPerformed(ActionEvent event){
        DatePicker datePicker = new DatePicker(this);
        PbirthdateText.setText(datePicker.setPickedDate());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public String getcombogender(){
        return (String) dropdowncomboboxgender.getSelectedItem().toString();
    }

    // ---- load file method(For consultation array) ----
   public void loadfilepat(ArrayList<Consultation> consultations){
        try {
            fpatinput =new FileInputStream(consultList);
            opatinpu =new ObjectInputStream(fpatinput);

            while (true){
                try {
                    Consultation newconsult = (Consultation) opatinpu.readObject();
                    consultations.add(newconsult);
                }catch (IOException | ClassNotFoundException e){
                    break;
                }
            }


            fpatinput.close();
            opatinpu.close();

        }catch (FileNotFoundException e){}
        catch (IOException e) {}
    }

    // ---- save file method(For consultation array) ----
    public void savefilepat(ArrayList<Consultation> consultations){
        try {

            //--clear existing data version--
            FileWriter clearFile = new FileWriter(consultList);
            clearFile.write("");
            clearFile.close();

            fpatout=new FileOutputStream(consultList,true);
            fpatobjout=new ObjectOutputStream(fpatout);

            //--write new data--
            Iterator itr = consultations.iterator();
            System.out.println(consultations);
            while (itr.hasNext()){
                fpatobjout.writeObject((Consultation)itr.next());
            }

            fpatout.close();
            fpatobjout.close();

        }catch (FileNotFoundException e){
            //System.out.println("Error Found");
        }
        catch (IOException e){
            //System.out.println("Error found");
        }
    }

    // ---- J table for patient revealing ----
    // ---- Need alingments ----
    public void table(ArrayList<Consultation> consultations, JFrame jFrame, JPanel jPanel3){
        String[] patientinfo = {"Consultation Id", "Full Name of the patient", "Gender of the patient", " Relevant doctor", "Channel Date", "Cost"};
        tableModel1 = new DefaultTableModel(patientinfo, 0);
        JTable jTable1 = new JTable(tableModel1);
        TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter1);
        if (consultations.size() > 0){
            for (int i = 0; i < consultations.size(); i++){
                String patname = patients.get(i).getName().substring(0,1).toUpperCase() + patients.get(i).getName().substring(1)+ " " + patients.get(i).getSurname().substring(1).toLowerCase();
                int patconsultID = consultations.get(i).getConsultationID();
                String patgen = patients.get(i).getGender().substring(0,1).toUpperCase() + patients.get(i).getGender().substring(1);
                String patreldoc = consultations.get(i).getRelevantdoc().substring(0,1).toUpperCase() + consultations.get(i).getRelevantdoc().substring(1);
                String weekday = consultations.get(i).getaDate();
                int cost = consultations.get(i).getCost();

                tableobject1 = new Object[]{patconsultID, patname, patgen, patreldoc, weekday, cost};
                tableModel1.addRow(tableobject1);
                System.out.println(patreldoc);
            }
            JOptionPane.showMessageDialog(null, new JScrollPane(jTable1), "Details of the Patients", JOptionPane.INFORMATION_MESSAGE);
            jTable1.setPreferredSize(new Dimension(1550,300));
            jTable1.setBounds(0,0,1500,500);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(300);
            //jPanel3.add(jTable1);
        }
    }

}
