public class DriverResource {
    public static void main(String[] args) {

        try (DemoResource resource = new DemoResource()) {

            resource.use();

        } catch (Exception e) {

            System.out.println("Caught: " + e.getMessage());

            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }
}