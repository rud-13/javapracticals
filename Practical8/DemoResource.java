public class DemoResource implements AutoCloseable {

    public DemoResource() {
        System.out.println("Resource opened.");
    }

    public void use() throws Exception {
        System.out.println("Using resource.");
        throw new Exception("Original error occurred.");
    }

    public void close() throws Exception {
        System.out.println("Resource closed.");
        throw new Exception("Error while closing resource.");
    }
}