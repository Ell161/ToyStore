package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileService implements FileService {
    private static final String delimiter = ";";

    /**
     * @param filename string value of the file path
     * @return list of arrays of string values obtained when reading from a file
     */
    @Override
    public List<String[]> readFile(String filename) {
        List<String[]> data = new ArrayList<>();
        try {
            File file = new File(filename);
            if (!file.exists()) return null;
            BufferedReader br = Files.newBufferedReader(Paths.get(filename));
            String line;
            while ((line = br.readLine()) != null) {
                // convert line into tokens
                String[] tokens = line.split(delimiter);
                data.add(tokens);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    /**
     * @param filename string value of the file path
     * @param data     list of arrays of string values
     * @param header   array of string values of table column names
     */
    @Override
    public void writeToFile(String filename, List<String[]> data, String[] header) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
            writer.write(String.join(delimiter, header));
            writer.newLine();

            for (String[] record : data) {
                writer.write(String.join(delimiter, record));
                writer.newLine();
            }

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
