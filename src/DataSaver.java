import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> recs = new ArrayList<>();
        boolean notDone = false;
        int Counter = 1;
        int birthYear;
        int idNumber;
        String firstName = "";
        String lastName = "";
        String email = "";
        String fileName = "";

        do {

            firstName = SafeInput.getNonZeroLenString(sc, "Enter your first name.");
            lastName = SafeInput.getNonZeroLenString(sc, "Enter your last name.");
            email = SafeInput.getRegExString(sc, "Enter your email address", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$]");
            birthYear = SafeInput.getRangedInt(sc, "Enter your birth year.", 0, 9999);
            idNumber = SafeInput.getRangedInt(sc, "Enter your id number", 000001, 999999);
            recs.add(String.format("%s, %s, %s, %04d", firstName, lastName, idNumber, email, birthYear));
            notDone = SafeInput.getYNConfirm(sc, "Do you want to enter another record?[y/n]");

        } while (notDone);
        
        fileName = SafeInput.getNonZeroLenString(sc, "Enter your file name. Please add a .csv extension.");
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getAbsolutePath(), fileName);
        
        try {
            
            OutputStream out =
            new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = 
                    new BufferedWriter(new OutputStreamWriter(out));
            for (String rec : recs) {
                writer.write(rec, 0, rec.length());
                writer.newLine();}
            writer.close();
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();}

    }
}
