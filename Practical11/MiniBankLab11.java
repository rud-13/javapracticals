import java.io.*;
import java.nio.file.*;
import java.util.List;

class PersistentAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String accountNumber;
    private long balance;

    public PersistentAccount(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public long getBalance() { return balance; }
    @Override public String toString() { return accountNumber + " (Balance: ₹" + balance + ")"; }
}

class StatePersister {
    public static void save(PersistentAccount[] accounts, Path file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file))) {
            oos.writeObject(accounts);
        }
    }

    public static PersistentAccount[] load(Path file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
            return (PersistentAccount[]) ois.readObject();
        }
    }
}

class TransactionLog {
    public static void append(Path file, String line) throws IOException {
        Files.writeString(file, line + System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}

class ReportGenerator {
    public static void generateReport(Path logFile) throws IOException {
        long totalDeposits = 0;
        long totalWithdrawals = 0;

        List<String> lines = Files.readAllLines(logFile);
        for (String line : lines) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length >= 3) {
                String type = tokens[0];
                long amount = Long.parseLong(tokens[2]);
                if (type.equalsIgnoreCase("DEPOSIT")) {
                    totalDeposits += amount;
                } else if (type.equalsIgnoreCase("WITHDRAW")) {
                    totalWithdrawals += amount;
                }
            }
        }

        System.out.println("==========================================");
        System.out.println("      END-OF-DAY RECONCILIATION REPORT    ");
        System.out.println("==========================================");
        System.out.println("Total Deposits Recorded    : ₹" + totalDeposits);
        System.out.println("Total Withdrawals Recorded : ₹" + totalWithdrawals);
        System.out.println("Net Cash Flow Difference   : ₹" + (totalDeposits - totalWithdrawals));
        System.out.println("==========================================");
    }
}

public class MiniBankLab11 {
    public static void main(String[] args) throws Exception {
        Path dataFile = Paths.get("minibank_accounts.dat");
        Path logFile = Paths.get("transactions.log");

        // 1. Save and reload accounts
        PersistentAccount[] original = {
            new PersistentAccount("AC0001", 5000),
            new PersistentAccount("AC0002", 12000),
            new PersistentAccount("AC0003", 7500)
        };
        StatePersister.save(original, dataFile);
        System.out.println("Saved 3 accounts to disk.");

        PersistentAccount[] reloaded = StatePersister.load(dataFile);
        System.out.println("Reloaded accounts successfully: ");
        for (PersistentAccount a : reloaded) System.out.println(" - " + a);

        // 2. Log transactions
        TransactionLog.append(logFile, "DEPOSIT AC0001 500");
        TransactionLog.append(logFile, "WITHDRAW AC0002 200");
        TransactionLog.append(logFile, "DEPOSIT AC0003 1500");

        // 3. Generate summary report
        ReportGenerator.generateReport(logFile);

        // Clean up temporary files
        Files.deleteIfExists(dataFile);
        Files.deleteIfExists(logFile);
    }
}