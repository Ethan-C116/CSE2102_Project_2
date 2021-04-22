import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    private final Dimension SCREEN_DIMENSION = WindowTools.getScreenDimension();

    private JLabel titleLabel;
    private JPanel mainPanel;
    private ButtonGroup menuButtonGroup;
    private JRadioButton createRB;
    private JRadioButton deleteRB;
    private JRadioButton updateRB;
    private JRadioButton findRB;
    private JRadioButton displayRB;
    private JButton submitButton;


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
        GridBagConstraints gbConstraints = new GridBagConstraints();
        //layout starts at center top
        gbConstraints.anchor = GridBagConstraints.PAGE_START;
        //set internal padding to space out elements
        gbConstraints.ipadx = WindowTools.DEFAULT_PADDING_X;
        gbConstraints.ipady = WindowTools.DEFAULT_PADDING_Y;

        //title
        titleLabel = new JLabel("Integrated Patient System");
        //make font bigger by changing size of current font
        titleLabel.setFont(titleLabel.getFont().deriveFont(15.0F));

        //adjust external padding of label
        gbConstraints.insets = WindowTools.SPACED_INSET;

        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        mainPanel.add(titleLabel, gbConstraints);
        //reset inset to default
        gbConstraints.insets = WindowTools.DEFAULT_INSET;

        //store radioButtons in a group and add to panel
        menuButtonGroup = new ButtonGroup();

        createRB = new JRadioButton("Create Profile");
        menuButtonGroup.add(createRB);
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 1;
        mainPanel.add(createRB, gbConstraints);

        deleteRB = new JRadioButton("Delete Profile");
        menuButtonGroup.add(deleteRB);
        gbConstraints.gridy = 2;
        mainPanel.add(deleteRB, gbConstraints);

        updateRB = new JRadioButton("Update Profile");
        menuButtonGroup.add(updateRB);
        gbConstraints.gridy = 3;
        mainPanel.add(updateRB, gbConstraints);

        findRB = new JRadioButton("Find/Display Profile");
        menuButtonGroup.add(findRB);
        gbConstraints.gridy = 4;
        mainPanel.add(findRB, gbConstraints);

        displayRB = new JRadioButton("Display All Profiles");
        menuButtonGroup.add(displayRB);
        gbConstraints.gridy = 5;
        mainPanel.add(displayRB, gbConstraints);

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
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 6;
        gbConstraints.insets = WindowTools.EXTRA_SPACED_INSET;
        mainPanel.add(submitButton, gbConstraints);
        gbConstraints.insets = WindowTools.DEFAULT_INSET;

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
