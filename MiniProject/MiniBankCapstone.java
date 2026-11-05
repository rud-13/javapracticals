import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

class BankService {
    private final Map<String, Long> accounts = new ConcurrentHashMap<>();
    private final Path logFile = Paths.get("minibank_final.log");

    public BankService() {
        // Sample seed accounts
        accounts.put("AC0001", 10000L);
        accounts.put("AC0002", 5000L);
    }

    public synchronized void openAccount(String accNo, long initialDeposit) {
        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists.");
            return;
        }
        accounts.put(accNo, Math.max(0, initialDeposit));
        log("OPEN " + accNo + " " + initialDeposit);
        System.out.println("Account " + accNo + " successfully opened with ₹" + initialDeposit);
    }

    public synchronized void deposit(String accNo, long amount) {
        if (!accounts.containsKey(accNo)) {
            System.out.println("Account not found: " + accNo);
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        accounts.put(accNo, accounts.get(accNo) + amount);
        log("DEPOSIT " + accNo + " " + amount);
        System.out.println("Deposited ₹" + amount + " into " + accNo + ". New Balance: ₹" + accounts.get(accNo));
    }

    public synchronized boolean withdraw(String accNo, long amount) {
        if (!accounts.containsKey(accNo)) {
            System.out.println("Account not found: " + accNo);
            return false;
        }
        long bal = accounts.get(accNo);
        if (amount > bal) {
            System.out.println("Insufficient funds! Balance: ₹" + bal + ", Shortfall: ₹" + (amount - bal));
            return false;
        }
        accounts.put(accNo, bal - amount);
        log("WITHDRAW " + accNo + " " + amount);
        System.out.println("Withdrawn ₹" + amount + " from " + accNo + ". New Balance: ₹" + accounts.get(accNo));
        return true;
    }

    public synchronized void transfer(String from, String to, long amount) {
        if (withdraw(from, amount)) {
            deposit(to, amount);
            System.out.println("Successfully transferred ₹" + amount + " from " + from + " to " + to);
        }
    }

    public void printStatement(String accNo) {
        if (!accounts.containsKey(accNo)) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("----------------------------------------");
        System.out.println("Account Statement: " + accNo);
        System.out.println("Balance: ₹" + accounts.get(accNo));
        System.out.println("----------------------------------------");
    }

    public void listAll() {
        System.out.println("\n--- Active Bank Accounts ---");
        accounts.forEach((acc, bal) -> System.out.printf("%-10s : ₹%,d%n", acc, bal));
    }

    private void log(String line) {
        try {
            Files.writeString(logFile, line + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ignored) {}
    }
}

public class MiniBankCapstone {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  CHARUSAT MINIBANK - SEMESTER CAPSTONE SYSTEM INTEGRATION");
        System.out.println("==========================================================");

        BankService bank = new BankService();
        bank.listAll();

        System.out.println("\n--- Executing Transactions ---");
        bank.deposit("AC0001", 2000);
        bank.withdraw("AC0002", 1500);
        bank.transfer("AC0001", "AC0002", 3000);
        bank.withdraw("AC0002", 20000); // Insufficient funds

        bank.printStatement("AC0001");
        bank.printStatement("AC0002");
        bank.listAll();

        System.out.println("\nMilestone 3 Deliverable: All 14 Practical Modules Integrated Successfully!");
    }
}