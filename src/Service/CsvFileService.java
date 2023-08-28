package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileService implements FileService {
    private static final String delimiter = ";";

    @Override
    public List<String[]> readFile(String filename) {
        List<String[]> data = new ArrayList<>();
        try {
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
