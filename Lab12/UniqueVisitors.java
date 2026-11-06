
import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {

        String[] loginNames = {
            "Krisha", "Heni", "Hinal", "Krisha",
            "Aditi", "Heni", "Neha", "Hinal"
        };

        HashSet<String> uniqueUsers = new HashSet<>();
        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>();

        // Store unique users and maintain first-seen order
        for (int i = 0; i < loginNames.length; i++) {
            uniqueUsers.add(loginNames[i]);
            orderedUsers.add(loginNames[i]);
        }

        // Print total number of unique users
        System.out.println("Unique User Count: " + uniqueUsers.size());

        // Print first three distinct names
        System.out.println("First Three Unique Users:");

        int count = 0;

        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}
