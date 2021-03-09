import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PatientProfDB{
    public int numPatient = 0;
    private int currentPatientIndex;
    private String fileName;
    private final List<PatientProf> patientList = new ArrayList<PatientProf>();
    private final String EXTENSION = ".json";


    public PatientProfDB(String filePath) throws IOException, RuntimeException {
        //check if correct type of file
        if(!checkFileExtension(filePath, EXTENSION)){
            throw new RuntimeException("Expected a " + EXTENSION
                    + " file.");
        }

        File DBFile = new File(filePath);

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
            initializeDatabase(filePath);
        }
        //TODO call interface method to prompt new database creation
        //call writeDatabase if wanted
    }


    /**
     * Scans the contents of a JSON PatientDB file into the DB.
     * @param filePath The filepath of a database JSON file
     */
    public void initializeDatabase(String filePath){
        Scanner scanner = new Scanner(filePath);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            //TODO
        }

    }


    /**
     * Writes the PatientDB to the specified filepath as a JSON file.
     * @param filepath the desired filepath of the JSON file
     */
    private void writeDatabase(String filepath) throws RuntimeException{
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