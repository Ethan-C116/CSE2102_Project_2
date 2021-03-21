import java.util.Scanner;

public class CSE2102_Project_1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path of an existing database file" +
                "or the desired file path for a new database:");
        String filePath = scanner.nextLine();
        scanner.close();
        PatientProfInterface patientProfInterface =  new PatientProfInterface(filePath);
    }

}
