import java.util.Locale;
import java.util.Scanner;

public class PatientProfInterface{
    private PatientProfDB DB;
    private boolean noFileFlag; //indicates whether DB file is present
    private String adminID;
    private final String WELCOME_MESSAGE = "Welcome to the Integrated Patient Management System!";


    /**
     * @param databaseFilePath String The file path of the database file.
     *                        Can be null or "" if no existing database file.
     */
    public void PatientProfInterface(String databaseFilePath){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!");

        //prompt adminID and ask if correct
        while(true) {
            System.out.println("Please enter your adminID to begin: ");
            this.adminID = scanner.nextLine();

            System.out.println("You entered: " + this.adminID". Correct? (Y/N)");
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
      */
    private void deletePatientProf(){

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
    private void displayPatientProf(){

    }


    /**
     * Prints a list of every patient in the DB.
     */
    private void displayAllPatientProf(){

    }


    /**
     * Writes a patientProf to the DB.
     */
    private void writeToDB(){

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

    }


    /**
     * Walks the user through creating a new MedCond Object.
     * @return MedCond
     */
    private MedCond createNewMedCond(){

    }

    /**
     * Walks the user through modifying a PatientProf.
     */
    private void modifyPatientProf(){

    }


    private void printHelpMessage(){

    }

}