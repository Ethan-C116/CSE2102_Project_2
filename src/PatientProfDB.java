import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;

public class PatientProfDB{
    public int numPatient = 0;
    private int currentPatientIndex = 0;
    private String fileName;
    private String adminID; //adminID of the user
    private final List<PatientProf> patientList = new ArrayList<PatientProf>();
    private final String EXTENSION = "json";
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
                    ". Continue to start a new DB file.");
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
        PatientProf returnValue = null;
        //go through each patient in the DB
        for(PatientProf patient: this.patientList){
            //if adminID and lastName match return the patient
            if(patient.getAdminID().equals(adminID) &
                    patient.getLastName().equals(lastName)){
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
    /*
    public @Nullable PatientProf findNextProfile(){
        PatientProf returnValue = null;

        //if currentPatientIndex is the end of the array set it to 0
        //to loop back to start of the array
        boolean resetFlag = false;
        if(this.currentPatientIndex == this.patientList.toArray().length - 1){
            System.out.println("Time to reset");
            this.currentPatientIndex = 0;
            resetFlag = true;
        }


        //start at next patient
        int startIndex;
        if(resetFlag){
            startIndex = this.currentPatientIndex;
        }
        else{
            startIndex = this.currentPatientIndex + 1;
        }

        for(int i = startIndex; i < this.patientList.toArray().length; i++){
            PatientProf patientProf = this.patientList.get(i);
            System.out.println("patient at i = " + i + " is " + patientProf.getFirstName() + " " + patientProf.getLastName());
            //if the patientProf matches the adminID return it
            this.currentPatientIndex ++;
            if(patientProf.getAdminID().equals(this.adminID)){
                System.out.println("Admin ID for " + patientProf.getFirstName() + " " + patientProf.getLastName() + " matches.");
                returnValue = patientProf;
                break;
            }
        }

        return returnValue;
    }
     */


    /**
     * Writes the patientList to the specified filepath as a JSON file.
     * @param filepath the desired filepath of the JSON file
     */
    public void writeAllPatientProf(String filepath) throws RuntimeException{
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

        //construct JSONArray to write to file
        JSONArray patientJsonArray = constructPatientJsonArray(this.patientList);

        //write file
        try {
            writer.write(patientJsonArray.toString(4));
        }
        catch(IOException e){
            throw new RuntimeException("Failed to write database file " + e);
        }

        //close file
        try {
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            throw new RuntimeException("FileWriter couldn't close properly.");
        }
    }


    /**
     * Scans the contents of a JSON PatientDB file into the DB.
     * @param filePath The filepath of a database JSON file
     */
    public void initializeDatabase(String filePath) throws RuntimeException{
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        }
        catch(FileNotFoundException e){
            throw new RuntimeException("Unable to find file '" + filePath + "'.");
        }


        this.fileName = filePath;
        JSONTokener tokener = new JSONTokener(fileReader); //tokener to read JSON from file
        JSONArray array;
        try {
            array = new JSONArray(tokener); //JSON array from file
        }
        catch (JSONException e){
            throw new RuntimeException("Error parsing file " + this.fileName + ". " + e.toString());
        }


        int JSONIndex = 0;
        //iterate through JSONArray until exception
        while(true){
            JSONObject JSONPatient;
            //try to get next object in array
            try{
                JSONPatient = array.getJSONObject(JSONIndex);
            }
            //if no more objects or other problem break loop
            catch (JSONException e){
                break;
            }

            //initialize temp values to store read patient info
            String adminID, firstName, lastName, phone, insuType, patientType;
            float coPay;
            String mdContact, mdPhone, algType, illType;

            try {
                //get patientProf values from JSON object
                adminID = JSONPatient.getString("adminID");
                firstName = JSONPatient.getString("firstName");
                lastName = JSONPatient.getString("lastName");
                phone = JSONPatient.getString("phone");
                coPay = JSONPatient.getFloat("coPay");
                insuType = JSONPatient.getString("insuType");
                patientType = JSONPatient.getString("patientType");

                //get patient's MedCondInfo values
                JSONObject JSONMedCond = JSONPatient.getJSONObject("medCondInfo");
                mdContact = JSONMedCond.getString("mdContact");
                mdPhone = JSONMedCond.getString("mdPhone");
                algType = JSONMedCond.getString("algType");
                illType = JSONMedCond.getString("illType");
            }
            catch(JSONException e){
                throw new RuntimeException("Error reading in database file." +
                        "Bad JSON file or information missing.");
            }

            //create patientProf
            MedCond medCond = new MedCond(mdContact, mdPhone, algType, illType);
            PatientProf patientProf = new PatientProf(adminID, firstName, lastName,
                    phone, coPay, insuType, patientType, medCond);
            //add patientProf to DB
            insertNewProfile(patientProf);

            //iterate index
            JSONIndex++;
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't close fileReader.");
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


    /**
     * Constructs a JSONArray that contains all patients in the patientList
     * @param patientList List of PatientProfs
     * @return a JSONArray containing all patients in patientList
     */

    private JSONArray constructPatientJsonArray(List<PatientProf> patientList){
        JSONArray dbArray = new JSONArray();
        //iterate over patients in DB
        for(PatientProf patient: this.patientList){
            //create object for each patient
            JSONObject patientObj = new JSONObject();
            //fill out patient information
            patientObj.put("adminID", patient.getAdminID());
            patientObj.put("firstName", patient.getFirstName());
            patientObj.put("lastName", patient.getLastName());
            patientObj.put("phone", patient.getPhone());
            patientObj.put("coPay", patient.getCoPay());
            patientObj.put("insuType", patient.getInsuType());
            patientObj.put("patientType", patient.getPatientType());

            //create medCondObj to add to patientObj
            JSONObject medCondObj = new JSONObject();
            MedCond medCond = patient.getMedCondInfo();
            medCondObj.put("mdContact", medCond.getMdContact());
            medCondObj.put("mdPhone", medCond.getMdPhone());
            medCondObj.put("algType", medCond.getAlgType());
            medCondObj.put("illType", medCond.getIllType());
            //put the medCondObj into patientObj
            patientObj.put("medCondInfo", medCondObj);

            //add patient object to dbArray
            dbArray.put(patientObj);
        }

        return dbArray;
    }

    private void constructTestPatientList(int numPatients){
        for(int i = 0; i < numPatients; i++){
            String firstName = "john" + i;
            String lastName = "doe" + i;
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
                    firstName, lastName, phone, coPay, insuType,
                    patientType, medCond);

            insertNewProfile(patient);
        }
    }

}