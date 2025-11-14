package TacoApp;


// Represents a drink item that can be ordered
// Extends OrderItem to add size and flavor properties
public class Drink extends OrderItem {

    // Size constants - used to ensure consistent size names throughout the app
    public static final String sizeSmall = "Small";
    public static final String sizeMedium = "Medium";
    public static final String sizeLarge = "Large";


    private String size;
    private String flavor;

    // Constructor
    public Drink(String size, String flavor) {
        super(" Drink ");
        this.size = size;
        this.flavor = flavor;

    }

    // Returns the size of the drink
    public String getSize() {
        return size;
    }

    // Returns the flavor of the drink
    public String getFlavor() {
        return flavor;
    }

    // Calculates the price based on the drink size
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

    // Returns a formatted string summary of the drink order
    @Override
    public String getSummary() {
        return String.format("Drink (%s, %s) - $%.2f" ,size,flavor,getPrice());
    }

}
