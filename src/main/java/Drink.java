import java.util.ArrayList;
import java.util.List;

public class Drink extends OrderItem {

    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        super("Drink");
        this.size = size;
        this.flavor = flavor;

    }


    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small":
                return 2.00;
            case "medium":
                return 2.50;
            case "large":
                return 3.00;
            default:
                return 0.0;

        }
    }

    @Override
    public String getSummary() {
        return size + " Drink - " + flavor + "\n" + String.format("Price: $%.2f", getPrice());
    }

}
