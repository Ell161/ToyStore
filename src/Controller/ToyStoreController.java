package Controller;

import Service.CsvFileService;
import Service.ToyDataService;
import View.ToyStoreView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToyStoreController {
    String file = "data.csv";
    ToyDataService dataService = new ToyDataService();
    CsvFileService fileService = new CsvFileService();
    ToyStoreView view = new ToyStoreView();

    public void start() {
        setDataToys();
        String menuItem = view.getMenuItem();
        switch (menuItem) {
            case "1" -> dataService.toysToString();
            case "2" -> setToy();
            case "3" -> setToy();
            case "4" -> setToy();
            case "5" -> System.out.println("The program is completed.");
        }
    }

    private void setDataToys() {
        List<String[]> dataToys = fileService.readFile(file);
        String[] header = dataToys.get(0);
        for (int i = 1; i < dataToys.size(); i++) {
            String[] data = dataToys.get(i);
            Map<String, String> toy = new HashMap<>();
            for (int j = 0; j < data.length; j++) {
                toy.put(header[j], data[j]);
            }
            dataService.create(toy);
        }
    }

    private void setToy() {
        Map<String, String> toy = new HashMap<>() {{
            put("id", view.getId());
            put("name", view.getName());
            put("count", view.getCount());
            put("chanceOfFallingOut", view.getChanceOfFallingOut());
        }};
        try {
            dataService.create(toy);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setToy();
        }

        String[] header = {"id", "name", "count", "chanceOfFallingOut"};
        List<String[]> dataToys = dataService.getDataToys();
        fileService.writeToFile(file, dataToys, header);
    }
}
