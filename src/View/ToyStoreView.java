package View;

public class ToyStoreView extends BaseView {

    public String getMenuItem() {
        String itemMenu;
        try {
            itemMenu = getData("""
                [1] Display a list of toys
                [2] Add a new toy
                [3] Change the chance of a toy falling out
                [4] Get a prize toy
                [5] Exit
                """);
            if (!itemMenu.matches("[1-5]+"))
                throw new IllegalArgumentException("Invalid input. Enter the number of the menu item..");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMenuItem();
        }
        return itemMenu;
    }

    public String getId() {
        String id;
        try {
            id = getData("Enter toy ID: ");
            if (!isNumeric(id))
                throw new IllegalArgumentException("Only numeric values are allowed. Please try again.");
            if (id.length() > 10)
                throw new IllegalArgumentException("The ID cannot be more than 10 characters. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getId();
        }
        return id;
    }

    public String getName() {
        String name;
        try {
            name = getData("Toy name: ");
            if (!isName(name))
                throw new IllegalArgumentException("The name can consist only of letters, a space mark and a dash. Please try again.");
            if (name.length() > 50)
                throw new IllegalArgumentException("The ID cannot be more than 50 characters. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getName();
        }
        return name;
    }

    public String getCount() {
        String count;
        try {
            count = getData("Count: ");
            if (!isNumeric(count))
                throw new IllegalArgumentException("Only numeric values are allowed. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCount();
        }
        return count;
    }

    public String getChanceOfFallingOut() {
        String chanceOfFallingOut;
        try {
            chanceOfFallingOut = getData("Chance of falling out: ");
            Double chanceOfFallingOutToDouble = Double.parseDouble(chanceOfFallingOut);
        } catch (NumberFormatException e) {
            System.out.println("Unknown data format. The chance can only be an integer or a real number. Please try again.");
            return getChanceOfFallingOut();
        }
        return chanceOfFallingOut;
    }

    private boolean isNumeric(String value) {
        return value.matches("[0-9]+");
    }

    private boolean isName(String value) {
        return value.matches("[a-zA-Zа-яА-Я -]+");
    }

}
