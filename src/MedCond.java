// MedCond class Keeps track of the special medical conditions for a patient,
// i.e., allergies, life threatening or chronic medical conditions, and physician contact information.
// contains a constructor method and getters and setters

import java.util.Locale;

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
        if(checkAlgType(algType)){
            this.algType = algType;
        }
        else{
            throw new RuntimeException("Unexpected input for AlgType." +
                    "AlgType should be 'none', 'food', 'medication', or 'other'");
        }
        if(checkIllType(illType)){
            this.illType = illType;
        }
        else{
            throw new RuntimeException("Unexpected input for IllType." +
                    "IllType should be 'none', 'CHD', 'diabetes', 'asthma', or 'other'");
        }
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
    public void setMdContact(String c){
        this.mdContact = c;
    }

    public void setMdPhone(String p){
        this.mdPhone = p;
    }

    public void setAlgType(String a) throws RuntimeException{
        if(checkAlgType(a)){
            this.algType = a;
        }
        else{
            throw new RuntimeException("Unexpected input for AlgType." +
                    "AlgType should be 'none', 'food', 'medication', or 'other'");
        }
    }

    public void setIllType(String i) throws RuntimeException{
        if(checkIllType(i)){
            this.illType = i;
        }
        else{
            throw new RuntimeException("Unexpected input for IllType." +
                    "IllType should be 'none', 'CHD', 'diabetes', 'asthma', or 'other'");
        }
    }

    /**
     * Checks if illType matches one of the expected illness types
     * @param illType String of illType
     * @return true if illType matches expected illnesses
     */
    private boolean checkIllType(String illType){
        illType = illType.toLowerCase(Locale.ROOT).strip();
        return illType.equals("none") | illType.equals("chd") |
                illType.equals("diabetes") | illType.equals("asthma") |
                illType.equals("other");
    }


    /**
     * Checks if algType matches one of the expected allergy types.
     * @param algType String of algType
     * @return true if algType matches expected allergies
     */
    private boolean checkAlgType(String algType){
        algType = algType.toLowerCase(Locale.ROOT).strip();
        return algType.equals("none") | algType.equals("food") |
                algType.equals("medication") | algType.equals("other");
    }
}
