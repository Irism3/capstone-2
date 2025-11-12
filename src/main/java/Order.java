import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> items;
    private double totalPrice;

    public Order() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // add items
    public void addItem(OrderItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    // removes items
    public void removeItem(OrderItem item) {
        if (items.remove(item)) {
            totalPrice -= item.getPrice();
        }
    }

    //get total price
    public double getTotalPrice() {
        return totalPrice;
    }

    //show summary
    public void displayOrder() {
        System.out.println("Order Summary");
        for (OrderItem item : items) {
            System.out.println(item.getSummary());
        }
        System.out.printf("Total: $%.2f%n", totalPrice);
        System.out.println("--------------------------");
    }

    // getter for items
    public ArrayList<OrderItem> getItems() {
        return items;
    }

}

