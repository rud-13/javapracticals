public class BoundedStats {
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) return 0.0;
        double sum = 0.0;
        for (N val : values) {
            sum += val.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] intArr = { 10, 20, 30, 40 };
        Double[] doubleArr = { 2.5, 3.5, 4.0 };
        Long[] longArr = { 1000L, 2000L, 3000L };

        System.out.println("Integer Array Average: " + average(intArr));
        System.out.println("Double Array Average : " + average(doubleArr));
        System.out.println("Long Array Average   : " + average(longArr));
        // Note: average(new String[]{"a", "b"}) fails at compile time because String does not extend Number!
    }
}