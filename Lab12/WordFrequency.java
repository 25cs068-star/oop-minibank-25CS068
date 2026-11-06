
import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "java is easy and java is powerful";

        // Split paragraph into individual words
        String[] words = paragraph.split(" ");

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

        HashMap<String, Integer> frequency = new HashMap<>();

        // Count frequency of each word using HashMap
        for (int i = 0; i < words.length; i++) {
            if (frequency.containsKey(words[i])) {
                frequency.put(words[i], frequency.get(words[i]) + 1);
            } else {
                frequency.put(words[i], 1);
            }
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        // Sort words by frequency in descending order
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a,
                               Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        // Print words with their frequencies
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
