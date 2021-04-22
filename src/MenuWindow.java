import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    private final Dimension SCREEN_DIMENSION = getScreenDimension();
    private final int DEFAULT_PADDING_X = 10;
    private final int DEFAULT_PADDING_Y = 10;
    private final Insets DEFAULT_INSET = new Insets(0, 0, 0, 0);
    private final Insets SPACED_INSET = new Insets(10, 0, 10,0);
    private final Insets EXTRA_SPACED_INSET = new Insets(15, 0, 15,0);

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
        int screenW = (int)SCREEN_DIMENSION.getWidth();
        int screenH = (int)SCREEN_DIMENSION.getHeight();
        //put window in the center
        this.setLocation((screenW - (int) this.getMinimumSize().getWidth())/2,
                (screenH - (int) this.getMinimumSize().getHeight())/2);

        //create panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbConstraints = new GridBagConstraints();
        //layout starts at center top
        gbConstraints.anchor = GridBagConstraints.PAGE_START;
        //set internal padding to space out elements
        gbConstraints.ipadx = DEFAULT_PADDING_X;
        gbConstraints.ipady = DEFAULT_PADDING_Y;

        //title
        titleLabel = new JLabel("Integrated Patient System");
        //make font bigger by changing size of current font
        titleLabel.setFont(titleLabel.getFont().deriveFont(15.0F));

        //adjust external padding of label
        gbConstraints.insets = SPACED_INSET;

        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        mainPanel.add(titleLabel, gbConstraints);
        //reset inset to default
        gbConstraints.insets = DEFAULT_INSET;

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
        gbConstraints.insets = EXTRA_SPACED_INSET;
        mainPanel.add(submitButton, gbConstraints);
        gbConstraints.insets = DEFAULT_INSET;

        //add panel to frame
        this.add(mainPanel);
        this.pack();

    }

    private Dimension getScreenDimension(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    //main for testing
    public static void main(String[] args) {
        //create window and display
        MenuWindow menu = new MenuWindow();
        menu.setVisible(true);
    }

}
