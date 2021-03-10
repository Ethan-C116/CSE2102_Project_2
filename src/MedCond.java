// MedCond class Keeps track of the special medical conditions for a patient,
// i.e., allergies, life threatening or chronic medical conditions, and physician contact information.

public class MedCond {

    // Attributes
    private String mdContact;
    private String mdPhone;
    private String algType;     // (none, food, medication, other)
    private String illType;     // (none, CHD, diabetes, asthma, other)

    // not sure what this does? need to check in with Ethan
//    public static String medCond(){
//
//    }

    // retrieves the patient's physician contact (i.e. name)
    public void getMdContact(){
        if (this.mdContact != null){
            System.out.println("Patient's Physician Name: " + this.mdContact);
        }
        else{
            System.out.println("Patient has no physician on file");
        }
    }

    // retrieves patient's physician phone number
    public void getMdPhone(){
        if(this.mdPhone != null){
            System.out.println("Patient's Physician Phone Number: " + this.mdPhone);
        }
        else {
            System.out.println("Patient has no physician phone number on file");
        }
    }

    public void getAlgType(){
        if(this.algType != null){
            System.out.println("Patient's Allergy Type: " + this.algType);
        }
        else {
            System.out.println("Patient has no allergies on file");
        }
    }

    public void getIllType(){
        if(this.illType != null){
            System.out.println("Patient's Illness Type: " + this.illType);
        }
        else {
            System.out.println("Patient has no allergies on file");
        }
    }

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
