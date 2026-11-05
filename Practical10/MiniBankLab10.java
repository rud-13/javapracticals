import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ManagedAccount {
    private final String accountNumber;
    private long balance;

    public ManagedAccount(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public synchronized void deposit(long amt) { balance += amt; }
    public synchronized boolean withdraw(long amt) {
        if (balance >= amt) { balance -= amt; return true; }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public synchronized long getBalance() { return balance; }
}

class TransactionProcessor {
    private final ExecutorService executor;

    public TransactionProcessor(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    // Deadlock-free transfer method by locking lowest accountNumber first
    public static void transfer(ManagedAccount from, ManagedAccount to, long amount) {
        ManagedAccount first = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? from : to;
        ManagedAccount second = (first == from) ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    System.out.println("Transferred ₹" + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber());
                } else {
                    System.out.println("Transfer failed: Insufficient funds in " + from.getAccountNumber());
                }
            }
        }
    }
}

public class MiniBankLab10 {
    public static void main(String[] args) {
        ManagedAccount accA = new ManagedAccount("AC0001", 10000);
        ManagedAccount accB = new ManagedAccount("AC0002", 5000);

        TransactionProcessor processor = new TransactionProcessor(4);

        System.out.println("Submitting simultaneous reciprocal transfers...");
        // Submit A -> B and B -> A concurrently
        processor.submit(() -> TransactionProcessor.transfer(accA, accB, 1500));
        processor.submit(() -> TransactionProcessor.transfer(accB, accA, 1000));
        processor.submit(() -> accA.deposit(500));

        processor.stop();

        System.out.println("\nFinal Status:");
        System.out.println(accA.getAccountNumber() + " Balance: ₹" + accA.getBalance());
        System.out.println(accB.getAccountNumber() + " Balance: ₹" + accB.getBalance());
    }
}