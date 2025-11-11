public class Drink extends OrderItem{

    private String size;
    private String flavor;

    public Drink(String displayName, String size, String flavor) {
        super(displayName);
        this.size = size;
        this.flavor = flavor;
    }

}
