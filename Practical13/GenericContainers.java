class Box<T> {
    private T item;
    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

class Pair<A, B> {
    private final A first;
    private final B second;
    public Pair(A first, B second) { this.first = first; this.second = second; }
    public A getFirst() { return first; }
    public B getSecond() { return second; }
    @Override public String toString() { return "(" + first + ", " + second + ")"; }
}

public class GenericContainers {
    public static <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) return null;
        return array[0];
    }

    public static void main(String[] args) {
        Box<String> strBox = new Box<>();
        strBox.set("Java 17 Generics");
        System.out.println("String Box Content: " + strBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer Box Content: " + intBox.get());

        Pair<String, Double> productPrice = new Pair<>("Laptop", 74999.50);
        System.out.println("Pair Value: " + productPrice);

        String[] fruits = {"Apple", "Banana", "Cherry"};
        Integer[] numbers = {10, 20, 30};
        System.out.println("First Fruit: " + getFirst(fruits));
        System.out.println("First Number: " + getFirst(numbers));
    }
}