package Service;

import java.util.List;

public interface FileService {
    List<String[]> readFile(String filename);
    void writeToFile(String filename, List<String[]> data, String[] header);
}
