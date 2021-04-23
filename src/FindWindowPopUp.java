import javax.swing.*;
import java.awt.*;

public class FindWindowPopUp extends JFrame {
    GridBagConstraints labelConstraints = WindowTools.getLabelConstraints();

    public FindWindowPopUp(PatientProf patientProf){
        //set up frame
        super("IPS - Find Patient Results");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 500));
        //start popUp off center
        int[] location = WindowTools.getWindowStartCoordinates(this);
        this.setLocation(location[0] + 20, location[1] + 20);

        //make arrays to hold strings for labels and info
        String[] patientInfo = {patientProf.getAdminID(), patientProf.getLastName(),
                patientProf.getFirstName(), patientProf.getAddress(), patientProf.getPhone(),
                patientProf.getCoPay().toString(), patientProf.getInsuType(), patientProf.getPatientType(),
                patientProf.getMedCondInfo().getMdContact(), patientProf.getMedCondInfo().getMdPhone(),
                patientProf.getMedCondInfo().getAlgType(), patientProf.getMedCondInfo().getIllType()};

        String[] labels = {"Admin ID:", "Last Name:", "First Name:", "Address:", "Phone:",
                "CoPay:", "Insurance Type:", "Patient Type:", "Md Contact:", "Md Phone:",
                "Allergies:", "Illness:"};

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel title = new JLabel("Patient Results");
        title.setFont(title.getFont().deriveFont((float)14.0));
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 4;
        panel.add(title, labelConstraints);
        labelConstraints.gridwidth = 1;

        int y = 1;
        //add labels down column x = 0
        labelConstraints.gridx = 0;
        for(int i = 0; i < labels.length; i++){
            labelConstraints.gridy = y;
            panel.add(new JLabel(labels[i]), labelConstraints);
            y++;
        }
        //add info down column x = 1;
        y = 1;
        labelConstraints.gridx = 1;
        for(int i = 0; i < patientInfo.length; i++){
            labelConstraints.gridy = y;
            panel.add(new JLabel(patientInfo[i]), labelConstraints);
            y++;
        }

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
}
