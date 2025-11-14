package pluralsight;

import java.util.*;


public class TacoApp {


    private Scanner scanner = new Scanner(System.in);
    private Order order = new Order();


    // Main application loop - displays main menu and handles user choices
    public void run() {


        while (true) {
            System.out.println("====Yummy-Taco=====");
            System.out.println("1) New Order");
            System.out.println("0) Exist ");
            System.out.println("Choose: ");
            String t = scanner.nextLine().trim();

            if (t.equals("1")) {
                startNewOrder(scanner);
            } else if (t.equals("0")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice. \n");
            }
        }

    }

    // Starts a new order process and handles the order screen menu
    private void startNewOrder(Scanner scanner) {
        Order order = new Order();

        while (true) {
            System.out.println("====== Order Screen ======");
            displayItemsNewestFirst(order);
            System.out.printf("Subtotal: $%.2f%n", order.getTotalPrice());
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
                    // If checkout is successful, return to main menu
                    if (checkout(scanner, order)) {
                        return;
                    }
                    break;
                case "0":
                    // Confirm cancellation before returning to main menu
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

    // Returns a reversed list of order items (newest first)
    private List<OrderItem> getItemsReversed(Order order) {
        List<OrderItem> items = new ArrayList<>(order.getItems());
        Collections.reverse(items);
        return items;
    }

    //Displays all items in the order with newest items shown first
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

    // Guides the user through building a custom taco and adds it to the order
    private void addTaco(Scanner scanner, Order order) {
        System.out.println("\n===== Build Your Taco =====");


        // Select shell type
        System.out.println("\nSelect Shell: ");
        System.out.println("1) Flour ");
        System.out.println("2) Corn");
        System.out.println("3) Hard Shell");
        System.out.println("4) Bowl");
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
            case "4":
                shell = "Bowl";
                break;
            default:
                System.out.println("Invalid shell choice.");
                return;
        }


        //Select size
        System.out.println("\nSelect Size: ");
        System.out.println("1) Single Taco  ");
        System.out.println("2) 3-Taco Plate ");
        System.out.println("3) Burrito ");
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


        // Ask if user wants it deep fried
        boolean deepFried = askYesNo(scanner, "Deep fried? (y/n): ");

        // Create the taco with selected shell, size, and deep fried option
        Taco taco = new Taco(shell, size, deepFried);

        // Select meat type
        System.out.println("\nSelect Meat: ");
        System.out.println("1) Carne Asada");
        System.out.println("2) al Pastor");
        System.out.println("3) Carnitas");
        System.out.println("4) Pollo");
        System.out.println("5) Chorizo");
        System.out.println("6) Pescado");
        String meatChoice = scanner.nextLine().trim();

        String meat;
        switch (meatChoice) {
            case "1":
                meat = "Carne Asada";
                break;
            case "2":
                meat = "Al Pastor";
                break;
            case "3":
                meat = "Carnitas";
                break;
            case "4":
                meat = "Pollo";
                break;
            case "5":
                meat = "Chorizo";
                break;
            case "6":
                meat = "Pescado";
                break;
            default:
                System.out.println("Invalid meat choice.");
                return;
        }

        // Ask if user wants extra meat and add to taco
        boolean extraMeat = askYesNo(scanner, "Extra meat? (y/n): ");
        taco.addMeat(meat, extraMeat);

        // Select cheese type
        System.out.println("\nSelect Cheese: ");
        System.out.println("1) Queso Fresco ");
        System.out.println("2) Oaxaca ");
        System.out.println("3) Cotija ");
        System.out.println("4) Cheddar ");
        String cheeseChoice = scanner.nextLine().trim();

        String cheese;
        switch (cheeseChoice) {
            case "1":
                cheese = "Queso Fresco";
                break;
            case "2":
                cheese = "Oaxaca";
                break;
            case "3":
                cheese = "Cotija";
                break;
            case "4":
                cheese = "Cheddar";
                break;
            default:
                System.out.println("Invalid cheese choice.");
                return;

        }

        // Ask if user wants extra cheese and add to taco
        boolean extraCheese = askYesNo(scanner, "Extra cheese? (y/n): ");
        taco.addCheese(cheese, extraCheese);


        // Add toppings - user can add multiple toppings
        System.out.println("\n--- Add Toppings ---");
        System.out.println("Type 'done' when you're finished.\n");

        System.out.println("Available Toppings:");
        System.out.println("Lettuce, Cilantro, Onions, Tomatoes, Jalapeños, Radishes, Pico De Gallo, Guacamole, Corn");

        String topping;

        while (true) {
            System.out.println("Enter a topping (or 'done'): ");
            topping = scanner.nextLine().trim();

            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            if (!topping.isEmpty()) {
                taco.addToppings(topping);
                System.out.println(topping + "  added! \n");
            }
        }

        // Ask if user wants to add sauces
        System.out.println("Would you like to add a sauce? (yes/no): ");
        String wantsSauce = scanner.nextLine().trim();

        if (wantsSauce.equalsIgnoreCase("yes")) {

            // Add sauces - user can add multiple sauces
            System.out.println("\n--- Add Sauces ---");
            System.out.println("Type 'done' when you're finished.\n");

            System.out.println("Available Sauces:");
            System.out.println(" Salsa Verde, Salsa Roja, Chipotle, Habanero, Mild, Extra Hot\n");

            String sauce;

            while (true) {
                System.out.println("Enter sauce choice (or 'done') ");
                sauce = scanner.nextLine().trim();

                if (sauce.equalsIgnoreCase("done")) {
                    break;
                }
                if (!sauce.isEmpty()) {
                    taco.addSauce(sauce);
                    System.out.println(sauce + " added!\n");
                }
            }
        }

        // Add sides - user can add multiple sides
        System.out.println("\n--- Add Sides---");
        System.out.println("Type 'done' when you're finished.\n");

        System.out.println("Available Slides:");
        System.out.println(" Lime, Salt, Crema");

        String sides;

        while (true) {
            System.out.println("Enter sides choice (or 'done') ");
            sides = scanner.nextLine().trim();
            if (sides.equalsIgnoreCase("done")) {
                break;
            }
            if (!sides.isEmpty()) {
                taco.addSides(sides);
                System.out.println(sides + "added!\n");
            }

        }

        // Add the completed taco to the order
        order.addItem(taco);
        System.out.println("\nTaco added to order!");


    }

    // Guides the user through adding a drink to the order
    private void addDrink(Scanner scanner, Order order) {
        System.out.println("\n===== Add Drink =====");

        // Select drink size
        System.out.println("\nSelect Size:");
        System.out.println("1) Small ");
        System.out.println("2) Medium ");
        System.out.println("3) Large ");
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

        // Select drink flavor
        System.out.println("\nSelect Flavor:");
        System.out.println("1) Horchata ");
        System.out.println("2) Jamaica ");
        System.out.println("3) Coke ");
        System.out.println("4) Sprite ");
        System.out.println("5) Water ");
        System.out.println("Flavor: ");
        String flavorChoice = scanner.nextLine().trim();

        String flavor;
        switch (flavorChoice) {
            case "1":
                flavor = "Horchata";
                break;
            case "2":
                flavor = "Jamaica";
                break;
            case "3":
                flavor = "Coke";
                break;
            case "4":
                flavor = "Sprite";
                break;
            case "5":
                flavor = "Water";
                break;
            default:
                System.out.println("Invalid flavor choice");
                return;
        }


        // Create drink and add to order
        Drink drink = new Drink(size, flavor);
        order.addItem(drink);
        System.out.println("Drink added to order!");
    }

    // Guides the user through adding chips and salsa to the order
    private void addChips(Scanner scanner, Order order) {
        System.out.println("\n======= Add Chips & Salsa ======");

        // Select salsa type
        System.out.println("\nChoose your salsa:");
        System.out.println("1) Salsa Verde");
        System.out.println("2) Salsa Roja");
        System.out.println("3) Chipotle");
        System.out.println("4) Habanero");
        System.out.println("5) Mild");
        System.out.println("6) Extra Hot");
        System.out.println("Enter choice:");
        String salsaChoice = scanner.nextLine().trim();

        String salsa;
        switch (salsaChoice) {
            case "1":
                salsa = "Salsa Verde";
                break;
            case "2":
                salsa = "Salsa Roja";
                break;
            case "3":
                salsa = "Chipotle";
                break;
            case "4":
                salsa = "Habanero";
                break;
            case "5":
                salsa = "Mild";
                break;
            case "6":
                salsa = "Extra Hot";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        ChipsAndSalsa chips = new ChipsAndSalsa(salsa);
        order.addItem(chips);
        System.out.println("\nAdded: " + chips.getSummary());


    }

    // Handles the checkout process - displays order summary and confirms payment
    // Returns true if checkout is successful, false otherwise
    private boolean checkout(Scanner scanner, Order order) {

        if (order.getItems().isEmpty()) {
            System.out.println("Cannot checkout with an empty order.");
            return false;
        }
        // Display final order summary
        System.out.println("\n===== Order Summary =====");
        order.displayOrder();

        // Confirm order with user
        if (askYesNo(scanner, "Confirm order? (y/n): ")) {
            System.out.println("\nProcessing payment....");
            System.out.println("Payment successful!");

            // Save receipt to file
            ReceiptFile.saveReceipt(order);

            System.out.println("\nThank you for your order!");
            return true;
        }
        return false;
    }

    // Helper method to ask yes/no questions
    private boolean askYesNo(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}

