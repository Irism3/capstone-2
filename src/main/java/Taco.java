import java.util.ArrayList;
import java.util.List;

public class Taco extends OrderItem {

    private String shell;
    private String size; //single taco , 3-taco plate , burrito
    private boolean isDeepFried;

    private List<String> meats = new ArrayList<>();
    private List<String> cheeses = new ArrayList<>();
    private List<String> toppings = new ArrayList<>();
    private List<String> sauces = new ArrayList<>();


    //Constructor
    public Taco(String shell, String size, boolean isDeepFried, double getPrice) {
        super(size + "taco");
        this.shell = shell;
        this.size = size;
        this.isDeepFried = isDeepFried;

    }

    public void addMeat(String meat, boolean extra) {
        meats.add(meat);
        if (!extra) lineTotal += meatPrice();
        else lineTotal += extraMeatPrice();
    }

    public void addCheese(String cheese, boolean extra) {
        cheeses.add(cheese);
        if (!extra) lineTotal += cheesePrice();
        else lineTotal += extraCheesePrice();
    }

    public void addToppings(String topping) {
        toppings.add(topping);
    }

    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    private double lineTotal = 0;

    private double basePrice() {
        if (size.equalsIgnoreCase("Single")) {
            return 3.50;
        }
        if (size.equalsIgnoreCase("3-Taco") || size.equalsIgnoreCase("3-Taco Plate")) {
            return 9.00;
        }
        if (size.equalsIgnoreCase("Burrito")) {
            return 8.50;
        }
        return 0;
    }

    private double meatPrice() {
        if (size.equalsIgnoreCase("Single")) {
            return 3.00;
        }
        if (size.equalsIgnoreCase("3-Taco")) {
            return 2.00;
        }
        return 3.00; //Burrito
    }

    private double extraMeatPrice() {
        if (size.equalsIgnoreCase("Single")) {
            return 0.50;
        }
        if (size.equalsIgnoreCase("3-Taco")) {
            return 1.00;
        }
        return 1.50; //Burrito
    }

    private double cheesePrice() {
        if (size.equalsIgnoreCase("Single")) {
            return 0.75;
        }
        if (size.equalsIgnoreCase("3-Taco")) {
            return 1.50;
        }
        return 2.25;
    }

    private double extraCheesePrice() {
        if (size.equalsIgnoreCase("Single")) {
            return 0.30;
        }
        if (size.equalsIgnoreCase("3-Taco")) {
            return 0.60;
        }
        return 0.90;
    }

    @Override
    public double getPrice() {
        return basePrice() + lineTotal;   //lineTotal holds all upcharges
    }

    @Override
    public String getSummary() {
        return getDisplayName() + shell + (isDeepFried ? " , Deep Fried" : "")
                + "\n" + "Meats: " + meats + "\n" + "Cheeses: " + cheeses + "\n"
                + "Toppings: " + toppings + "\n" + "Sauces: " + sauces + "\n"
                + String.format("Price: $%.2f", getPrice());
    }


}
