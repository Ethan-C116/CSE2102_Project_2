import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class UpdateWindowPopUp extends JFrame {
    private JPanel panel;
    private JLabel titleLabel, adminLabel, admin, nameLabel, name, updateLabel, currentInfoLabel, currentInfo;
    private JTextField updateTF;
    private JButton submitButton;
    private String selection;
    private PatientProf patient;
    private JComboBox<JLabel> updateCB;
    private boolean comboBox;

    /**
     *
     * @param selection String - the part of the patient profile to update
     * @param adminID String - adminID of the user
     * @param lastName String - last name of patient
     * @param patientProf profile of the patient to change
     */
    public UpdateWindowPopUp(String selection, String adminID, String lastName, PatientProf patientProf){
        //create a JFrame with title
        super("IPS - Update Patient Info");

        //set variables from arguments
        this.selection = selection;
        this.patient = patientProf;
        ArrayList<String> selectionOptions = new ArrayList<>(Arrays.asList("Insurance Type",
                "Patient Type", "Illness Type", "Allergy Type"));
        //if selection is a limited value field make combo box
        if(selectionOptions.contains(selection)){
            this.comboBox = true;
        }
        else{
            this.comboBox = false;
        }


        //set up frame
        this.setMinimumSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //put window off-center
        int[] coordinates = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(coordinates[0], coordinates[1]);


        panel = new JPanel(new GridBagLayout());
        //constraints for labels
        GridBagConstraints labelConstraints = new GridBagConstraints();
        //layout starts center
        labelConstraints.anchor = GridBagConstraints.CENTER;
        //set internal padding to space out elements
        labelConstraints.ipadx = WindowTools.DEFAULT_PADDING_X;
        labelConstraints.ipady = WindowTools.DEFAULT_PADDING_Y;
        labelConstraints.insets = WindowTools.SPACED_INSET;

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
        titleLabel = new JLabel("Update");
        labelConstraints.insets = WindowTools.SPACED_INSET;
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        //4 wide. from 0-3
        labelConstraints.gridwidth = GridBagConstraints.REMAINDER;
        labelConstraints.weightx = 1;
        //add to panel
        panel.add(titleLabel, labelConstraints);
        //reset constraints
        labelConstraints.insets = WindowTools.DEFAULT_INSET;
        labelConstraints.gridwidth = 1;
        labelConstraints.weightx = 1;

        //set up adminID label
        adminLabel = new JLabel("Admin ID:");
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 1;
        panel.add(adminLabel, labelConstraints);

        admin = new JLabel(adminID);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        admin.setFont(admin.getFont().deriveFont(Font.BOLD));
        panel.add(admin, textFieldConstraints);

        //set up last name label
        nameLabel = new JLabel("Last Name:");
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 2;
        panel.add(nameLabel, labelConstraints);

        name = new JLabel(lastName);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 2;
        name.setFont(name.getFont().deriveFont(Font.BOLD));
        panel.add(name, textFieldConstraints);

        //set up current info label
        currentInfoLabel = new JLabel("Current Info:");
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 3;
        currentInfoLabel.setFont(name.getFont().deriveFont(Font.BOLD));
        panel.add(currentInfoLabel, labelConstraints);

        if (selection.equals("Address")) {
            currentInfo = new JLabel(patient.getAddress());
        } else if (selection.equals("Phone")) {
            currentInfo = new JLabel(patient.getPhone());
        } else if (selection.equals("CoPay")) {
            currentInfo = new JLabel(patient.getCoPay().toString());
        } else if (selection.equals("Insurance Type")) {
            currentInfo = new JLabel(patient.getInsuType());
        } else if (selection.equals("Patient Type")) {
            currentInfo = new JLabel(patient.getPatientType());
        } else if (selection.equals("Md Contact")) {
            currentInfo = new JLabel(patient.getMedCondInfo().getMdContact());
        } else if (selection.equals("Md Phone")) {
            currentInfo = new JLabel(patient.getMedCondInfo().getMdPhone());
        } else if (selection.equals("Illness Type")) {
            currentInfo = new JLabel(patient.getMedCondInfo().getIllType());
        } else if (selection.equals("Allergy Type")) {
            currentInfo = new JLabel(patient.getMedCondInfo().getAlgType());
        }
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 3;
        panel.add(currentInfo, textFieldConstraints);

        //set up update label
        updateLabel = new JLabel(selection + ":");
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 4;
        panel.add(updateLabel, labelConstraints);

        if(this.comboBox) {
            //use a combo box to update if field limited
            String[] updateOptions = null;
            if(selection.equals("Insurance Type")){
                updateOptions = new String[] {"Government", "Private"};
            }
            else if (selection.equals("Patient Type")){
                updateOptions = new String[] {"Pediatric", "Adult", "Senior"};
            }
            else if (selection.equals("Illness Type")){
                updateOptions = new String[] {"None", "CHD", "Diabetes", "Asthma", "Other"};
            }
            else if (selection.equals("Allergy Type")){
                updateOptions = new String[] {"None", "Food", "Medication", "Other"};
            }

            updateCB = new JComboBox(updateOptions);
            textFieldConstraints.gridx = 1;
            textFieldConstraints.gridy = 4;
            panel.add(updateCB, textFieldConstraints);
        }
        else {
            //set up update text field
            updateTF = new JTextField();
            textFieldConstraints.gridx = 1;
            textFieldConstraints.gridy = 4;
            panel.add(updateTF, textFieldConstraints);
        }

        //set up submit button
        submitButton = new JButton("Submit");
        setUpListener(this);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 5;
        labelConstraints.insets = new Insets(10, 15, 10, 15);
        labelConstraints.gridwidth = 4;
        panel.add(submitButton, labelConstraints);
        //reset constraints
        labelConstraints.insets = WindowTools.TEXT_FIELD_INSET;
        labelConstraints.gridwidth = 1;

        this.add(panel);
        this.pack();
    }


    private void setUpListener(JFrame frame){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = true;
                String updateInfo = null;

                //get new info to update
                if(comboBox){
                    updateInfo = updateCB.getSelectedItem().toString().toLowerCase(Locale.ROOT).strip();
                    System.out.println(updateInfo);
                }
                else {
                    updateInfo = updateTF.getText().strip();
                }

                //reach into database and update patientProf passed in
                try {
                    if (selection.equals("Address")) {
                        patient.setAddress(updateInfo);
                    } else if (selection.equals("Phone")) {
                        patient.setPhone(updateInfo);
                    } else if (selection.equals("CoPay")) {
                        patient.setCoPay(Float.parseFloat(updateInfo));
                    } else if (selection.equals("Insurance Type")) {
                        patient.setInsuType(updateInfo);
                    } else if (selection.equals("Patient Type")) {
                        System.out.println(updateInfo);
                        patient.setPatientType(updateInfo);
                    } else if (selection.equals("Md Contact")) {
                        patient.getMedCondInfo().setMdContact(updateInfo);
                    } else if (selection.equals("Md Phone")) {
                        patient.getMedCondInfo().setMdPhone(updateInfo);
                    } else if (selection.equals("Illness Type")) {
                        patient.getMedCondInfo().setIllType(updateInfo);
                    } else if (selection.equals("Allergy Type")) {
                        patient.getMedCondInfo().setAlgType(updateInfo);
                    }
                }
                //catch any runtime errors from updating profile
                catch (RuntimeException exception) {
                    success = false;
                    JOptionPane.showMessageDialog(frame, exception.toString(),
                            "Error updating profile", JOptionPane.ERROR_MESSAGE);
                }

                String returnMessage = "Successfully updated " + selection + " to be " + updateInfo;
                if (success) {
                    //save changes
                    try {
                        MenuWindow.DB.writeAllPatientProf(MenuWindow.FILE_PATH);
                    }
                    catch (RuntimeException exception){
                        JOptionPane.showMessageDialog(frame, exception.toString(),
                                "Error saving update", JOptionPane.ERROR_MESSAGE);
                    }
                    //show success message
                    JOptionPane.showMessageDialog(frame, returnMessage,
                            "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        UpdateWindowPopUp popUp = new UpdateWindowPopUp("Address",
                "abc123", "doe", null);
        popUp.setVisible(true);
    }
}
