package Service;

import Model.Toy;

import java.util.*;

public class ToyDataService implements DataService {
    private Map<String, Toy> toys = new HashMap<>();

    @Override
    public void create(Map<String, String> dict) {
        String id = dict.get("id");
        if (toys.containsKey(id)) throw new IllegalArgumentException("The product with this ID already exists.");
        String name = dict.get("name");
        Integer count = Integer.parseInt(dict.get("count"));
        Double chanceOfFallingOut = Double.parseDouble(dict.get("chanceOfFallingOut"));
        Toy toy = new Toy(id, name, count, chanceOfFallingOut);
        this.toys.put(id, toy);
    }

    @Override
    public void update(Map<String, String> dict) {
        String id = dict.get("id");
        Toy toy = this.toys.get(id);
        String name = dict.get("name");
        if (name != null) toy.setName(name);
        String count = dict.get("count");
        if (count != null) toy.setCount(Integer.parseInt(count));
        String chanceOfFallingOut = dict.get("chanceOfFallingOut");
        if (chanceOfFallingOut != null) toy.setChanceOfFallingOut(Double.parseDouble(chanceOfFallingOut));
        this.toys.put(id, toy);
    }

    @Override
    public void delete(String id) {
        this.toys.remove(id);
    }

    public List<String[]> getDataToys() {
        List<String[]> dataToys = new ArrayList<>();
        for (Map.Entry<String, Toy> entry : this.toys.entrySet()) {
            Toy toy = entry.getValue();
            dataToys.add(toy.getData());
        }
        return dataToys;
    }

    public void toysToString() {
        for (Map.Entry<String, Toy> entry : this.toys.entrySet()) {
            Toy toy = entry.getValue();
            System.out.println(toy.toString());
        }
    }
}
