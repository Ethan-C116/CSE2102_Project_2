import java.util.Locale;
import java.util.Scanner;

public class PatientProfInterface{
    private PatientProfDB DB;
    private String FILEPATH;
    private String adminID;
    private final String WELCOME_MESSAGE = "Welcome to the Integrated Patient Management System!";


    /**
     * @param databaseFilePath String The file path of the database file.
     *                        Can be null or "" if no existing database file.
     */
    public void PatientProfInterface(String databaseFilePath){
        this.FILEPATH = databaseFilePath;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!");

        //prompt adminID and ask if correct
        while(true) {
            System.out.println("Please enter your adminID to begin: ");
            this.adminID = scanner.nextLine();

            System.out.println("You entered: " + this.adminID + ". Correct? (Y/N)");
            String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
            if(response.equals("y")) {
                break;
            }
        }
        scanner.close();

        //initialize DB using given filepath
        try {
            DB = new PatientProfDB(databaseFilePath, this.adminID);
        }
        catch (RuntimeException e){
            System.out.println(e.toString());
        }

        getUserChoice();
    }


    /**
     * Present the user with a menu of options and
     * call the appropriate method(s) for user's choice.
     */
    private void getUserChoice(){
        boolean menuFlag = true;
        System.out.println("Welcome to the Integrated Patient Management System!");

        //display menu until option selected
        while(menuFlag) {
            System.out.println("");
            System.out.println("");
            System.out.println("Please select a menu option by entering its number.");
            System.out.println("1 - Enter a new PatientProf");
            System.out.println("2 - Delete a PatientProf");
            System.out.println("3 - Find a PatientProf");
            System.out.println("4 - Modify a PatientProf");
            System.out.println("5 - Display all Profiles");
            System.out.println("6 - Write changes to database file");
            System.out.println("7 - Load information from database file");
            System.out.println("h - Display help information");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();

            //call appropriate function.
            //if response doesn't match display menu again
            switch (response) {
                case "1" -> {
                    createNewPatientProf();
                    menuFlag = false;
                }
                case "2" -> {
                    deletePatientProf();
                    menuFlag = false;
                }
                case "3" -> {
                    findPatientProf();
                    menuFlag = false;
                }
                case "4" -> {
                    modifyPatientProf();
                    menuFlag = false;
                }
                case "5" -> {
                    displayAllPatientProf();
                    menuFlag = false;
                }
                case "6" -> {
                    writeToDB();
                    menuFlag = false;
                }
                case "7" -> {
                    initDB();
                    menuFlag = false;
                }
                case "h" -> printHelpMessage();
            }
        }

    }

    /**
     * Walks the user through deleting the a PatientProf.
     * Prompts for adminID and lastName to look up profile, displays the profile,
     * and confirms deletion.
     * Loops until user enters exit command.
      */
    private void deletePatientProf(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--Delete Profile--");
        System.out.println("To exit to menu enter '-e'.");
        System.out.println("Enter the last name of the patient you wish to delete.");
        String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
        scanner.close();

        //check to see if exit
        if (checkforExit(response)) {
            getUserChoice();
        }
        //find patient
        PatientProf patient = DB.findProfile(this.adminID, response);

        //if no patient found, start deletePatientProf again
        if(patient == null){
            System.out.println("No patient found.");
            deletePatientProf();
        }
        System.out.println("Found patient profile:");
        displayPatientProf(patient);

        scanner = new Scanner(System.in);
        System.out.println("Do you wish to delete? (Y/N)");
        response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
        scanner.close();

        //if don't want to delete, restart deletePatientProf
        if(!response.equals("y")) {
            deletePatientProf();
        }

        //delete profile
        boolean success = DB.deleteProfile(this.adminID, patient.getLastName());
        if(success){
            System.out.println("Deletion successful");
            deletePatientProf();
        }
        else{
            System.out.println("Deletion failed");
            deletePatientProf();
        }
    }

    /**
     * Walks user through finding a patientProf using adminID and lastName.
     */
    private void findPatientProf(){

    }

    /**
     * Walks the user through updating information stored in
     * a patientProf.
     */
    private void updatePatientProf(){

    }


    /**
     * Prints the desired PatientProf
     */
    private void displayPatientProf(PatientProf patientProf){
        if(patientProf == null){
            System.out.println("No patient information.");
        }
        else {
            System.out.println("\tLast: " + patientProf.getLastName());
            System.out.println("\tFirst: " + patientProf.getFirstName());
            System.out.println("\tPhone: " + patientProf.getPhone());
            System.out.println("\tType: " + patientProf.getPatientType());
            System.out.println("\tInsurance: " + patientProf.getInsuType());
            System.out.println("\tCoPay: " + patientProf.getCoPay());
            System.out.println("\tMedical:");
            MedCond medcond = patientProf.getMedCondInfo();
            System.out.println("\t\tMedical Contact: " + medcond.getMdContact());
            System.out.println("\t\tContact phone: " + medcond.getMdPhone());
            System.out.println("\t\tIllnesses: " + medcond.getIllType());
            System.out.println("\t\tAllergies: " + medcond.getAlgType()));
            System.out.println("");
        }
    }


    /**
     * Prints a list of every patient in the DB.
     */
    private void displayAllPatientProf(){

    }


    /**
     * Writes a all patients in the DB to the database file.
     */
    private void writeToDB(){
        try {
            DB.writeAllPatientProf(this.FILEPATH);
        }
        //if error, print and go back to menu
        catch(RuntimeException e){
            System.out.println(e.toString());
            getUserChoice();
        }
        //print success and go back to menu
        System.out.println("Write Successful");
        getUserChoice();
    }


    /**
     * Initialize a patientProfDB.
     */
    private void initDB(){

    }


    /**
     * Walk user through creating a PatientProf.
     * @return PatientProf
     */
    private PatientProf createNewPatientProf(){
        PatientProf patientProf = null;
        return patientProf;
    }


    /**
     * Walks the user through creating a new MedCond Object.
     * @return MedCond
     */
    private MedCond createNewMedCond(){
        MedCond medCond = null;
        return medCond;
    }

    /**
     * Walks the user through modifying a PatientProf.
     */
    private void modifyPatientProf(){

    }


    private void printHelpMessage(){

    }

    private boolean checkforExit(String message){
        message = message.toLowerCase(Locale.ROOT).strip();
        if(message.equals("-e")){
            return true;
        }
        else{
            return false;
        }
    }
}