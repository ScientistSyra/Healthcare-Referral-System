package util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

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
