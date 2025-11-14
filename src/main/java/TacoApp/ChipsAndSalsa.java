package TacoApp;

// Extends OrderItem to add salsa type selection
public class ChipsAndSalsa extends OrderItem {

    private String salsaType;

    // Constructor
    public ChipsAndSalsa(String salsaType) {
        super(" Chips And Salsa ");
        this.salsaType = salsaType;
    }

    // Returns the type of salsa for this order
    public String getSalsaType() {
        return salsaType;
    }

    // Returns the price of chips and salsa (fixed at $1.50)
    @Override
    public double getPrice() {
        return 1.50;
    }

    // Returns a formatted string summary of the chips and salsa order
    // Includes the salsa type and price
    @Override
    public String getSummary() {
        return String.format("Chips & Salsa (%s) - $%.2f", getSalsaType(),getPrice());
    }
}
