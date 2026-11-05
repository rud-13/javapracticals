public class DeadlockResolution {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void safeTransfer(Object firstLock, Object secondLock, String threadName) {
        // Enforce consistent locking order based on System identity hash code
        Object lock1 = System.identityHashCode(firstLock) < System.identityHashCode(secondLock) ? firstLock : secondLock;
        Object lock2 = (lock1 == firstLock) ? secondLock : firstLock;

        synchronized (lock1) {
            System.out.println(threadName + " acquired lock 1");
            try { Thread.sleep(20); } catch (InterruptedException ignored) {}
            synchronized (lock2) {
                System.out.println(threadName + " acquired lock 2 -> TRANSACTION EXECUTED SAFELY");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Testing Deadlock-Free Consistent Lock Ordering ===");
        Thread t1 = new Thread(() -> safeTransfer(resourceA, resourceB, "Thread-1"));
        Thread t2 = new Thread(() -> safeTransfer(resourceB, resourceA, "Thread-2"));

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Both threads finished successfully without hanging in deadlock.");
    }
}