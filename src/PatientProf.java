public class PatientProf{
    private String adminID;
    private String firstName;
    private String lastName;
    private String phone;
    private Float coPay;
    private String insuType;
    private String patientType;
    private MedCond medCondInfo;

    /**
     * Equality method for comparing PatientProf.
     * Needed to check for duplicates in PatientDB.
     * @param p1 1st PatientProf to check
     * @param p2 2nd PatientProf to check
     * @return True if lastName and adminID match
     */
    public boolean equals(PatientProf p1, PatientProf p2){
        return((p1.adminID.equals(p2.adminID))
                & (p1.lastName.equals(p2.lastName))
        );
    }
}