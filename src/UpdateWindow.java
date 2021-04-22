import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateWindow extends JFrame {
    private final Dimension SCREEN_DIMENSION = WindowTools.getScreenDimension();
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel adminLabel;
    private JLabel nameLabel;
    private JLabel updateFieldLabel;
    private JTextField adminTF;
    private JTextField nameTF;
    private JComboBox<JLabel> updateCB;
    private JButton findButton;

    public UpdateWindow() {
        //create a JFrame
        super("IPS - Update Profile");
        //stop program on close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //adjust window size
        this.setMinimumSize(new Dimension(400, 300));
        //put window in center of screen
        int screenW = (int) SCREEN_DIMENSION.getWidth();
        int screenH = (int) SCREEN_DIMENSION.getHeight();
        //put window off-center
        this.setLocation((screenW - (int) this.getMinimumSize().getWidth()) / 2 + 50,
                (screenH - (int) this.getMinimumSize().getHeight()) / 2 + 30);

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
        titleLabel = new JLabel("Update Profile");
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

        //set up last name label
        nameLabel = new JLabel("Last Name:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        panel.add(nameLabel, gbConstraints);

        //set up last name field
        nameTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 2;
        panel.add(nameTF, textFieldConstraints);

        //set up update field label
        updateFieldLabel = new JLabel("Update Field:");
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 3;
        panel.add(updateFieldLabel, gbConstraints);

        //set up update field drop-down
        String[] updateOptions = {"Address", "Phone", "CoPay", "Insurance Type",
                "Patient Type", "Md Contact", "Md Phone", "Illness Type", "Allergy Type"};

        updateCB = new JComboBox(updateOptions);
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 3;
        panel.add(updateCB, textFieldConstraints);

        //add find button
        findButton = new JButton("Find");
        setUpListener();
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        gbConstraints.insets = new Insets(10, 15, 10, 15);
        gbConstraints.gridwidth = 4;
        panel.add(findButton, gbConstraints);
        //reset constraints
        gbConstraints.insets = WindowTools.TEXT_FIELD_INSET;
        gbConstraints.gridwidth = 1;

        this.add(panel);
        this.pack();
    }

    private void setUpListener(){
        //set up the button listener
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //see what is selected in the ComboBox
                String selection = updateCB.getSelectedItem().toString();
                System.out.println("Update selection: " + selection);

                //TODO find profile
                String adminID = adminTF.getText().strip();
                String lastName = nameTF.getText().strip();


                PatientProf patientProf = null;

                //if profile found:
                //create new window to update
                JFrame updatePopUp = new UpdateWindowPopUp(selection, adminID, lastName, patientProf);
                updatePopUp.setVisible(true);
            }
        });
    }

    private Dimension getScreenDimension(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    //main for testing
    public static void main(String[] args) {
        //create window and display
        UpdateWindow updateWindow = new UpdateWindow();
        updateWindow.setVisible(true);
    }
}
