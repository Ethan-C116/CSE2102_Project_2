import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PatientProfDB{
    public int numPatient = 0;
    private int currentPatientIndex = 0;
    private String fileName;
    private final List<PatientProf> patientList = new ArrayList<PatientProf>();
    private final String EXTENSION = ".json";


    public PatientProfDB(String filePath) throws IOException, RuntimeException {
        //check if correct type of file
        if(!checkFileExtension(filePath, EXTENSION)){
            throw new RuntimeException("Expected a " + EXTENSION
                    + " file.");
        }

        this.fileName = filePath;

        File DBFile = new File(this.fileName);

        //check if file exists, create if not
        boolean newFile = DBFile.createNewFile();

        //set read/write permission
        if(!DBFile.setReadable(true)){
            throw new RuntimeException("No read permission for DB file");
        }
        if(!DBFile.setWritable(true)){
            throw new RuntimeException("No write permission for DB file");
        }
        //if existing DB file read in the contents
        if(!newFile){
            initializeDatabase(this.fileName);
        }
        //TODO call interface method to prompt new database creation
        //call writeDatabase if wanted
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
     * @return the first patientProf in the database
     */
    public PatientProf findFirstProfile(){
        return this.patientList.get(0);
    }


    /**
     * Gets the next patientProf in the database and iterates the
     * currentPatientIndex. If the index has reached the end it resets to 0.
     * @return the next patientProf in the database
     */
    public PatientProf findNextProfile(){
        PatientProf returnValue = this.patientList.get(this.currentPatientIndex);

        //Make sure the index doesn't go out of bounds
        if(this.currentPatientIndex < this.patientList.toArray().length - 1){
            this.currentPatientIndex++;
        }
        else{
            this.currentPatientIndex = 0;
        }

        return returnValue;
    }


    /**
     * Writes the patientList to the specified filepath as a JSON file.
     * @param filepath the desired filepath of the JSON file
     */
    public void writeAllPatientProf(String filepath) throws RuntimeException{
        File databaseFile = new File(filepath);
        //create file
        try {
            if (!databaseFile.createNewFile()) {
                throw new RuntimeException("Couldn't write database file at "
                        + filepath + ". File already exists with that name");
            }
        }
        catch(IOException e){
            throw new RuntimeException("Couldn't write database file " + e);
        }
        //check writable
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

    }


    /**
     * Scans the contents of a JSON PatientDB file into the DB.
     * @param filePath The filepath of a database JSON file
     */
    public void initializeDatabase(String filePath) throws RuntimeException{
        if(checkFileExtension(filePath, this.EXTENSION)){
            throw new RuntimeException("Expected a " + EXTENSION
                    + " file.");
        }
        this.fileName = filePath;
        Scanner scanner = new Scanner(filePath);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            //TODO
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

}