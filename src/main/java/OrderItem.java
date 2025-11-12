public abstract class OrderItem {

    private String displayName;

    public OrderItem(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract double getPrice();

    public abstract String getSummary();
}

