package util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    // Overwrite file (used for header)
    public static void overwrite(String filePath, String line) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            fw.write(line);
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error overwriting CSV file: " + filePath);
            e.printStackTrace();
        }
    }

    // Append line to file
    public static void append(String filePath, String line) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(line);
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + filePath);
            e.printStackTrace();
        }
    }
}
