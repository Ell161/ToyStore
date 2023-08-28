package Service;

import java.util.Map;

public interface DataService {
    void create(Map<String, String> dict);
    void update(Map<String, String> dict);
    void delete(String id);
}
