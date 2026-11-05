import java.util.Scanner;

public class ChatFilterDemo
{
    public static void main(String arg[])
    {
        // Store chat log lines including one malformed line
        String log[] =
        {
            "3:28 Krisha Hello Honey",
            "4:34 Heni Good Morning",
            "4:01 Honey How are you",
            "3:34 Panvi",
            "3:32 Krisha Good Morning"
        };

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the keyword:");
        String keyword = sc.nextLine();

        int match = 0;
        StringBuilder report = new StringBuilder();

        // Check each line in the chat log
        for(String line : log)
        {
            String[] parts = ChatFilter.splitLine(line);

            // Skip malformed lines
            if(parts == null)
            {
                continue;
            }

            String time = parts[0];
            String name = parts[1];
            String message = parts[2];

            // Check whether message contains the keyword
            if(ChatFilter.hasKeyword(message, keyword))
            {
                match++;

                // Add matching chat to the report
                report.append(time)
                      .append(" ")
                      .append(name)
                      .append(": ")
                      .append(message)
                      .append("\n");
            }
        }

        System.out.println("Matches: " + match);
        System.out.println("Report:");
        System.out.println(report);

        sc.close();
    }
}

class ChatFilter
{
    // Split chat line into time, user and message
    public static String[] splitLine(String line)
    {
        String[] parts = line.split(" ", 3);

        if(parts.length < 3)
        {
            return null;
        }

        return parts;
    }

    // Check keyword without case sensitivity
    public static boolean hasKeyword(String message, String keyword)
    {
        return message.toLowerCase().contains(keyword.toLowerCase());
    }
}