import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class landingpage implements ActionListener, Serializable {
    private final JButton manager;
    private final JButton patient;
    private final JFrame landingframe;
    Image landingbg = Toolkit.getDefaultToolkit().getImage("/Users/himansha/Desktop/IIT/OOP/CW/BG images/Landingimage.png");
    public landingpage(){
        // initiating background image
        // Creating a panel for two buttons
        landingframe = new JFrame();
        JLabel jLabel = new JLabel();
        jLabel.setText("Welcome to Westminster consultation");
        landingframe.setTitle("Westminster Consultation");
        ImageIcon imageIcon = new ImageIcon("westworldlogowithoutbg.png");
        landingframe.setIconImage(imageIcon.getImage());
        landingframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        landingframe.setSize(700,600);
        landingframe.setLayout(new FlowLayout(FlowLayout.CENTER,60,250));
        manager = new JButton("Manager");
        Font managerfont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        manager.setFont(managerfont);
        landingframe.add(manager);
        manager.addActionListener(this);
        patient = new JButton("Patient");
        Font patientfont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        patient.setFont(patientfont);
        landingframe.add(patient);
        patient.addActionListener(this);
        landingframe.setVisible(true);
        landingframe.getContentPane().setBackground(Color.CYAN);

    }

    public static void main(String[] args) throws FileNotFoundException {
        new landingpage();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        WestminsterSkinConsultationManager manager1 = new WestminsterSkinConsultationManager();
        Cosnulatationbookingpage consultation = null;
        consultation = new Cosnulatationbookingpage();
        try {
            if(e.getSource() == manager){
                landingframe.setVisible(false);
                manager1.manager();
                // execute manager console
            }

            if (e.getSource() == patient){
                landingframe.setVisible(false);
                assert consultation != null;
                consultation.consulatationexecute();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
