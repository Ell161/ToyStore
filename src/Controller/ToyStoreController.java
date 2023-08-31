package Controller;

import Model.Toy;
import Service.CsvFileService;
import Service.ToyDataService;
import View.ToyStoreView;

import java.util.*;

public class ToyStoreController {
    String fileDataToys = "dataToys.csv";
    String fileDataPrizeToys = "dataPrizeToys.csv";
    String[] header = {"id", "name", "count", "chanceOfFallingOut"};
    Deque<Toy> listPrizeToys = new ArrayDeque<>();
    ToyDataService dataService = new ToyDataService();
    CsvFileService fileService = new CsvFileService();
    ToyStoreView view = new ToyStoreView();

    public void start() {
        setListToys();
        setListPrizeToys();
        int program = 1;
        while (program == 1) {
            String menuItem = view.getMenuItem();
            switch (menuItem) {
                case "1" -> dataService.toysToString();
                case "2" -> setToy();
                case "3" -> setChanceOfFallingOut();
                case "4" -> getPrizeToy();
                case "5" -> {
                    System.out.println("The program is completed.");
                    program = 0;
                }
            }
        }
    }


    /**
     * @apiNote The method reads data from a file and creates a list of instances of the Toy class
     */
    private void setListToys() {
        List<String[]> dataToys = fileService.readFile(fileDataToys);
        if (dataToys != null) {
            if (dataToys.size() > 1) {
                for (int i = 1; i < dataToys.size(); i++) {
                    String[] data = dataToys.get(i);
                    if (data.length == header.length) {
                        Map<String, String> toy = new HashMap<>();
                        for (int j = 0; j < data.length; j++) {
                            toy.put(header[j], data[j]);
                        }
                        dataService.create(toy);
                    }
                }
            }
        }
    }

    /**
     * @apiNote The method requests the toy's id, name, quantity and chance of falling out
     * and creates an instance of the Toy class. Writes new data to a file.
     */
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
        }
        List<String[]> dataToys = dataService.getDataToys();
        fileService.writeToFile(fileDataToys, dataToys, header);
        System.out.println("\nThe information has been successfully saved!");
    }

    /**
     * @apiNote The method requests new toy's chance of falling out
     * and updates an instance of the Toy class. Writes new data to a file.
     */
    private void setChanceOfFallingOut() {
        String toyID = view.getId();
        Toy toy = dataService.getToy(toyID);
        if (toy != null) {
            Double chanceOfFallingOut = Double.parseDouble(view.getChanceOfFallingOut());
            toy.setChanceOfFallingOut(chanceOfFallingOut);
            Map<String, String> data = new HashMap<>() {{
                put("id", toy.getId());
                put("chanceOfFallingOut", String.valueOf(toy.getChanceOfFallingOut()));
            }};
            dataService.update(data);
            List<String[]> dataToys = dataService.getDataToys();
            fileService.writeToFile(fileDataToys, dataToys, header);
            System.out.println("\nThe operation was successfully completed.");
        } else System.out.println("\nA product with this ID does not exist.");
    }

    /**
     * @apiNote The method of obtaining an ordinal toy from the list of prize toys.
     * Writes information about the received prize instance of the Toy class to a file.
     */
    private void getPrizeToy() {
        if (listPrizeToys.isEmpty() && !dataService.getToys().isEmpty()) setListPrizeToys();
        if (!listPrizeToys.isEmpty()) {
            Toy prizeToy = listPrizeToys.pop();
            int newCountToy = dataService.getToy(prizeToy.getId()).getCount() - 1;
            if (newCountToy == 0) {
                dataService.delete(prizeToy.getId());
            } else {
                Map<String, String> data = new HashMap<>() {{
                    put("id", prizeToy.getId());
                    put("count", String.valueOf(newCountToy));
                }};
                dataService.update(data);
            }

            List<String[]> dataToys = dataService.getDataToys();
            fileService.writeToFile(fileDataToys, dataToys, header);

            List<String[]> dataPrizeToy = new ArrayList<>();
            if (fileService.readFile(fileDataPrizeToys) != null)
                dataPrizeToy.addAll(fileService.readFile(fileDataPrizeToys).subList(1, fileService.readFile(fileDataPrizeToys).size()));
            dataPrizeToy.add(prizeToy.getData());
            fileService.writeToFile(fileDataPrizeToys, dataPrizeToy, header);
            System.out.println("\nPrize toy is a " + prizeToy.getName() + "(ID " + prizeToy.getId() + "), congratulate!");
        } else System.out.println("\nLet's have a list of toys. Enter the data.");
    }

    /**
     * @apiNote The method gets the total number of all toys, sorts them by chance of falling out
     * and creates a prize list of instances of the Toy class.
     */
    private void setListPrizeToys() {
        if (dataService.getToys().size() > 0) {
            ArrayList<Toy> toys = new ArrayList<>();
            for (Map.Entry<String, Toy> entry : dataService.getToys().entrySet()) {
                Toy toy = entry.getValue();
                try {
                    toys.add((Toy) toy.clone());
                } catch (CloneNotSupportedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            toys.sort(Comparator.comparing(Toy::getChanceOfFallingOut));
            while (listPrizeToys.size() < dataService.getCountToys()) {
                double random = new Random().nextDouble();
                Integer prizeToyIndex = setPrizeToy(toys, random);
                if (prizeToyIndex != null) {
                    listPrizeToys.add(toys.get(prizeToyIndex));
                    int newToyCount = toys.get(prizeToyIndex).getCount() - 1;
                    if (newToyCount == 0) toys.remove(toys.get(prizeToyIndex));
                    else toys.get(prizeToyIndex).setCount(newToyCount);
                }
            }
        }
    }

    /**
     * @param toys        list of instances of the Toy class
     * @param prizeChance a randomly generated real value of the chance of falling out
     * @return index of the Toy class instance selected from the list
     * @apiNote Method for selecting an instance of the Toy class from the list,
     * the chance of falling out of which is greater than the specified one
     */
    private Integer setPrizeToy(ArrayList<Toy> toys, double prizeChance) {
        for (int index = 0; index < toys.size(); index++) {
            if ((prizeChance < toys.get(index).getChanceOfFallingOut() / 100) && toys.get(index).getCount() > 0) {
                return index;
            }
        }
        return null;
    }
}
