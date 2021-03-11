// MedCond class Keeps track of the special medical conditions for a patient,
// i.e., allergies, life threatening or chronic medical conditions, and physician contact information.
// contains a constructor method and getters and setters

public class MedCond {

    // Attributes
    private String mdContact;
    private String mdPhone;
    private String algType;     // (none, food, medication, other)
    private String illType;     // (none, CHD, diabetes, asthma, other)

    // constructor class
    public MedCond(String mdContact, String mdPhone, String algType, String illType){
        this.mdContact = mdContact;
        this.mdPhone = mdPhone;
        this.algType = algType;
        this.illType = illType;
    }

    // Getters for class
    public String getMdContact(){
        return this.mdContact;
    }

    public String getMdPhone(){
        return this.mdPhone;
    }

    public String getAlgType(){
        return this.algType; 
    }

    public String getIllType(){
        return this.illType;
    }
    
    // setters for class
    public void updateMdContact(String c){
        this.mdContact = c;
    }

    public void updateMdPhone(String p){
        this.mdPhone = p;
    }

    public void updateAlgType(String a){
        this.algType = a;
    }

    public void updateIllType(String i){
        this.illType = i;
    }

}
