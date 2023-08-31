package View;

public class ToyStoreView extends BaseView {

    /**
     * @return integer value of the menu item selected by the user
     * @apiNote The method displays a list of the console menu and prompts the user to select a menu item
     */
    public String getMenuItem() {
        String itemMenu;
        try {
            itemMenu = getData("""
                    _________________________________________
                    [1] Display a list of toys
                    [2] Add a new toy
                    [3] Change the chance of a toy falling out
                    [4] Get a prize toy
                    [5] Exit
                    """);
            if (!itemMenu.matches("[1-5]"))
                throw new IllegalArgumentException("Invalid input. Enter the number of the menu item..");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMenuItem();
        }
        return itemMenu;
    }

    /**
     * @return string value of the Toy class instance ID
     * @apiNote The method asks the user for an integer value of the ID of the Toy class instance
     */
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

    /**
     * @return a string value of the name of the Toy class instance
     * @apiNote The method asks the user for a string value of the name of the Toy class instance
     */
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

    /**
     * @return string value of the Toy class instance count
     * @apiNote The method asks the user for an integer value of the count of the Toy class instance
     */
    public String getCount() {
        String count;
        try {
            count = getData("Count: ");
            if (!isNumeric(count) || Integer.parseInt(count) < 1)
                throw new IllegalArgumentException("Only numeric values are allowed. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCount();
        }
        return count;
    }

    /**
     * @return string value of the Toy class instance chance of falling out
     * @apiNote The method asks the user for a number value of the chance of falling out of the Toy class instance
     */
    public String getChanceOfFallingOut() {
        String chanceOfFallingOut;
        try {
            chanceOfFallingOut = getData("Chance of falling out: ");
            double chanceOfFallingOutToDouble = Double.parseDouble(chanceOfFallingOut);
            if (chanceOfFallingOutToDouble < 0 || chanceOfFallingOutToDouble >= 100)
                throw new IllegalArgumentException("The chance of a prize cannot be less than zero or more than one hundred percent.");
        } catch (NumberFormatException e) {
            System.out.println("Unknown data format. The chance can only be an integer or a real number. Please try again.");
            return getChanceOfFallingOut();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
