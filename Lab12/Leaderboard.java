
import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {

        // Store player names and their scores
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Krisha", 95);
        scores.put("Panvi", 85);
        scores.put("Hinal", 95);
        scores.put("Honey", 75);
        scores.put("Heni", 90);
        scores.put("Aditi", 85);

        int N = 3;

        // Convert Map entries into a List for sorting
        List<Map.Entry<String, Integer>> players =
                new ArrayList<>(scores.entrySet());

        // Sort by score (highest first), then by name (alphabetically)
        players.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a,
                               Map.Entry<String, Integer> b) {

                int result = b.getValue().compareTo(a.getValue());

                if (result == 0) {
                    return a.getKey().compareTo(b.getKey());
                }

                return result;
            }
        });

        // Print top N players
        System.out.println("Top " + N + " Players:");

        for (int i = 0; i < N && i < players.size(); i++) {
            Map.Entry<String, Integer> player = players.get(i);

            System.out.println(
                player.getKey() + " : " + player.getValue()
            );
        }
    }
}
