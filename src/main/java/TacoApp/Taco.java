package TacoApp;

import java.util.ArrayList;
import java.util.List;

public class Taco extends OrderItem {

    public static final String sizeSingle = "Single";
    public static final String sizeThreeTaco = "3-TacoApp.Taco plate";
    public static final String sizeBurrito = "Burrito";

    private String shell;
    private String size; //single taco , 3-taco plate , burrito
    private boolean isDeepFried;

    private List<String> meats = new ArrayList<>();
    private List<String> cheeses = new ArrayList<>();
    private List<String> toppings = new ArrayList<>();
    private List<String> sauces = new ArrayList<>();


    //Constructor
    public Taco(String shell, String size, boolean isDeepFried) {
        super(size + "taco");
        this.shell = shell;
        this.size = size;
        this.isDeepFried = isDeepFried;

    }
    // Getters
    public String getShell() {
        return shell;
    }

    public String getSize() {
        return size;
    }

    public boolean isDeepFried() {
        return isDeepFried;
    }

    public List<String> getMeats() {
        return new ArrayList<>(meats);
    }

    public List<String> getCheeses() {
        return new ArrayList<>(cheeses);
    }

    public List<String> getToppings() {
        return new ArrayList<>(toppings);
    }

    public List<String> getSauces() {
        return new ArrayList<>(sauces);
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
        switch (size) {
            case sizeSingle:
                return 3.50;
            case sizeThreeTaco:
                return 9.00;
            case sizeBurrito:
                return 8.50;
            default:
                return 0.0;
        }
    }

    private double meatPrice() {
        switch (size) {
            case sizeSingle:
                return 1.00;
            case sizeThreeTaco:
                return 2.00;
            case sizeBurrito:
                return 3.00;
            default:
                return 0.0;
        }
    }

    private double extraMeatPrice() {
        switch (size) {
            case sizeSingle:
                return 0.50;
            case sizeThreeTaco:
                return 1.00;
            case sizeBurrito:
                return 1.50;
            default:
                return 0.0;
        }
    }

    private double cheesePrice() {
        switch (size) {
            case sizeSingle:
                return 0.75;
            case sizeThreeTaco:
                return 1.50;
            case sizeBurrito:
                return 2.25;
            default:
                return 0.0;

        }
    }

    private double extraCheesePrice() {
        switch (size) {
            case sizeSingle:
                return 0.30;
            case sizeThreeTaco:
                return 0.60;
            case sizeBurrito:
                return 0.90;
            default:
                return 0.0;
        }
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
