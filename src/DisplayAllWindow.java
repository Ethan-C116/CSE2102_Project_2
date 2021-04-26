import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllWindow extends JFrame{
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel adminLabel;
    private JTextField adminTF;
    private JButton submitButton;
    private JFrame frame;

    public DisplayAllWindow() {
        //create a JFrame
        super("IPS - Display Profiles");
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
        titleLabel = new JLabel("Display Profiles");
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

        //add submit button
        submitButton = new JButton("Submit");
        setUpListener();
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
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
        //set up the button listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get adminID from adminTF and pass to pop up window
                String adminID = adminTF.getText().strip();
                new DisplayAllWindowPopUp(adminID).setVisible(true);
            }
        });
    }
}
