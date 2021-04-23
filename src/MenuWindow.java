import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    private JLabel titleLabel;
    private JPanel mainPanel;
    private ButtonGroup menuButtonGroup;
    private JRadioButton createRB;
    private JRadioButton deleteRB;
    private JRadioButton updateRB;
    private JRadioButton findRB;
    private JRadioButton displayRB;
    private JButton submitButton;
    GridBagConstraints labelConstraints;



    public MenuWindow (){
        //create a JFrame
        super("IPS - Main menu");
        //stop program on close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //adjust window size
        this.setMinimumSize(new Dimension(400, 300));
        //put window in center of screen
        int[] coordinates = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(coordinates[0], coordinates[1]);

        //create panel
        mainPanel = new JPanel(new GridBagLayout());
        labelConstraints = WindowTools.getLabelConstraints();

        //title
        titleLabel = new JLabel("Integrated Patient System");
        //make font bigger by changing size of current font
        titleLabel.setFont(titleLabel.getFont().deriveFont(15.0F));

        //adjust external padding of label
        labelConstraints.insets = WindowTools.SPACED_INSET;

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        mainPanel.add(titleLabel, labelConstraints);
        //reset inset to default
        labelConstraints.insets = WindowTools.DEFAULT_INSET;

        //store radioButtons in a group and add to panel
        menuButtonGroup = new ButtonGroup();

        createRB = new JRadioButton("Create Profile");
        menuButtonGroup.add(createRB);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 1;
        mainPanel.add(createRB, labelConstraints);

        deleteRB = new JRadioButton("Delete Profile");
        menuButtonGroup.add(deleteRB);
        labelConstraints.gridy = 2;
        mainPanel.add(deleteRB, labelConstraints);

        updateRB = new JRadioButton("Update Profile");
        menuButtonGroup.add(updateRB);
        labelConstraints.gridy = 3;
        mainPanel.add(updateRB, labelConstraints);

        findRB = new JRadioButton("Find/Display Profile");
        menuButtonGroup.add(findRB);
        labelConstraints.gridy = 4;
        mainPanel.add(findRB, labelConstraints);

        displayRB = new JRadioButton("Display All Profiles");
        menuButtonGroup.add(displayRB);
        labelConstraints.gridy = 5;
        mainPanel.add(displayRB, labelConstraints);

        //set up submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //see what RadioButton is selected
                if(createRB.isSelected()){
                    System.out.println(createRB.getText());
                }
                else if(deleteRB.isSelected()){
                    System.out.println(deleteRB.getText());
                }
                else if(updateRB.isSelected()){
                    System.out.println(updateRB.getText());
                }
                else if(findRB.isSelected()){
                    System.out.println(findRB.getText());
                }
                else if(displayRB.isSelected()){
                    System.out.println(displayRB.getText());
                }
            }
        });
        //add submit button to panel
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 6;
        labelConstraints.insets = WindowTools.EXTRA_SPACED_INSET;
        mainPanel.add(submitButton, labelConstraints);
        labelConstraints.insets = WindowTools.DEFAULT_INSET;

        //add panel to frame
        this.add(mainPanel);
        this.pack();

    }

    //main for testing
    public static void main(String[] args) {
        //create window and display
        MenuWindow menu = new MenuWindow();
        menu.setVisible(true);
    }

}
