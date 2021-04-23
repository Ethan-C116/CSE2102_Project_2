
import javax.swing.*;
import java.awt.*;

public class DeleteProfile extends JPanel {

    private JFrame mainFrame;
    private JLabel lb1, lb2;
    private JTextField tf1, tf2;

    public DeleteProfile(JFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame;
        JLabel title;

        // create components
        JPanel choicePanel = createSimpleDialogueBox();

        title = new JLabel("Delete Profile", JLabel.CENTER);
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));

        //Lay out the main panel.
        add(title, BorderLayout.NORTH);
        add(choicePanel, BorderLayout.CENTER);
    }

    private JPanel createSimpleDialogueBox() {
        JButton selectButton = null;

        lb1 = new JLabel("Admin ID:");
        lb1.setBounds(150, 0, 100, 20);
        tf1 = new JTextField();
        tf1.setBounds(120,30,100,20);

        lb2 = new JLabel("Last Name:");
        lb2.setBounds(10,60,100,20);
        tf2 = new JTextField();
        tf2.setBounds(120,60,100,20);

        // bottom JButton to select
        selectButton = new JButton("Select");

        return createPane(lb1, lb2, tf1, tf2, selectButton);
    }

    private JPanel createPane(JLabel lb1, JLabel lb2, JTextField tf1, JTextField tf2, JButton showButton) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));

        box.add(lb1);
        box.add(tf1);
        box.add(lb2);
        box.add(tf2);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.NORTH);
        pane.add(showButton, BorderLayout.SOUTH);
        return pane;

    }

    public static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Delete Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 300));
        int[] location = WindowTools.getWindowStartCoordinates(frame);
        frame.setLocation(location[0], location[1]);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add(new DeleteProfile(frame));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

}