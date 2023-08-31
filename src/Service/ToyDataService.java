package Service;

import Model.Toy;
import java.util.*;

public class ToyDataService implements DataService {
    private Map<String, Toy> toys = new HashMap<>();

    /**
     * @param dict the object of comparison with the expected keys: identifier (String),
     *             name (String), quantity (Integer), chanceOfFallingOut (Double)
     * @apiNote The method creates an instance of the Toy class.
     */
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

    /**
     * @param dict the object of comparison with the expected keys: identifier (String),
     *             name (String), quantity (Integer), chanceOfFallingOut (Double)
     * @apiNote The method updates an instance of the Toy class.
     */
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

    /**
     * @param id string value of the Toy class instance id
     * @apiNote The method removes an instance of the Toy class from the list of toys.
     */
    @Override
    public void delete(String id) {
        this.toys.remove(id);
    }

    /**
     * @param id string value of the Toy class instance id
     * @return the Toy class instance
     */
    public Toy getToy(String id) {
        return toys.get(id);
    }

    /**
     * @return a dictionary object where keys are ids and values are instances of the Toy class
     */
    public Map<String, Toy> getToys() {
        return toys;
    }

    /**
     * @return a list of string values of data instances of the Toy class to be written to a file.
     */
    public List<String[]> getDataToys() {
        List<String[]> dataToys = new ArrayList<>();
        for (Map.Entry<String, Toy> entry : this.toys.entrySet()) {
            Toy toy = entry.getValue();
            dataToys.add(toy.getData());
        }
        return dataToys;
    }

    /**
     * @return the total number of all toys in the list.
     */
    public Integer getCountToys() {
        Integer countToys = 0;
        for (Map.Entry<String, Toy> entry : this.toys.entrySet()) {
            Toy toy = entry.getValue();
            countToys += toy.getCount();
        }
        return countToys;
    }

    /**
     * @apiNote A method for displaying all the toys in the list to the console.
     */
    public void toysToString() {
        for (Map.Entry<String, Toy> entry : this.toys.entrySet()) {
            Toy toy = entry.getValue();
            System.out.println(toy.toString());
        }
    }
}
