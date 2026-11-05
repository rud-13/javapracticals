class SynchronizedAccount {
    private final String accNo;
    private long balance;

    public SynchronizedAccount(String accNo, long initialBalance) {
        this.accNo = accNo;
        this.balance = initialBalance;
    }

    // Synchronized to prevent race condition across multiple concurrent threads
    public synchronized void deposit(long amount) {
        long current = balance;
        // Small delay to amplify race condition potential if unsynchronized
        balance = current + amount;
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public String getAccNo() { return accNo; }
}

class AccountWorker implements Runnable {
    private final SynchronizedAccount account;
    private final int times;
    private final long amount;

    public AccountWorker(SynchronizedAccount account, int times, long amount) {
        this.account = account;
        this.times = times;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] STARTED depositing...");
        for (int i = 0; i < times; i++) {
            account.deposit(amount);
        }
        System.out.println("[" + Thread.currentThread().getName() + "] FINISHED.");
    }
}

public class MiniBankLab9 {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedAccount account = new SynchronizedAccount("AC0001", 0);
        int threadCount = 10;
        int depositsPerThread = 1000;
        long depositAmount = 1;

        System.out.println("Initial Balance: ₹" + account.getBalance());
        System.out.println("Starting 10 worker threads, each depositing 1000 times ₹1...");

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new AccountWorker(account, depositsPerThread, depositAmount), "Worker-" + (i + 1));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nAll worker threads joined.");
        System.out.println("Final Account Balance: ₹" + account.getBalance());
        System.out.println("Expected Balance: ₹" + (threadCount * depositsPerThread * depositAmount));
    }
}