import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "java is robust and java is object oriented and java is secure and platform independent";
        String[] words = paragraph.toLowerCase().split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        // Sort by frequency descending; ties broken alphabetically
        entries.sort((e1, e2) -> {
            int cmp = e2.getValue().compareTo(e1.getValue());
            return (cmp != 0) ? cmp : e1.getKey().compareTo(e2.getKey());
        });

        System.out.println("=== Word Frequencies (Sorted Highest to Lowest) ===");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.printf("%-15s : %d%n", entry.getKey(), entry.getValue());
        }
    }
}