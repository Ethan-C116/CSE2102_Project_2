import javax.swing.*;
import java.awt.*;

public class WindowTools {
    static final int DEFAULT_PADDING_X = 10;
    static final int DEFAULT_PADDING_Y = 10;
    static final Insets DEFAULT_INSET = new Insets(0, 5, 0, 10);
    static final Insets SPACED_INSET = new Insets(5, 5, 5,5);
    static final Insets TEXT_FIELD_INSET = new Insets(10, 0, 5,15);
    static final Insets EXTRA_SPACED_INSET = new Insets(15, 0, 15,0);

    public static Dimension getScreenDimension(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     *
     * @param frame the JFrame you're using
     * @return int - the x-coordinate that will place the frame in the center of the screen
     */
    private static int windowCenterX(JFrame frame){
        Dimension screenDimension = getScreenDimension();
        int screenW = (int) screenDimension.getWidth();
        return (screenW - (int) frame.getMinimumSize().getWidth())/2;
    }

    /**
     *
     * @param frame the JFrame you're using
     * @return int - the y-coordinate that will place the frame in the center of the screen
     */
    private static int windowCenterY(JFrame frame){
        Dimension screenDimension = getScreenDimension();
        int screenH = (int) screenDimension.getHeight();
        return (screenH - (int) frame.getMinimumSize().getHeight())/2;
    }

    /**
     *
     * @param frame - the JFrame you're working with
     * @return int[] - the (x, y) coordinates for the window location
     */
    public static int[] getWindowStartCoordinates(JFrame frame){
        return new int[]{windowCenterX(frame), windowCenterY(frame)};
    }

    /**
     *
     * @return GridBagConstraints - a constraints set up for Labels
     */
    public static GridBagConstraints getLabelConstraints() {
        //constraints for labels
        GridBagConstraints gbConstraints = new GridBagConstraints();
        //layout starts center
        gbConstraints.anchor = GridBagConstraints.CENTER;
        //set internal padding to space out elements
        gbConstraints.ipadx = DEFAULT_PADDING_X;
        gbConstraints.ipady = DEFAULT_PADDING_Y;
        gbConstraints.insets = SPACED_INSET;

        return gbConstraints;
    }

    /**
     *
     * @return GridBagConstraints - constraints set up for TextFields
     */
    public static GridBagConstraints getTextFieldConstraints() {
        //constraints for text fields
        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        //layout starts at center left
        textFieldConstraints.anchor = GridBagConstraints.LINE_START;
        //set internal padding to space out elements
        textFieldConstraints.ipadx = DEFAULT_PADDING_X;
        textFieldConstraints.ipady = DEFAULT_PADDING_Y;
        textFieldConstraints.insets = TEXT_FIELD_INSET;
        //make text fields expand horizontally
        textFieldConstraints.gridwidth = GridBagConstraints.REMAINDER;
        textFieldConstraints.weightx = 1;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;

        return textFieldConstraints;
    }
}
