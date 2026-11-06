
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LogAnalyzer {
    public static void main(String[] args) {

        try {
            // Create a folder and text files
            Path folder = Paths.get("logs");
            Files.createDirectories(folder);

            Path file1 = folder.resolve("log1.txt");
            Path file2 = folder.resolve("log2.txt");
            Path file3 = folder.resolve("log3.txt");

            Files.write(file1, (
                "Application started\n" +
                "User logged in\n" +
                "Error occurred\n"
            ).getBytes());

            Files.write(file2, (
                "Server running\n" +
                "Warning detected\n" +
                "User logged out\n"
            ).getBytes());

            Files.write(file3, (
                "Error in database\n" +
                "Connection successful\n" +
                "Error found again\n"
            ).getBytes());

            String keyword = "Error";

            int totalLines = 0;
            int keywordCount = 0;

            // Read each text file line by line
            try (DirectoryStream<Path> files =
                    Files.newDirectoryStream(folder, "*.txt")) {

                for (Path file : files) {

                    System.out.println("File: " + file.getFileName());

                    try (BufferedReader reader =
                            Files.newBufferedReader(file)) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            totalLines++;

                            if (line.contains(keyword)) {
                                keywordCount++;
                                System.out.println("  " + line);
                            }
                        }
                    }

                    // Get file size using file attributes
                    BasicFileAttributes attributes =
                        Files.readAttributes(file, BasicFileAttributes.class);

                    System.out.println(
                        "File Size: " + attributes.size() + " bytes"
                    );

                    System.out.println();
                }
            }

            // Print total analysis
            System.out.println("Total Lines: " + totalLines);
            System.out.println("Lines containing '" + keyword +
                               "': " + keywordCount);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
