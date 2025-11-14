package pluralsight;

import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> items;
    private double totalPrice;

    public Order() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // Adds an item to the order and updates the total price
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

    // Returns the total price of all items in the order
    public double getTotalPrice() {
        return totalPrice;
    }

    // Shows each item and the total price
    public void displayOrder() {
        System.out.println("Order Summary");
        for (OrderItem item : items) {
            System.out.println(item.getSummary());
        }
        System.out.printf("Total: $%.2f%n", totalPrice);
        System.out.println("--------------------------");
    }

    // Returns the list of all items in the order
    public ArrayList<OrderItem> getItems() {
        return items;
    }

}


