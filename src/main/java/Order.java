import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    String id;
    LocalDateTime dateTime;
    double subTotal;
    double tax;
    double total;

    public Order(String id, LocalDateTime dateTime, double subTotal, double tax, double total) {
        this.id = id;
        this.dateTime = dateTime;
        this.subTotal = subTotal;
        this.tax = tax;
        this.total = total;
    }
}
