class UnsafeCounter {
    private int count = 0;
    public void increment() { count++; }
    public int getCount() { return count; }
}

class SafeCounter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public synchronized int getCount() { return count; }
}

public class CounterRace {
    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 5;
        final int INCREMENTS = 10000;
        final int EXPECTED = THREADS * INCREMENTS;

        // 1. Unsynchronized Execution
        UnsafeCounter unsafe = new UnsafeCounter();
        Thread[] uThreads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            uThreads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) unsafe.increment();
            });
            uThreads[i].start();
        }
        for (Thread t : uThreads) t.join();
        System.out.println("Unsynchronized Counter: " + unsafe.getCount() + " (Expected: " + EXPECTED + ") -> MISMATCH!");

        // 2. Synchronized Execution
        SafeCounter safe = new SafeCounter();
        Thread[] sThreads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            sThreads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) safe.increment();
            });
            sThreads[i].start();
        }
        for (Thread t : sThreads) t.join();
        System.out.println("Synchronized Counter  : " + safe.getCount() + " (Expected: " + EXPECTED + ") -> EXACT MATCH!");
    }
}