import java.util.*;

record Product(String id, String name, double price) {}

class ProductCatalogService {
    private final List<Product> catalog = new ArrayList<>();

    public void addProduct(Product p) {
        if (p.price() <= 0) throw new IllegalArgumentException("Price must be > 0");
        catalog.add(p);
    }

    public int getCount() { return catalog.size(); }
    public Product getById(String id) {
        return catalog.stream().filter(p -> p.id().equals(id)).findFirst().orElse(null);
    }
}

public class LayeredTesting {
    // Simulated test runner demonstrating JUnit test assertions
    public static void main(String[] args) {
        System.out.println("=== Running JUnit Unit Tests for ProductCatalogService ===");

        ProductCatalogService service = new ProductCatalogService();

        // Test 1: Addition increases count
        service.addProduct(new Product("P101", "Keyboard", 1500));
        assert service.getCount() == 1 : "Test 1 Failed: Count should be 1";
        System.out.println(" [PASS] testAddProductIncreasesCount");

        // Test 2: Retrieval by ID
        Product p = service.getById("P101");
        assert p != null && p.name().equals("Keyboard") : "Test 2 Failed";
        System.out.println(" [PASS] testGetProductById");

        // Test 3: Invalid price throws IllegalArgumentException
        boolean threw = false;
        try {
            service.addProduct(new Product("P102", "Free Item", -10));
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        assert threw : "Test 3 Failed: Exception expected for negative price";
        System.out.println(" [PASS] testInvalidPriceThrowsException");

        System.out.println("All automated test cases passed successfully!");
    }
}