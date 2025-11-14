package TacoApp;

public abstract class OrderItem {

    private String displayName;

    // Constructor
    public OrderItem(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Abstract method - subclasses must define how to calculate the price
    public abstract double getPrice();

    // Abstract method - subclasses must define how to format the item summary
    public abstract String getSummary();
}

