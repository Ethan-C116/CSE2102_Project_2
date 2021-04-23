import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FindWindow extends JFrame {
    private JLabel adminLabel, nameLabel, titleLabel;
    private JTextField adminTF, nameTF;
    private JButton findButton;
    private JPanel panel;
    private GridBagConstraints labelConstraints;
    private GridBagConstraints textFieldConstraints;



    public FindWindow(){
        //create frame and set values
        super("IPS - Find Patient");

        this.setMinimumSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //get location for window
        int[] location = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(location[0], location[1]);

        //create GridBagLayout panel
        panel = new JPanel(new GridBagLayout());
        labelConstraints = WindowTools.getLabelConstraints();
        textFieldConstraints = WindowTools.getTextFieldConstraints();

        setUpLabels();
        setUpTextFields();

        findButton = new JButton("Find");
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 3;
        textFieldConstraints.gridwidth = 4;
        textFieldConstraints.insets = new Insets(10, 15, 10, 15);
        panel.add(findButton, textFieldConstraints);
        textFieldConstraints.gridwidth = 1;
        textFieldConstraints.insets = WindowTools.TEXT_FIELD_INSET;

        JFrame frame = this;
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get adminID and name from text fields
                String adminID = adminTF.getText().strip();
                String lastName = nameTF.getText().strip();

                //TODO call find patient method
                //TODO remove driver code
                PatientProf patientProf = new PatientProf("123", "doe", "john",
                        "street", "860", (float)1.00, "private", "adult",
                        new MedCond("doctor", "doc phone", "none", "none"));

                if(patientProf != null){
                    //create dialog to show patient
                    FindWindowPopUp popUp = new FindWindowPopUp(patientProf);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "No results found.",
                            "IPS - Patent Results",  JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        this.add(panel);
        this.pack();

    }

    private void setUpLabels() {
        titleLabel = new JLabel("Find");
        //make title bigger
        titleLabel.setFont(titleLabel.getFont().deriveFont((float) 14.0));
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 4;
        panel.add(titleLabel, labelConstraints);
        labelConstraints.gridwidth = 1;

        adminLabel = new JLabel("Admin ID:");
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 1;
        panel.add(adminLabel, labelConstraints);

        nameLabel = new JLabel("Last Name:");
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 2;
        panel.add(nameLabel, labelConstraints);
    }

    private void setUpTextFields(){
        adminTF = new JTextField();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        panel.add(adminTF, textFieldConstraints);

        nameTF = new JTextField();
        textFieldConstraints.gridy = 2;
        panel.add(nameTF, textFieldConstraints);

    }

    public static void main(String[] args) {
        FindWindow window = new FindWindow();
        window.setVisible(true);
    }
}
