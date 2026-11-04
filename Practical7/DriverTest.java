public class DriverTest {
    public static void main(String[] args) {
        TestCases tests = new TestCases();

        int total = TestRunner.runTests(tests);

        System.out.println("Total tests executed: " + total);
    }
}