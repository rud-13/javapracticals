import java.util.EmptyStackException;

class ArrayStack<T> {
    private Object[] elements;
    private int size = 0;

    public ArrayStack(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    public void push(T item) {
        if (size == elements.length) {
            Object[] larger = new Object[elements.length * 2];
            System.arraycopy(elements, 0, larger, 0, elements.length);
            elements = larger;
        }
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) throw new EmptyStackException();
        T item = (T) elements[--size];
        elements[size] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) throw new EmptyStackException();
        return (T) elements[size - 1];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}

public class GenericStack {
    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>(2);
        stack.push("Alpha");
        stack.push("Beta");
        stack.push("Gamma"); // Expands automatically

        System.out.println("Top element: " + stack.peek());
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}