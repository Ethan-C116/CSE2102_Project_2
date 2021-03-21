import org.jetbrains.annotations.Nullable;

import java.util.Locale;

@SuppressWarnings("ALL")
public class PatientProf{
    private String adminID;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private Float coPay;
    //insuType is "Private" or "Government"
    private String insuType;
    //patientType is "Pediatric", "Adult", or "Senior"
    private String patientType;
    private MedCond medCondInfo;

    public PatientProf(String adminID, String firstName, String lastName, String address,
                       String phone, Float coPay, String insuType,
                       String patientType, @Nullable MedCond medCondInfo) throws RuntimeException{
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.coPay = coPay;
        //check insuType is correct
        insuType = insuType.toLowerCase(Locale.ROOT).strip();
        if(insuType.equals("private") | insuType.equals("government")){
            this.insuType = insuType;
        }
        else{
            throw new RuntimeException("Insurance type should be 'Private'" +
                    " or 'Government' not " + insuType);
        }

        //check patientType is correct
        patientType = patientType.toLowerCase(Locale.ROOT).strip();
        if(patientType.equals("pediatric") | patientType.equals("adult")
                | patientType.equals("senior")){
            this.patientType = patientType;
        }
        else{
            throw new RuntimeException("Patient type should be 'Pediatric', " +
                    "'Adult', or 'Senior' not " + patientType);
        }
        this.medCondInfo = medCondInfo;
    }

    public String getAdminID() {
        return adminID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Float getCoPay() {
        return coPay;
    }

    public String getInsuType() {
        return insuType;
    }

    public String getPatientType() {
        return patientType;
    }

    public MedCond getMedCondInfo() {
        return medCondInfo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCoPay(Float coPay) {
        this.coPay = coPay;
    }

    public void setInsuType(String insuType) throws  RuntimeException{
        if(insuType.equals("private") | insuType.equals("government")){
            this.insuType = insuType;
        }
        else{
            throw new RuntimeException("Insurance type should be 'Private'" +
                    " or 'Government' not " + insuType);
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPatientType(String patientType) throws RuntimeException {
        if(patientType.equals("pediatric") | patientType.equals("adult")
                | patientType.equals("senior")){
            this.patientType = patientType;
        }
        else{
            throw new RuntimeException("Patient type should be 'Pediatric', " +
                    "'Adult', or 'Senior' not " + patientType);
        }
    }
}