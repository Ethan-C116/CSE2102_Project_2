import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PatientProfDB{
    private int numPatient = 0;
    private int currentPatientIndex = 0;
    private String fileName;
    private String adminID; //adminID of the user
    private final List<PatientProf> patientList = new ArrayList<PatientProf>();
    private final String EXTENSION = "txt";
    //used to see if findNextProfile has gone through entire list
    private boolean findNextProfileReachedEnd = false;
    //used to indicate that the adminID has no patients in list
    private boolean noProfilesMatching = false;


    public PatientProfDB(String filePath, String adminID) throws RuntimeException {
        //check if correct type of file
        if(!checkFileExtension(filePath, EXTENSION)){
            throw new RuntimeException("Expected a " + EXTENSION
                    + " file.");

        }
        this.adminID = adminID;
        this.fileName = filePath;

        File DBFile = new File(this.fileName);

        //check if file exists, create if not
        boolean newFile = false;
        try {
            newFile = DBFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("IO error occurred creating file.");
        }

        //if existing DB file read in the contents
        if(!newFile){
            System.out.println("File already exists. Initializing existing DB at " + this.fileName);
            initializeDatabase(this.fileName);
        }
        else{
            System.out.println("No existing DB file found at " + this.fileName +
                    ". Created a new DB file.");
            writeAllPatientProf(this.fileName);
        }
    }


    /**
     * Adds the given patientProf to the database's List of patients
     * @param patientProf patient profile to add
     */
    public void insertNewProfile(PatientProf patientProf){
        this.patientList.add(patientProf);
        this.numPatient++;
    }


    /**
     * Deletes the patient profile associated with the adminID and
     * lastName given. If successful returns true.
     * @param adminID the adminID of the profile to delete
     * @param lastName the lastName of the profile to delete
     * @return true if delete successful
     */
    public boolean deleteProfile(String adminID, String lastName){
        boolean returnValue;
        PatientProf profile = this.findProfile(adminID, lastName);

        //patient will only delete if adminID nad lastName on profile match
        //this is bc findProfile() would return null if they didn't
        if(profile != null){
            this.patientList.remove(profile);
            this.numPatient--;
            returnValue = true;
        }
        else{
            returnValue = false;
        }

        return returnValue;
    }


    /**
     * Finds the patient profile that matches the adminID and lastName.
     * @param adminID the adminID on the profile to find
     * @param lastName the lastName on the profile to find
     * @return the associated PatientProfile. Will be Null if not found.
     */
    public @Nullable PatientProf findProfile(String adminID, String lastName){
        lastName = lastName.strip();
        PatientProf returnValue = null;
        //go through each patient in the DB
        for(PatientProf patient: this.patientList){
            //if adminID and lastName match return the patient
            if(patient.getAdminID().equals(adminID) &
                    (patient.getLastName().equals(lastName) |
                            patient.getLastName().toLowerCase(Locale.ROOT).equals(lastName.toLowerCase(Locale.ROOT)))){
                returnValue = patient;
                break;
            }
        }
        return returnValue;
    }


    /**
     * Finds and returns the first patient in the database that matches the user's adminID.
     * @return the first patientProf in the database. Null if no patient with matching adminID is in database
     */
    public @Nullable PatientProf findFirstProfile(){
        //if the first patient in list matches adminID get that
        PatientProf patient = null;
        for(int i = 0; i < this.patientList.toArray().length; i++){
            PatientProf patientProf = this.patientList.get(i);
            if(patientProf.getAdminID().equals(this.adminID)){
                patient = patientProf;
                break;
            }
        }

        return patient;
    }


    /**
     * Gets the next patientProf in the database that matches the user's adminID
     * and iterates the currentPatientIndex. If the index has reached the end it resets to 0.
     * @return the next patientProf in the database, or null if no patient in database matching adminID.
     */
    public @Nullable PatientProf findNextProfile(){
        while(this.currentPatientIndex < this.patientList.toArray().length){
            //start by checking if next patient matches adminID
            PatientProf patientProf = this.patientList.get(this.currentPatientIndex);
            if(patientProf.getAdminID().equals(this.adminID)) {
                //if adminID matches, return that profile
                currentPatientIndex++;
                //make array loop back to beginning
                if(currentPatientIndex >= this.patientList.toArray().length){
                    currentPatientIndex = 0;
                }
                return patientProf;
            }

            currentPatientIndex++;
            //make array loop back to beginning
            if(currentPatientIndex >= this.patientList.toArray().length){
                currentPatientIndex = 0;
            }

        }
        //if nothing is found in array
        return null;
    }

    public List<PatientProf> getPatientList(){
        return this.patientList;
    }


    /**
     * Writes the patientList to the specified filepath.
     * @param filepath the desired filepath of the file
     */
    public void writeAllPatientProf(String filepath) throws RuntimeException{
        /*
         * Data is written in the form of:
         * adminID
         * First
         * Last
         * address
         * phone
         * copay
         * patientType
         * insuType
         * mdContact
         * mdPhone
         * algType
         * illType
         * ..next patient data starts
         */

        File databaseFile = new File(filepath);
        //create a new file.
        try {
            databaseFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("IOException in writeAllPatientProf " + e);
        }

        //set writable
        if(!databaseFile.setWritable(true)){
            throw new RuntimeException("No write permission for the document");
        }

        //create a fileWriter to write to the created file
        FileWriter writer;
        try{
            writer = new FileWriter(databaseFile);
        }
        catch (IOException e){
            throw new RuntimeException("Couldn't create FileWriter" + e);
        }

        //write patient information
        for(PatientProf patientProf : this.patientList){
            StringBuilder patientInfo = new StringBuilder();
            //add info from patientProf
            patientInfo.append(patientProf.getAdminID()).append("\n");
            patientInfo.append(patientProf.getFirstName()).append("\n");
            patientInfo.append(patientProf.getLastName()).append("\n");
            patientInfo.append(patientProf.getAddress()).append("\n");
            patientInfo.append(patientProf.getPhone()).append("\n");
            patientInfo.append(patientProf.getCoPay()).append("\n");
            patientInfo.append(patientProf.getPatientType()).append("\n");
            patientInfo.append(patientProf.getInsuType()).append("\n");
            //adds info from the patient's medCond
            patientInfo.append(patientProf.getMedCondInfo().getMdContact()).append("\n");
            patientInfo.append(patientProf.getMedCondInfo().getMdPhone()).append("\n");
            patientInfo.append(patientProf.getMedCondInfo().getAlgType()).append("\n");
            patientInfo.append(patientProf.getMedCondInfo().getIllType()).append("\n");

            try {
                writer.write(patientInfo.toString());
            }
            catch (IOException e){
                throw new RuntimeException("Failed to write database file " + e);
            }
        }

        //close file
        try {
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            throw new RuntimeException("FileWriter couldn't close properly. Data may not have saved.");
        }
    }


    /**
     * Scans the contents of a PatientDB file into the DB. This does not check for
     * duplicates before adding a profile to the DB.
     * @param filePath The filepath of a database file
     */
    public void initializeDatabase(String filePath) throws RuntimeException{
        //open file
        File file;
        try {
            file = new File(filePath);
        }
        catch(NullPointerException e){
            throw new RuntimeException("Unable to find file '" + filePath + "'.");
        }

        this.fileName = filePath;
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e){
            throw new RuntimeException("Unable to find file '" + filePath + "'.");
        }

        String adminID, firstName, lastName, address, phone, insuType, patientType;
        float coPay;
        String mdContact, mdPhone, algType, illType;
        //scan through file
        while(fileScanner.hasNextLine()){
            //get patient info
            adminID = fileScanner.nextLine();
            firstName = fileScanner.nextLine();
            lastName = fileScanner.nextLine();
            address = fileScanner.nextLine();
            phone = fileScanner.nextLine();
            coPay = Float.parseFloat(fileScanner.nextLine());
            patientType = fileScanner.nextLine();
            insuType = fileScanner.nextLine();
            //get medCond info for patient
            mdContact = fileScanner.nextLine();
            mdPhone = fileScanner.nextLine();
            algType = fileScanner.nextLine();
            illType = fileScanner.nextLine();

            //construct patient and add to database
            PatientProf patient = new PatientProf(adminID, firstName, lastName,
                    address, phone, coPay, insuType, patientType,
                    new MedCond(mdContact, mdPhone, algType, illType));
            this.patientList.add(patient);
        }

        //close scanner
        try {
            fileScanner.close();
        } catch (IllegalStateException e) {
            throw new RuntimeException("Couldn't close file Scanner. " + e.toString());
        }
    }


    /**
     * Checks if the provided filename has the given file extension.
     * Does not handle illegal filenames.
     * @param fileName the file name to check the extension of
     * @param extension the file extension to check against
     * @return True if the file extension matches
     */
    private boolean checkFileExtension(String fileName, String extension){
        fileName = fileName.toLowerCase();
        String[] splitPath = fileName.split("\\.");
        String fileExtension = splitPath[splitPath.length - 1];
        return(fileExtension.equals(extension));
    }


    private void constructTestPatientList(int numPatients){
        for(int i = 0; i < numPatients; i++){
            String firstName = "john" + i;
            String lastName = "doe" + i;
            String address = "123 Street St";
            String phone = "8604864357";
            float coPay = (float) 0.00;
            String insuType = "Private";
            String patientType = "Adult";
            String mdContact = "Dr. Nancy";
            String mdPhone = "8604864357";
            String algType = "None";
            String illType = "None";

            MedCond medCond = new MedCond(mdContact, mdPhone, algType, illType);
            PatientProf patient = new PatientProf(this.adminID,
                    firstName, lastName, address, phone, coPay, insuType,
                    patientType, medCond);

            insertNewProfile(patient);
        }
    }

}