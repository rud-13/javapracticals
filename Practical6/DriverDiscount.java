import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DriverDiscount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> prices = Arrays.asList(
            1000.0, 2000.0, 3000.0
        );

        DiscountEngine engine = new DiscountEngine(prices);

        DiscountRule tenPercent = price -> price * 0.90;
        DiscountRule twentyPercent = price -> price * 0.80;
        DiscountRule noDiscount = price -> price;

        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. No Discount");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        DiscountRule selectedRule;

        switch (choice) {
            case 1:
                selectedRule = tenPercent;
                break;
            case 2:
                selectedRule = twentyPercent;
                break;
            case 3:
                selectedRule = noDiscount;
                break;
            default:
                System.out.println("Invalid choice");
                sc.close();
                return;
        }

        engine.applyDiscount(selectedRule);
        sc.close();
    }
}