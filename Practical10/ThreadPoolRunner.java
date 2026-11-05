import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        System.out.println("=== Fixed Thread Pool (Size: 3) Dispatching 10 Tasks ===");
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task #" + taskId + " executing on " + threadName);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                System.out.println("Task #" + taskId + " COMPLETED on " + threadName);
            });
        }

        pool.shutdown();
        boolean finished = pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("All tasks finished successfully: " + finished);
    }
}