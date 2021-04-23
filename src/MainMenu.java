
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainMenu extends JPanel {
    JFrame frame;

    public MainMenu(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(400, 300));

        //center the frame;
        int[] location = WindowTools.getWindowStartCoordinates(this.frame);
        this.frame.setLocation(location[0], location[1]);

        JLabel title;

        // create components
        JPanel choicePanel = createSimpleDialogueBox();

        title = new JLabel("Integrated Patient System", JLabel.CENTER);
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));

        //Lay out the main panel.
        add(title, BorderLayout.NORTH);
        add(choicePanel, BorderLayout.CENTER);

    }

    private JPanel createSimpleDialogueBox() {
        // JRadioButtons for menu options
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];

        final ButtonGroup group = new ButtonGroup();

        JButton selectButton = null;

        radioButtons[0] = new JRadioButton("Create Profile");
        radioButtons[1] = new JRadioButton("Delete Profile");
        radioButtons[2] = new JRadioButton("Update Profile");
        radioButtons[3] = new JRadioButton("Find/Display Profile");
        radioButtons[4] = new JRadioButton("Display All Profiles");

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }

        // bottom JButton to select menu option
        selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtons[0].isSelected()){
                    //create

                }
                else if(radioButtons[1].isSelected()){
                    //delete
                }
                else if(radioButtons[2].isSelected()){
                    //update
                    UpdateWindow update = new UpdateWindow();
                    update.setVisible(true);
                }
                else if(radioButtons[3].isSelected()){
                    //find
                    FindWindow find = new FindWindow();
                    find.setVisible(true);
                }
                else if(radioButtons[4].isSelected()){
                    //display all
                    DisplayAllWindow display = new DisplayAllWindow();
                    display.setVisible(true);
                }
            }
        });

        return createPane(radioButtons, selectButton);
    }

    private JPanel createPane(JRadioButton[] radioButtons, JButton showButton) {
        int numChoices = radioButtons.length;
        JPanel box = new JPanel();

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.NORTH);
        pane.add(showButton, BorderLayout.SOUTH);
        return pane;
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add(new MainMenu(frame));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

