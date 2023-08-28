package Model;


public class Toy extends Product {
    private Double chanceOfFallingOut;

    public Toy(String id, String name, Integer count, Double chanceOfFallingOut) {
        super(id, name, count);
        this.chanceOfFallingOut = chanceOfFallingOut;
    }

    public Double getChanceOfFallingOut() {
        return chanceOfFallingOut;
    }

    public void setChanceOfFallingOut(Double chanceOfFallingOut) {
        this.chanceOfFallingOut = chanceOfFallingOut;
    }

    public String[] getData() {
        return new String[]{
                this.getId(),
                this.getName(),
                String.valueOf(this.getCount()),
                String.valueOf(this.getChanceOfFallingOut())
        };
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() +
                "\nName: " + this.getName() +
                "\nCount: " + this.getCount() +
                "\nChance of falling out: " + this.getChanceOfFallingOut() + "%\n";
    }
}
