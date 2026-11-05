import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = List.of("alice", "bob", "alice", "charlie", "bob", "david", "alice", "eve");

        Set<String> uniqueSet = new HashSet<>(logins);
        Set<String> firstSeenOrder = new LinkedHashSet<>(logins);

        System.out.println("Total Login Events: " + logins.size());
        System.out.println("Unique Visitors Count: " + uniqueSet.size());

        System.out.println("\nFirst Three Distinct Visitors (Chronological First-Seen Order):");
        int count = 0;
        for (String user : firstSeenOrder) {
            System.out.println((++count) + ". " + user);
            if (count == 3) break;
        }
    }
}