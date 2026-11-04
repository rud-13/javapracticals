public class DriverWarehouse {
    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        String[] items = {"Laptop", "Mouse", "Keyboard", "Laptop"};
        int[] quantities = {2, 15, -1, 5};

        for (int i = 0; i < items.length; i++) {

            try {
                warehouse.issue(items[i], quantities[i]);

            } catch (OutOfStockException e) {
                System.out.println(e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());

            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("All requests processed.");
    }
}