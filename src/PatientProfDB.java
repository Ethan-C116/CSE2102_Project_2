import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PatientProfDB{
    public int numPatient = 0;
    private int currentPatientIndex;
    private String fileName;
    private final List<PatientProf> patientList = new ArrayList<PatientProf>();
    private final String EXTENSION = "csv";

    public PatientProfDB(String filePath) throws IOException, RuntimeException {
        //check if correct type of file
        if(checkFileExtension(filePath, EXTENSION)){
            throw new RuntimeException("Expected a " + EXTENSION
                    + " file.");
        }

        File DBFile = new File(filePath);

        //check if file exists, create if not
        boolean newFile = DBFile.createNewFile();

        //set read/write permission
        if(!DBFile.setReadable(true)){
            throw new IOException("No read permission for DB file");
        }
        if(DBFile.setWritable(true)){
            throw new IOException("No write permission for DB file");
        }
        //if existing DB file read in the contents
        if(newFile){
            initializeDatabase(filePath);
        }
    }

    /**
     * Scans the contents of a DBfile into the DB.
     * @param filePath The filepath of a database csv file
     */
    public void initializeDatabase(String filePath){
        Scanner scanner = new Scanner(filePath);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            //TODO
        }

    }

    /**
     * Checks the file extension and throws an exception
     * @param fileName the file name to check the extension of
     * @param extension the file extension to check against
     * @return True if the file extension matches
     */
    private boolean checkFileExtension(String fileName, String extension){
        fileName = fileName.toLowerCase();
        String[] splitPath = fileName.split(".");
        String fileExtension = splitPath[splitPath.length - 1];
        return(fileExtension.equals(extension));
    }
}