import java.io.*;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PatientProfInterface{
    private PatientProfDB DB;
    private String FILEPATH;
    private String adminID;
    private final String HELPFILEPATH = "C:\\Users\\Ethan\\IdeaProjects\\CSE2102_Project_1\\src\\help_info.txt";
    private final String WELCOME_MESSAGE = "Welcome to the Integrated Patient Management System!";


    /**
     * @param databaseFilePath String The file path of the database file.
     *                        Can be null or "" if no existing database file.
     */
    public PatientProfInterface(String databaseFilePath){
        this.FILEPATH = databaseFilePath;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!");

        //prompt adminID and ask if correct
        while(true) {
            System.out.println("Please enter your adminID to begin:");
            this.adminID = scanner.nextLine();

            System.out.println("You entered: " + this.adminID + ". Correct? (Y/N)");
            String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
            if(response.equals("y")) {
                break;
            }
        }

        //initialize DB using given filepath
        try {
            DB = new PatientProfDB(databaseFilePath, this.adminID);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            System.exit(1);
        }

        getUserChoice();
    }


    /**
     * Present the user with a menu of options and
     * call the appropriate method(s) for user's choice.
     */
    private void getUserChoice(){
        System.out.println("------------------------------");
        System.out.println("Welcome to the Integrated Patient Management System!");
        System.out.println("Changes to the database must be saved by writing to the database file.");

        //display menu until option selected
        while(true) {
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
                    PatientProf patientProf = createNewPatientProf();
                    DB.insertNewProfile(patientProf);
                }
                case "2" -> {
                    deletePatientProf();
                }
                case "3" -> {
                    findPatientProf();
                }
                case "4" -> {
                    modifyPatientProf();
                }
                case "5" -> {
                    displayAllPatientProf();
                }
                case "6" -> {
                    writeToDB();
                }
                case "7" -> {
                    initDB();
                }
                case "h" -> {
                    try {
                        printHelpMessage();
                    } catch (IOException e) {
                        System.out.println("Error printing help message." + e.toString());
                    }
                }
            }
        }

    }

    /**
     * Walks the user through deleting the a PatientProf.
     * Prompts for adminID and lastName to look up profile, displays the profile,
     * and confirms deletion.
      */
    private void deletePatientProf(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--Delete Profile--");
        System.out.println("To exit to menu enter '-e'.");
        System.out.println("Enter the last name of the patient you wish to delete.");
        String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();

        //check to see if exit
        if (checkForExit(response)) {
            getUserChoice();
        }
        //find patient
        PatientProf patient = DB.findProfile(this.adminID, response);

        //if no patient found, start deletePatientProf again
        if(patient == null){
            System.out.println("No patient found.");
        }
        else {
            System.out.println("Found patient profile:");
            displayPatientProf(patient);

            System.out.println("Do you wish to delete? (Y/N)");
            response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();

            //delete profile
            if (response.equals("y")) {
                boolean success = DB.deleteProfile(this.adminID, patient.getLastName());
                if (success) {
                    System.out.println("Deletion successful");
                } else {
                    System.out.println("Deletion failed");
                }
            } else {
                System.out.println("Did not delete.");
            }
        }
    }


    /**
     * Walks user through finding a patientProf using adminID and lastName.
     * Displays the profile if patient found. Will loop until user enters exit command.
     */
    private void findPatientProf(){
        PatientProf patientProf = findAndReturnPatientProf();
        displayPatientProf(patientProf);
    }


    /**
     * Walks user through finding a patient profile and returns that profile.
     * @return the found patient profile
     */
    private PatientProf findAndReturnPatientProf(){
        System.out.println("--Find Patient Profile--");
        System.out.println("Enter '-e' to return to menu.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the last name of the patient to find.");
        String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();

        //check to see if exit
        if (checkForExit(response)) {
            getUserChoice();
        }

        //find patient
        PatientProf patient = DB.findProfile(this.adminID, response);

        //if no patient found, start again
        if(patient == null){
            System.out.println("No patient found.");
        }

        return patient;
    }


    /**
     * Walks the user through updating information stored in
     * a patientProf.
     */
    private void updatePatientProf(PatientProf patient){
        System.out.println("--Update a Patient Prof--");
        System.out.println("Current patient info: ");
        displayPatientProf(patient);
        String update;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which info would you like to change");
        System.out.println("1 - Address");
        System.out.println("2 - Phone");
        System.out.println("3 - CoPay");
        System.out.println("4 - Insurance Type");
        System.out.println("5 - Patient Type");
        System.out.println("6 - Medical Conditions");
        String response = scanner.nextLine().toLowerCase(Locale.ROOT).strip();

        //check which option was selected and update that option
        switch(response){
            case "1" -> {
                System.out.println("Enter new address:");
                update = scanner.nextLine().strip();
                patient.setAddress(update);
            }
            case "2" -> {
                System.out.println("Enter new phone:");
                update = scanner.nextLine().strip();
                patient.setPhone(update);
            }
            case "3" -> {
                System.out.println("Enter new CoPay:");
                update = scanner.nextLine().strip();
                patient.setCoPay(Float.parseFloat(update));
            }
            case "4" -> {
                System.out.println("Enter new Insurance Type (Government, Private):");
                update = scanner.nextLine().strip();
                try {
                    patient.setInsuType(update);
                }
                catch (RuntimeException e){
                    System.out.println(e.toString());
                }
            }
            case "5" -> {
                System.out.println("Enter new Patient Type (Pediatric, Adult, Senior):");
                update = scanner.nextLine().strip();
                try {
                    patient.setPatientType(update);
                }
                catch (RuntimeException e){
                    System.out.println(e.toString());
                }
            }
            case "6" ->{
                System.out.println("What to update?");
                System.out.println("1 - MdContact");
                System.out.println("2 - MdPhone");
                System.out.println("3 - Illness Type");
                System.out.println("4 - Allergy Type");
                String medResponse = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
                switch(medResponse){
                    case "1" -> {
                        System.out.println("Enter new MdContact:");
                        update = scanner.nextLine().strip();
                        patient.getMedCondInfo().setMdContact(update);
                    }
                    case "2" -> {
                        System.out.println("Enter new MdPhone:");
                        update = scanner.nextLine().strip();
                        patient.getMedCondInfo().setMdPhone(update);
                    }
                    case "3" -> {
                        System.out.println("Enter new illness type:");
                        update = scanner.nextLine().strip();
                        try {
                            patient.getMedCondInfo().setIllType(update);
                        }
                        catch (RuntimeException e){
                            System.out.println(e.toString());
                        }
                    }
                    case "4" -> {
                        System.out.println("Enter new allergy type:");
                        update = scanner.nextLine().strip();
                        try {
                            patient.getMedCondInfo().setAlgType(update);
                        }
                        catch (RuntimeException e){
                            System.out.println(e.toString());
                        }
                    }
                    default -> {
                        System.out.println("Invalid Input");
                    }
                }
            }
            default -> {
                System.out.println("Invalid Input");
            }
        }
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
            System.out.println("\t\tAllergies: " + medcond.getAlgType());
            System.out.println("");
        }
    }


    /**
     * Prints every patient in the DB for the user's adminID.
     */
    private void displayAllPatientProf(){
        List<PatientProf> patientList = DB.getPatientList();
        for(PatientProf patient:patientList){
            if(patient.getAdminID().equals(this.adminID)){
                displayPatientProf(patient);
            }
        }
    }


    /**
     * Writes a all patients in the DB to the database file.
     */
    private void writeToDB(){
        boolean success = true;
        try {
            DB.writeAllPatientProf(this.FILEPATH);
        }
        //if error, print and go back to menu
        catch(RuntimeException e){
            System.out.println(e.toString());
            success = false;
        }
        //print success
        if(success){
            System.out.println("Write Successful");
        }
        else{
            System.out.println("Write Unsuccessful");
        }
    }


    /**
     * Initialize a patientProfDB. Replaces the existing DB if needed.
     */
    private void initDB(){
        try {
            DB.initializeDatabase(this.FILEPATH);
        }
        catch (RuntimeException e){
            System.out.println("Unable to read in database. " + e.toString());
        }
        System.out.println("Database loaded");
    }


    /**
     * Walk user through creating a PatientProf.
     * @return PatientProf
     */
    private PatientProf createNewPatientProf(){
        Scanner scanner = new Scanner(System.in);
        String last, first, phone, address, insuType, patientType;
        float coPay;

        System.out.println("--Create new patient profile--");
        System.out.println("Enter the following patient information: ");
        System.out.println("First Name:");
        first = scanner.nextLine().strip();
        System.out.println("Last name:");
        last = scanner.nextLine().strip();
        System.out.println("Phone:");
        phone = scanner.nextLine().strip();
        System.out.println("Address:");
        address = scanner.nextLine();
        System.out.println("Insurance type (government, private):");
        insuType = scanner.nextLine();
        System.out.println("CoPay:");
        coPay = Float.parseFloat(scanner.nextLine());
        System.out.println("Patient type (pediatric, adult, senior):");
        patientType = scanner.nextLine();
        MedCond medCond = createNewMedCond();

        PatientProf patientProf = null;
        try {
            patientProf = new PatientProf(this.adminID, first, last, address,
                    phone, coPay, insuType, patientType, medCond);
        }
        catch(RuntimeException e){
            System.out.println("Error creating patient. " + e.toString());
            getUserChoice();
        }

        return patientProf;
    }


    /**
     * Walks the user through creating a new MedCond Object.
     * @return MedCond
     */
    private MedCond createNewMedCond(){
        System.out.println("--New medCond--");
        Scanner scanner = new Scanner(System.in);
        String mdContact, mdPhone, illType, algType;

        System.out.println("Patient medical contact:");
        mdContact = scanner.nextLine().strip();
        System.out.println("Medical contact's phone number:");
        mdPhone = scanner.nextLine().strip();
        System.out.println("Patient illness (none, CHD, diabetes, asthma, other):");
        illType = scanner.nextLine().strip();
        System.out.println("Patient allergies (none, food, medication, other):");
        algType = scanner.nextLine().strip();

        MedCond medCond = null;
        try{
            medCond = new MedCond(mdContact, mdPhone, algType, illType);
        }
        catch(RuntimeException e){
            System.out.println("Error creating patient. " + e.toString());
        }
        return medCond;
    }

    /**
     * Walks the user through modifying a PatientProf.
     * Finds a patient using findAndReturnPatientProf() and then calls
     * updatePatientProf to change it.
     */
    private void modifyPatientProf(){
        System.out.println("--Modify a patient profile--");
        PatientProf patientProf = findAndReturnPatientProf();
        if(patientProf != null){
            updatePatientProf(patientProf);
        }
    }


    private void printHelpMessage() throws IOException{
        File helpInfo = new File(this.HELPFILEPATH);
        Scanner scanner = new Scanner(helpInfo);

        //read and display the help file
        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }

        scanner.close();
        getUserChoice();
    }

    private boolean checkForExit(String message){
        message = message.toLowerCase(Locale.ROOT).strip();
        if(message.equals("-e")){
            return true;
        }
        else{
            return false;
        }
    }
}