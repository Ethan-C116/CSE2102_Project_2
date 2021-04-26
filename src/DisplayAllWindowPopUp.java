import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayAllWindowPopUp extends JFrame {
    private GridBagConstraints labelConstraints = WindowTools.getLabelConstraints();
    private GridBagConstraints textFieldConstraints = WindowTools.getTextFieldConstraints();
    private ArrayList<JLabel> patientInfoFields = new ArrayList<>();
    private ArrayList<JLabel> patientInfoLabels= new ArrayList<>();
    private String adminID;


    public DisplayAllWindowPopUp(String adminID) {
        //set up frame
        super("IPS - Display Patients Results");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 500));

        this.adminID = adminID;
        MenuWindow.DB.currentPatientIndex = 0;

        //start window off center
        int[] location = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(location[0] + 25, location[1] + 25);

        JPanel panel = new JPanel(new GridBagLayout());

        //find first patient
        PatientProf patientProf = MenuWindow.DB.findFirstProfile(this.adminID);

        //driver code
        /*
        PatientProf patientProf = new PatientProf("123", "doe", "john",
                "street", "860", (float)1.00, "private", "adult",
                new MedCond("doctor", "doc phone", "none", "none"));

         */

        //populate patientInfoFields and patientInfoLabels
        populateFieldsArray(patientProf);
        populateLabelsArray();

        //add title label
        JLabel title = new JLabel("Find All Patients");
        title.setFont(title.getFont().deriveFont((float)14.0));
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 4;
        panel.add(title, labelConstraints);
        labelConstraints.gridwidth = 1;

        //add patient info labels and fields
        writePatientInfo(panel, patientProf, false);

        //add next button
        JButton nextButton = new JButton("Next");
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 13;
        textFieldConstraints.gridwidth = 4;
        textFieldConstraints.insets = new Insets(10, 15, 10, 15);
        panel.add(nextButton, textFieldConstraints);
        textFieldConstraints.gridwidth = 1;
        textFieldConstraints.insets = WindowTools.TEXT_FIELD_INSET;

        JFrame frame = this;
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call findNextPatient
                PatientProf nextPatient = MenuWindow.DB.findNextProfile(adminID);
                if(nextPatient != null) {

                    frame.remove(panel);
                    frame.setVisible(false);
                    //find next patient and pass to writePatientInfo
                    //add new info
                    writePatientInfo(panel, nextPatient, true);

                    //update frame with new panel
                    Dimension previousDim = frame.getSize();
                    frame.add(panel);
                    frame.pack();
                    frame.setSize(previousDim);
                    frame.setVisible(true);
                }
                else{
                    //no more patients
                    JOptionPane.showMessageDialog(frame, "No more patients to display",
                            "IPS - Display Patients", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        this.add(panel);
        this.pack();

    }

    private void writePatientInfo(JPanel panel, PatientProf patientProf, boolean update){

        labelConstraints.anchor = GridBagConstraints.CENTER;

        //if updating only iterate through fields not labels
        //initialize a counter
        int y;
        if(update){
            //make copy of old list
            ArrayList<JLabel> oldList = patientInfoFields;
            //update list
            updateFieldsArray(patientProf);

            y = 1;
            //add info fields down x = 1
            labelConstraints.gridx = 1;
            for(int field = 0; field < patientInfoFields.toArray().length; field ++){
                labelConstraints.gridy = y;
                //remove existing JLabel
                panel.remove(oldList.get(field));
                //replace with new updated JLabel
                panel.add(patientInfoFields.get(field), labelConstraints);
                y++;
            }
        }
        else{
            //first run
            //add info fields and labels
            y = 1;
            //add labels down x = 0
            labelConstraints.gridx = 0;
            for(int label = 0; label < patientInfoLabels.toArray().length; label ++){
                labelConstraints.gridy = y;
                panel.add(patientInfoLabels.get(label), labelConstraints);
                y++;
            }

            y = 1;
            //add info fields down x = 1
            labelConstraints.gridx = 1;
            for(int field = 0; field < patientInfoFields.toArray().length; field ++){
                labelConstraints.gridy = y;
                panel.add(patientInfoFields.get(field), labelConstraints);
                y++;
            }

        }
    }

    private void populateFieldsArray(PatientProf patientProf){
        //make arrays to hold strings for labels and info
        String[] patientInfo = {patientProf.getAdminID(), patientProf.getLastName(),
                patientProf.getFirstName(), patientProf.getAddress(), patientProf.getPhone(),
                patientProf.getCoPay().toString(), patientProf.getInsuType(), patientProf.getPatientType(),
                patientProf.getMedCondInfo().getMdContact(), patientProf.getMedCondInfo().getMdPhone(),
                patientProf.getMedCondInfo().getAlgType(), patientProf.getMedCondInfo().getIllType()};

        for(int x = 0; x < patientInfo.length; x ++){
            patientInfoFields.add(new JLabel(patientInfo[x]));
        }
    }

    private void updateFieldsArray(PatientProf patientProf){
        patientInfoFields = new ArrayList<JLabel>();
        populateFieldsArray(patientProf);
    }

    private void populateLabelsArray(){
        String[] labels = {"Admin ID:", "Last Name:", "First Name:", "Address:", "Phone:",
                "CoPay:", "Insurance Type:", "Patient Type:", "Md Contact:", "Md Phone:",
                "Allergies:", "Illness:"};

        for(int x = 0; x < labels.length; x ++){
            patientInfoLabels.add(new JLabel(labels[x]));
        }
    }

}
