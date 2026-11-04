import java.util.List;

public class DiscountEngine {
    private List<Double> prices;

    public DiscountEngine(List<Double> prices) {
        this.prices = prices;
    }

    public void applyDiscount(DiscountRule rule) {
        double total = 0;

        for (double price : prices) {
            double finalPrice = rule.apply(price);

            System.out.println("Original Price: " + price);
            System.out.println("Final Price: " + finalPrice);

            total += finalPrice;
        }

        System.out.println("Total Amount: " + total);
    }
}