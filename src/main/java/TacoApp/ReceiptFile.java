package TacoApp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFile {


    public static void saveReceipt(Order order) {

        String folder = "src/main/resources/receipts.csv";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String fileName = folder + timestamp + ".txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=====Yummy-Taco Receipt=====");
            writer.write("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a")) + "\n\n");

            for (OrderItem item : order.getItems()) {
                writer.write(item.getDisplayName() + "- $" + String.format("%.2f", item.getPrice()) + "\n");

                if (item instanceof Taco taco) {
                    writer.write("  Shell:  " + taco.getShell() + "\n");
                    writer.write(" Size:  " + taco.getSize() + "\n");
                    writer.write(" Deep Fried:  " + (taco.isDeepFried() ? "Yes" : "No") + "\n");
                    writer.write("  Meats:  " + taco.getMeats() + "\n");
                    writer.write("  Cheese:  " + taco.getCheeses() + "\n");
                    writer.write("  Toppings:  " + taco.getToppings() + "\n");
                    writer.write("  Sauces:  " + taco.getSauces() + "\n\n");
                }
            }

            // Totals
            writer.write("------------------------\n");
            writer.write("Total: $" + String.format("%.2f", order.getTotalPrice()) + "\n");
            writer.write("==========================\n");
            writer.write("Thank you for visiting Yummy-Taco! \n");

            System.out.println("Receipt saved:  " + fileName);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e);
        }
    }


}
