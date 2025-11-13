package TacoApp;

import java.util.*;


public class TacoApp {

    public static void main(String[] args) {
        TacoApp app = new TacoApp();
        try (Scanner myScanner = new Scanner(System.in)) {
            app.run(myScanner);
        }
    }

    private void run(Scanner scanner) {
        while (true) {
            System.out.println("====Yummy-Taco=====");
            System.out.println("1) New Order");
            System.out.println("0) Exist ");
            System.out.println("Choose: ");
            String t = scanner.nextLine().trim();

            if (t.equals("1")) {
                startNewOrder(scanner);
            }else if (t.equals("0")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice. \n");
            }
        }

    }
    private void startNewOrder(Scanner scanner) {
        Order order = new Order();

        while (true) {
            System.out.println("====== Order Screen ======");
            displayItemsNewestFirst(order);
            System.out.printf("Subtotal: $%.2f%n" , order.getTotalPrice());
            System.out.println();
            System.out.println("1) Add Taco");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips & Salsa");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTaco(scanner, order);
                    break;
                case "2":
                    addDrink(scanner, order);
                    break;
                case "3":
                    addChips(scanner, order);
                    break;
                case "4":
                    if (checkout(scanner, order)) {
                        return;
                    }
                    break;
                case "0":
                    if (askYesNo(scanner, "Cancel this order? (y/n): ")) {
                        System.out.println("Order canceled.\n");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. \n");
            }
        }
    }
    private List<OrderItem> getItemsReversed (Order order) {
        List<OrderItem> items = new ArrayList<>(order.getItems());
        Collections.reverse(items);
        return items;
    }



    private void displayItemsNewestFirst(Order order) {
        List<OrderItem> items = getItemsReversed(order);
        if (items.isEmpty()) {
            System.out.println("No items yet");
            return;
        }
        int itemNum = 1;
        for (OrderItem item : items) {
            System.out.println("-----Item " + (itemNum++) + "----");
            System.out.println(item.getSummary());
            System.out.println();
        }
    }

    private void addTaco(Scanner scanner, Order order) {
        System.out.println("\n===== Build Your Taco =====");

        //Select size
        System.out.println("\nSelect Size: ");
        System.out.println("1) Single Taco  - $3.50");
        System.out.println("2) 3-Taco Plate - $9.00");
        System.out.println("3) Burrito - $8.50");
        System.out.println("Choose: ");
        String sizeChoice = scanner.nextLine().trim();

        String size;
        switch (sizeChoice) {
            case "1":
               size = Taco.sizeSingle;
                break;
            case "2":
                size = Taco.sizeThreeTaco;
               break;
            case "3":
                size = Taco.sizeBurrito;
                break;
            default:
                System.out.println("Invalid size choice. ");
                return;

        }

        // Select shell
        System.out.println("\nSelect Shell: ");
        System.out.println("1) Flour");
        System.out.println("2) Corn");
        System.out.println("3) Hard Shell");
        System.out.println("Choose: ");
        String shellChoice = scanner.nextLine().trim();

        String shell;
        switch (shellChoice) {
            case "1":
                shell = "Flour";
                break;
            case "2":
                shell = "Corn";
                break;
            case "3":
                shell = "Hard Shell";
                break;
            default:
                System.out.println("Invalid shell choice.");
                return;
        }

        boolean deepFried = askYesNo(scanner, "Deep fried? (y/n): ");

        //create a taco
        Taco taco = new Taco(shell, size , deepFried);

        System.out.println("\nAdd Toppings (type 'done' when finished): ");
        System.out.println("Options: Lettuce, Tomato, Onions, Cilantro, Guacamole, Sour Cream");
        while (true) {
            System.out.println("Add topping: ");
            String topping = scanner.nextLine().trim();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            if (!topping.isEmpty()) {
                taco.addToppings(topping);
            }
        }

        System.out.println("\nAdd Sauces (type 'done' when finished):");
        System.out.println("Options: Mild, Hot, Verde, Chipotle");
        while (true) {
            System.out.println("Add sauce: ");
            String sauce = scanner.nextLine().trim();
            if (sauce.equalsIgnoreCase("done")) {
                break;
            }
            if (!sauce.isEmpty()) {
                taco.addSauce(sauce);
            }
        }

        order.addItem(taco);
        System.out.println("\nTaco added to order!");
    }

    private void addDrink(Scanner scanner, Order order) {
        System.out.println("\n===== Add Drink =====");

        System.out.println("\nSelect Size:");
        System.out.println("1) Small - $2.00");
        System.out.println("2) Medium - $2.50");
        System.out.println("3) Large - $3.00");
        System.out.println("Choose: ");
        String sizeChoice = scanner.nextLine().trim();

        String size;
        switch (sizeChoice) {
            case "1":
                size = Drink.sizeSmall;
                break;
            case "2":
                size = Drink.sizeMedium;
                break;
            case "3":
                size = Drink.sizeLarge;
                break;
            default:
                System.out.println("Invalid size choice");
                return;
        }

        System.out.println("\nSelect Flavor:");
        System.out.println("Options: Coke, Diet Coke, Horchata, Agua De Jamaica, Sprite, Water");
        System.out.println("Flavor: ");
        String flavor = scanner.nextLine().trim();

        if (flavor.isEmpty()) {
            System.out.println("Invalid flavor.");
            return;
        }
        Drink drink = new Drink(size, flavor);
        order.addItem(drink);
        System.out.println("Drink added to order!");
    }

    private void addChips(Scanner scanner, Order order) {
        System.out.println("\n======= Add Chips & Salsa ======");
        System.out.println("\nSelect Salsa:");
        System.out.println("Options: Mild, Medium, Hot, Verde");
        System.out.println("Salsa type: ");
        String salsaType = scanner.nextLine().trim();

        if (salsaType.isEmpty()) {
            System.out.println("Invalid salsa choice.");
            return;
        }
        ChipsAndSalsa chips = new ChipsAndSalsa(salsaType);
        order.addItem(chips);
        System.out.println("Chips & Salsa added to order!");
    }

    private boolean checkout(Scanner scanner, Order order) {
        if (order.getItems().isEmpty()) {
            System.out.println("Cannot checkout with an empty order.");
            return false;
        }
        System.out.println("\n===== Order Summary =====");
        order.displayOrder();

        if (askYesNo(scanner, "Confirm order? (y/n): ")) {
            System.out.println("\nProcessing payment....");
            System.out.println("Payment successful!");

            ReceiptFile.saveReceipt(order);

            System.out.println("\nThank you for your order!");
            return true;
        }
        return false;
    }
    private boolean askYesNo(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}
