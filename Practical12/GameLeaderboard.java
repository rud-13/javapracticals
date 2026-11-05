import java.util.*;

public class GameLeaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scoreBoard = new HashMap<>();
        scoreBoard.put("ShadowNinja", 2450);
        scoreBoard.put("CyberKnight", 3100);
        scoreBoard.put("PixelMage", 1850);
        scoreBoard.put("Valkyrie", 3100);
        scoreBoard.put("GhostRider", 2900);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(scoreBoard.entrySet());
        list.sort((a, b) -> {
            int scoreDiff = b.getValue().compareTo(a.getValue());
            return (scoreDiff != 0) ? scoreDiff : a.getKey().compareTo(b.getKey());
        });

        int topN = 3;
        System.out.println("=== TOP " + topN + " LEADERBOARD ===");
        for (int i = 0; i < topN && i < list.size(); i++) {
            Map.Entry<String, Integer> player = list.get(i);
            System.out.printf("Rank %d | %-15s : %d pts%n", (i + 1), player.getKey(), player.getValue());
        }
    }
}