import java.util.HashMap;

public class Warehouse {

    private HashMap<String, Integer> stock = new HashMap<>();

    public Warehouse() {
        stock.put("Laptop", 5);
        stock.put("Mouse", 10);
        stock.put("Keyboard", 3);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero");
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {
            throw new OutOfStockException(
                "Not enough stock for " + item,
                qty - available
            );
        }

        stock.put(item, available - qty);

        System.out.println(qty + " " + item + " issued successfully.");
    }
}