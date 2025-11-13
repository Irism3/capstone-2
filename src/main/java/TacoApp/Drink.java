package TacoApp;

public class Drink extends OrderItem {

    //Size constants
    public static final String sizeSmall = "Small";
    public static final String sizeMedium = "Medium";
    public static final String sizeLarge = "Large";


    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        super("Drink");
        this.size = size;
        this.flavor = flavor;

    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double getPrice() {
        switch (size) {
            case sizeSmall:
                return 2.00;
            case sizeMedium:
                return 2.50;
            case sizeLarge:
                return 3.00;
            default:
                return 0.0;
        }
    }

    @Override
    public String getSummary() {
        return size + " Drink - " + flavor + "\n" + String.format("Price: $%.2f", getPrice());
    }

}
