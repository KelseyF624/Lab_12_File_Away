import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.Scanner;

public class FileInspector {

    JFileChooser chooser = new JFileChooser();
    File selectedFile;
    String aString = "";
    String line;
    ArrayList<String> lines = new ArrayList<>();
    int lineCount = 0;
    int wordCount = 0;
    int charCount = 0;
    Scanner inFile;
    Path target = new File(System.getProperty("user.dir")).toPath();
    target =target.resolve("src");
    chooser.setCurrentDirectory(target.toFile);


    try{

        File workingDirectory = chooser.getCurrentDirectory();
        chooser.setCurrentDirectory(workingDirectory);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            Path File = selectedFile.toPath();
            InputStream in = null;
            try {
                in = new BufferedInputStream(Files.newInputStream(File, CREATE));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        }
        while (reader.ready()) {
            aString = reader.readLine();
            lineCount++;
            System.out.printf("\nLine %4d %-60s ", lineCount, aString);
        }
        reader.close();
        System.out.println("\n\nData file read.");
    }

} catch (FileNotFoundException e){
    System.out.println("File not found.");
    e.printStackTrace();}

catch (IOException e) {
    throw new RuntimeException(e);}
}
}
