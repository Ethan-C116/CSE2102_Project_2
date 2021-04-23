
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CreateProfile extends JPanel{
    private JFrame mainFrame;

    public CreateProfile(JFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame; JLabel title;

        // create components
        JPanel choicePanel = createSimpleDialogueBox();

        title = new JLabel("Create Profile", JLabel.CENTER);
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));

        //Lay out the main panel.
        add(title, BorderLayout.NORTH);
        add(choicePanel, BorderLayout.CENTER);
    }

    private JPanel createSimpleDialogueBox() {
        JButton selectButton = null;
        JLabel[] labels = new JLabel[12];
        JTextField[] textFields = new JTextField[8];
        JComboBox[] jcbuttons = new JComboBox[4];

        // admin ID label and text box
        labels[0] = new JLabel("Admin ID: ");
        labels[0].setBounds(150, 0, 100, 20);
        textFields[0] = new JTextField();
        textFields[0].setBounds(120,30,100,20);

        // name label and text box
        labels[1] = new JLabel("First Name: ");
        labels[1].setBounds(10,60,100,20);
        textFields[1] = new JTextField();
        textFields[1].setBounds(120,60,100,20);

        labels[2]= new JLabel("Last Name: ");
        labels[2].setBounds(10,90,100,20);
        textFields[2] = new JTextField();
        textFields[2].setBounds(120,90,100,20);

        // address label and text box
        labels[3] = new JLabel("Address: ");
        labels[3].setBounds(10,120,100,20);
        textFields[3] = new JTextField();
        textFields[3].setBounds(120,120,100,20);

        // phone label and text box
        labels[4] = new JLabel("Phone: ");
        labels[4].setBounds(10,150,100,20);
        textFields[4] = new JTextField();
        textFields[4].setBounds(120,150,100,20);

        // copay label and text box
        labels[5] = new JLabel("Co-Pay: ");
        labels[5].setBounds(10,180,100,20);
        textFields[5] = new JTextField();
        textFields[5].setBounds(120,180,100,20);

        // insurance type
        labels[6] = new JLabel("Insur. Type: ");
        labels[6].setBounds(10,210,100,20);
        // dropdown menu
        String[] insurTypes = {"Private", "Government"};
        jcbuttons[0] = new JComboBox(insurTypes);
        jcbuttons[0].setBounds(120,210,100,20);

        // patient type
        labels[7] = new JLabel("Patient Type: ");
        labels[7].setBounds(10,240,100,20);
        // dropdown menu
        String[] patientTypes = {"Pediatric", "Adult", "Senior"};
        jcbuttons[1] = new JComboBox(patientTypes);
        jcbuttons[1].setBounds(120,240,100,20);


        // md contact and text box
        labels[8] = new JLabel("Md Contact: ");
        labels[8].setBounds(10,270,150,20);
        textFields[6] = new JTextField();
        textFields[6].setBounds(120,270,100,20);

        // md phone and text box
        labels[9] = new JLabel("Md Phone: ");
        labels[9].setBounds(10,300,100,20);
        textFields[7] = new JTextField();
        textFields[7].setBounds(120,300,100,20);

        // allergies
        labels[10] = new JLabel("Allergies: ");
        labels[10].setBounds(10,330,100,20);
        String[] allTypes = {"none", "food", "medication", "other"};
        jcbuttons[2] = new JComboBox(allTypes);
        jcbuttons[2].setBounds(120,330,100,20);

        // illnesses
        labels[11] = new JLabel("Illnesses: ");
        labels[11].setBounds(10,360,100,20);
        String[] illTypes  = {"none", "CHD", "diabetes", "asthma", "other"};
        jcbuttons[3] = new JComboBox(illTypes);
        jcbuttons[3].setBounds(120,360,100,20);

        // bottom JButton to select
        selectButton = new JButton("Select");
        selectButton.setBounds(120, 400, 100, 20);


        return createPane(labels, textFields, jcbuttons, selectButton);
    }

    private JPanel createPane(JLabel[] labels, JTextField[] textFields, JComboBox[] jcbuttons, JButton showButton) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));

        box.add(labels[0]);
        box.add(textFields[0]);
        box.add(labels[1]);
        box.add(textFields[1]);
        box.add(labels[2]);
        box.add(textFields[2]);
        box.add(labels[3]);
        box.add(textFields[3]);
        box.add(labels[4]);
        box.add(textFields[4]);
        box.add(labels[5]);
        box.add(textFields[5]);
        box.add(labels[6]);
        box.add(jcbuttons[0]);
        box.add(labels[7]);
        box.add(jcbuttons[1]);
        box.add(labels[8]);
        box.add(textFields[6]);
        // md phone
        box.add(labels[9]);
        box.add(textFields[7]);
        // allergies
        box.add(labels[10]);
        box.add(jcbuttons[2]);
        // illnesses
        box.add(labels[11]);
        box.add(jcbuttons[3]);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.NORTH);
        pane.add(showButton, BorderLayout.SOUTH);
        return pane;
    }

    public static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Create Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 600));
        int[] location = WindowTools.getWindowStartCoordinates(frame);
        frame.setLocation(location[0], location[1]);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add(new CreateProfile(frame));

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
