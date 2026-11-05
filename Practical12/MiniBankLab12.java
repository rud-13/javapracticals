import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Account implements Comparable<Account> {
    private final String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-12s | Balance: ₹%,d", accountNumber, ownerName, balance);
    }
}

class AccountStore {
    private final Map<String, Account> map = new HashMap<>();
    private final List<String> history = new ArrayList<>();

    public void add(Account account) {
        map.put(account.getAccountNumber(), account);
        history.add("Account added: " + account.getAccountNumber());
    }

    public Account get(String accNo) {
        return map.get(accNo);
    }

    public List<Account> getAll() {
        return new ArrayList<>(map.values());
    }

    public List<Account> sortedByBalance() {
        List<Account> list = new ArrayList<>(map.values());
        // Highest balance first
        list.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return list;
    }

    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }
}

public class MiniBankLab12 {
    public static void main(String[] args) {
        AccountStore store = new AccountStore();
        store.add(new Account("AC0002", "Bob Smith", 5000));
        store.add(new Account("AC0001", "Alice Brown", 2000));
        store.add(new Account("AC0003", "Charlie Day", 9000));

        System.out.println("Lookup AC0002: " + store.get("AC0002"));

        System.out.println("\n=== Accounts Sorted by Balance (Highest First) ===");
        List<Account> sorted = store.sortedByBalance();
        for (Account acc : sorted) {
            System.out.println(acc);
        }

        // Thread-safe concurrent storage test
        ConcurrentHashMap<String, Account> concurrentStore = new ConcurrentHashMap<>();
        concurrentStore.put("AC0001", store.get("AC0001"));
        System.out.println("\nConcurrentHashMap initialized for multi-user safety: size = " + concurrentStore.size());
    }
}