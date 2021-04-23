import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateWindowPopUp extends JFrame {
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel adminLabel;
    private JLabel admin;
    private JLabel nameLabel;
    private JLabel name;
    private JLabel updateLabel;
    private JTextField updateTF;
    private JButton submitButton;

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

        //set up frame
        this.setMinimumSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //put window off-center
        int[] coordinates = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(coordinates[0], coordinates[1]);


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
        titleLabel = new JLabel("Update");
        gbConstraints.insets = WindowTools.SPACED_INSET;
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

        admin = new JLabel(adminID);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        admin.setFont(admin.getFont().deriveFont(Font.BOLD));
        panel.add(admin, textFieldConstraints);

        //set up last name label
        nameLabel = new JLabel("Last Name:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        panel.add(nameLabel, gbConstraints);

        name = new JLabel(lastName);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 2;
        name.setFont(name.getFont().deriveFont(Font.BOLD));
        panel.add(name, textFieldConstraints);

        //set up update label
        updateLabel = new JLabel(selection + ":");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 3;
        panel.add(updateLabel, gbConstraints);

        //set up update text field
        updateTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 3;
        panel.add(updateTF, textFieldConstraints);

        //set up submit button
        submitButton = new JButton("Submit");
        setUpListener(this);
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        gbConstraints.insets = new Insets(10, 15, 10, 15);
        gbConstraints.gridwidth = 4;
        panel.add(submitButton, gbConstraints);
        //reset constraints
        gbConstraints.insets = WindowTools.TEXT_FIELD_INSET;
        gbConstraints.gridwidth = 1;

        this.add(panel);
        this.pack();
    }


    private void setUpListener(JFrame frame){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get new info to update
                String updateInfo = updateTF.getText().strip();
                //TODO reach into database and update patientProf passed in

                boolean success = true;
                String returnMessage = "";
                if(success) {
                    //show success message
                    JOptionPane.showMessageDialog(frame, returnMessage, "Operation successful", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    //show error message
                    JOptionPane.showMessageDialog(frame, returnMessage, "Operation failed", JOptionPane.WARNING_MESSAGE);
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
