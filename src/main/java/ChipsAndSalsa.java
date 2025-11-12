public class ChipsAndSalsa extends OrderItem {

    private String salsaType;

    public ChipsAndSalsa(String salsaType) {
        super("Chips And Salsa");
        this.salsaType = salsaType;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String getSummary() {
        return "Chips & Salsa (" + salsaType + ")\n" + String.format("Price: ");
    }
}
