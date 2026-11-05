import java.util.concurrent.atomic.AtomicLong;

public class ParallelArraySum {
    private static long syncSum = 0;
    private static final Object lock = new Object();
    private static final AtomicLong atomicSum = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        int size = 1000000;
        int[] data = new int[size];
        long expectedTotal = 0;
        for (int i = 0; i < size; i++) {
            data[i] = 1;
            expectedTotal += 1;
        }

        int threads = 4;
        int chunkSize = size / threads;
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            int start = i * chunkSize;
            int end = start + chunkSize;
            workers[i] = new Thread(() -> {
                long localSum = 0;
                for (int j = start; j < end; j++) localSum += data[j];
                
                // Fix 1: Synchronized block
                synchronized (lock) {
                    syncSum += localSum;
                }
                // Fix 2: Lock-free atomic addition
                atomicSum.addAndGet(localSum);
            });
            workers[i].start();
        }

        for (Thread t : workers) t.join();

        System.out.println("Expected Total : " + expectedTotal);
        System.out.println("Synchronized   : " + syncSum);
        System.out.println("AtomicLong Sum : " + atomicSum.get());
    }
}