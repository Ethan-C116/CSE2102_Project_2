
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CreateProfile extends JFrame {
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel adminLabel, fNameLabel, lNameLabel, addressLabel, phoneLabel, coPayLabel, iTypeLabel, pTypeLabel, mdConLabel, mdPhoneLabel, allLabel, illLabel;
    private JTextField adminTF, fNameTF, lNameTF, addressTF, phoneTF, coPayTF, mdConTF, mdPhoneTF;
    private JButton submitButton;
    private JComboBox<JLabel> iTypeCB, pTypeCB, allTypeCB, illTypeCB;
    private JFrame frame;

    public CreateProfile() {
        //create a JFrame
        super("IPS - Create Profile");
        frame = this;
        //stop program on close
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //adjust window size
        this.setMinimumSize(new Dimension(400, 300));
        //put window off-center
        int[] center = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(center[0] + 15, center[1] + 15);

        //create panel
        panel = new JPanel(new GridBagLayout());

        //constraints for labels
        GridBagConstraints gbConstraints = new GridBagConstraints();
        //layout starts center
        gbConstraints.anchor = GridBagConstraints.CENTER;
        //set internal padding to space out elements
        gbConstraints.ipadx = WindowTools.DEFAULT_PADDING_X;
        gbConstraints.ipady = WindowTools.DEFAULT_PADDING_Y;
        gbConstraints.insets = WindowTools.SPACED_INSET;

        //constraints for text fields
        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        //layout starts at center left
        textFieldConstraints.anchor = GridBagConstraints.LINE_START;
        //set internal padding to space out elements
        textFieldConstraints.ipadx = WindowTools.DEFAULT_PADDING_X;
        textFieldConstraints.ipady = WindowTools.DEFAULT_PADDING_Y;
        textFieldConstraints.insets = WindowTools.TEXT_FIELD_INSET;
        //make text fields expand horizontally
        textFieldConstraints.gridwidth = GridBagConstraints.REMAINDER;
        textFieldConstraints.weightx = 1;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;

        //set up titleLabel
        titleLabel = new JLabel("Create Profile");
        gbConstraints.insets = WindowTools.DEFAULT_INSET;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        //4 wide. from 0-3
        gbConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gbConstraints.weightx = 1;
        //add to panel
        panel.add(titleLabel, gbConstraints);
        //reset constraints
        gbConstraints.insets = WindowTools.DEFAULT_INSET;
        gbConstraints.gridwidth = 1;
        gbConstraints.weightx = 1;

        //set up adminID label
        adminLabel = new JLabel("Admin ID:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 1;
        panel.add(adminLabel, gbConstraints);

        //set up adminID field
        adminTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        panel.add(adminTF, textFieldConstraints);

        //set up first name label
        fNameLabel = new JLabel("First Name:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        panel.add(fNameLabel, gbConstraints);

        //set up first name field
        fNameTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 2;
        panel.add(fNameTF, textFieldConstraints);

        //set up last name label
        lNameLabel = new JLabel("Last Name:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 3;
        panel.add(lNameLabel, gbConstraints);

        //set up last name field
        lNameTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 3;
        panel.add(lNameTF, textFieldConstraints);

        //set up address label
        addressLabel = new JLabel("Address:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        panel.add(addressLabel, gbConstraints);

        //set up address field
        addressTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 4;
        panel.add(addressTF, textFieldConstraints);

        //set up phone label
        phoneLabel = new JLabel("Phone:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 5;
        panel.add(phoneLabel, gbConstraints);

        //set up phone field
        phoneTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 5;
        panel.add(phoneTF, textFieldConstraints);

        //set up copay label
        coPayLabel = new JLabel("Co-Pay:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 6;
        panel.add(coPayLabel, gbConstraints);

        //set up copay field
        coPayTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 6;
        panel.add(coPayTF, textFieldConstraints);

        // set up insur. type label
        iTypeLabel = new JLabel("Insur. Type: ");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 7;
        panel.add(iTypeLabel, gbConstraints);

        // set up insur. type dropdown
        String[] insurOptions = {"Private", "Government"};
        iTypeCB = new JComboBox(insurOptions);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 7;
        panel.add(iTypeCB, textFieldConstraints);

        // set up Patient type label
        pTypeLabel = new JLabel("Patient Type: ");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 8;
        panel.add(pTypeLabel, gbConstraints);

        // set up patient type dropdown
        String[] patientOptions = {"Pediatric", "Adult", "Senior"};
        pTypeCB = new JComboBox(patientOptions);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 8;
        panel.add(pTypeCB, textFieldConstraints);

        //set up mdContact label
        mdConLabel = new JLabel("Md Contact:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 9;
        panel.add(mdConLabel, gbConstraints);

        //set up mdContact field
        mdConTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 9;
        panel.add(mdConTF, textFieldConstraints);


        //set up md Phone label
        mdPhoneLabel = new JLabel("Md Phone:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 10;
        panel.add(mdPhoneLabel, gbConstraints);

        //set up md Phone field
        mdPhoneTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 10;
        panel.add(mdPhoneTF, textFieldConstraints);

        // set up allergies label
        allLabel = new JLabel("Allergies: ");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 11;
        panel.add(allLabel, gbConstraints);

        // set up allergies dropdown
        String[] allOptions = {"none", "food", "medication", "other"};
        allTypeCB = new JComboBox(allOptions);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 11;
        panel.add(allTypeCB, textFieldConstraints);

        // set up illness label
        illLabel = new JLabel("Illnesses: ");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 12;
        panel.add(illLabel, gbConstraints);

        // set up illness dropdown
        String[] illOptions = {"none", "CHD", "diabetes", "asthma", "other"};
        illTypeCB = new JComboBox(illOptions);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 12;
        panel.add(illTypeCB, textFieldConstraints);

        //add submit button
        submitButton = new JButton("Submit");
        setUpListener();
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 13;
        gbConstraints.insets = new Insets(10, 15, 10, 15);
        gbConstraints.gridwidth = 4;
        panel.add(submitButton, gbConstraints);
        //reset constraints
        gbConstraints.insets = WindowTools.TEXT_FIELD_INSET;
        gbConstraints.gridwidth = 1;

        this.add(panel);
        this.pack();
    }

    private void setUpListener(){
        // set up button listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // see what's selected in ComboBoxes
                // private JComboBox<JLabel> iTypeCB, pTypeCB, allTypeCB, illTypeCB;
                String insurSelection = iTypeCB.getSelectedItem().toString();
                String patientSelection = pTypeCB.getSelectedItem().toString();
                String allSelection = allTypeCB.getSelectedItem().toString();
                String illSelection = illTypeCB.getSelectedItem().toString();

                // insert a new profile
                String adminID = adminTF.getText().strip();
                String firstName = fNameTF.getText().strip();
                String lastName = lNameTF.getText().strip();
                String address = addressTF.getText().strip();
                String phone = phoneTF.getText().strip();
                String copayString = coPayTF.getText().strip();
                Float copay = Float.parseFloat(copayString);
                String mdcontact = mdConTF.getText().strip();
                String mdphone = mdPhoneTF.getText().strip();

                MedCond medCond = new MedCond(mdcontact, mdphone, allSelection, illSelection);
                PatientProf patientProf = new PatientProf(adminID, firstName, lastName, address, phone, copay,
                        insurSelection, patientSelection, medCond);
                
                MenuWindow.DB.insertNewProfile(patientProf);
                MenuWindow.DB.writeAllPatientProf(MenuWindow.FILE_PATH);
                
                // make sure new profile is in database
                PatientProf newpatient = MenuWindow.DB.findProfile(adminID, lastName); 
                
                if(newpatient != null){
                    JOptionPane.showMessageDialog(null, "Patient Profile Successfuly Created!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error Creating Patient Profile");
                }
               
            }
        });
    }
    public static void main(String[] args){
        CreateProfile createProfile = new CreateProfile();
        createProfile.setVisible(true);
    }



}
