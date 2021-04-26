import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteProfileWindow extends JFrame{
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel adminLabel;
    private JLabel nameLabel;
    private JLabel updateFieldLabel;
    private JTextField adminTF;
    private JTextField nameTF;
    private JComboBox<JLabel> updateCB;
    private JButton deleteButton;
    private JFrame frame;

    public DeleteProfileWindow() {
        //create a JFrame
        super("IPS - Delete Profile");
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
        titleLabel = new JLabel("Delete Profile");
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


        //add find button
        deleteButton = new JButton("Delete");
        setUpListener();
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        gbConstraints.insets = new Insets(10, 15, 10, 15);
        gbConstraints.gridwidth = 4;
        panel.add(deleteButton, gbConstraints);
        //reset constraints
        gbConstraints.insets = WindowTools.TEXT_FIELD_INSET;
        gbConstraints.gridwidth = 1;

        this.add(panel);
        this.pack();
    }

    private void setUpListener(){
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminID = adminTF.getText().strip();
                String lastName = nameTF.getText().strip();

                boolean deleted = MenuWindow.DB.deleteProfile(adminID, lastName);
                if (!deleted) {
                    JOptionPane.showMessageDialog(null, "Patient Profile Does Not Exist");
                } else {
                    // deletion was succesfful
                    JOptionPane.showMessageDialog(null, "Patient Profile Deleted Successfully!");
                }

            }
        });

    }


    public static void main(String[] args) throws Exception {
        //create window and display
        DeleteProfileWindow deleteWindow = new DeleteProfileWindow();
        deleteWindow.setVisible(true);
    }
}
