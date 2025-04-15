import javax.swing.JFileChooser;
import java.io.File;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInspector {
    public static void main(String[] args) {

        Scanner inFile;
        String line;
        JFileChooser chooser = new JFileChooser();
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        Path target = new File(System.getProperty("user.dir") + "\\src\\main\\java\\FileInspector.java").toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(new File("src"));

        try {

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    System.out.println(line);
                    lineCount = lineCount + 1;
                    wordCount = line.split("\\s+").length + wordCount;
                    charCount = line.length() + charCount;}

                System.out.println("\n Summary report: ");
                System.out.println("File: " + target.toFile().getName());
                System.out.println("Line: " + lineCount);
                System.out.println("Word: " + wordCount);
                System.out.println("Characters: " + charCount);
                inFile.close();}

            else { System.out.println("No such file or directory. Terminating.");
                    System.exit(0);}
    } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
            System.exit(0);}

    }
}