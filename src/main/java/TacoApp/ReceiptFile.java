package TacoApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReceiptFile {


    public static void saveReceipt(Order order) {

        // Folder where all receipt files will be stored
        String folderPath = "src/main/resources/receipts/";

        // Create folder if it does not exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Create timestamp for the receipt filename
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));


        // Full file path for the new receipt
        String filename = folderPath + timestamp + ".txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // Header to the receipt
            writer.write("=====Yummy-Taco Receipt=====\n");

            // Write readable date and time for the customer
            writer.write("Date: " + now.format(DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a")) + "\n\n");


            // Loop through every item in the order and print details
            for (OrderItem item : order.getItems()) {


                // Write item name and price
                writer.write(item.getDisplayName() + "- $" + String.format("%.2f", item.getPrice()) + "\n");

                // Extra details only for Taco items
                if (item instanceof Taco) {
                    Taco taco = (Taco) item;
                    writer.write("  Shell:  " + taco.getShell() + "\n");
                    writer.write("  Size:  " + taco.getSize() + "\n");
                    writer.write("  Deep Fried:  " + (taco.isDeepFried() ? "Yes" : "No") + "\n");
                    writer.write("  Meats:  " + taco.getMeats() + "\n");
                    writer.write("  Cheese:  " + taco.getCheeses() + "\n");
                    writer.write("  Toppings:  " + taco.getToppings() + "\n");
                    writer.write("  Sauces:  " + taco.getSauces() + "\n");
                    writer.write("  Slices:  " + taco.getSides() + "\n\n");
                }
            }

            // Writes receipt with total
            writer.write("==========================\n");
            writer.write("Total: $" + String.format("%.2f", order.getTotalPrice()) + "\n");
            writer.write("==========================\n");
            writer.write("Thank you for visiting Yummy-Taco! \n");

            System.out.println("Receipt saved  " + filename);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e);
        }
    }


}
