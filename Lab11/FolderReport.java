
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class FolderReport {
    public static void main(String[] args) {

        Path folder = Paths.get("MyFolder");
        Path report = Paths.get("folder_report.txt");

        try {
            // Create folder and sample files
            Files.createDirectories(folder);
            Files.write(folder.resolve("file1.txt"),
                    "Hello Java".getBytes());
            Files.write(folder.resolve("file2.txt"),
                    "Welcome to NIO".getBytes());

            Path subFolder = folder.resolve("SubFolder");
            Files.createDirectories(subFolder);

            Files.write(subFolder.resolve("file3.txt"),
                    "Folder Tree Example".getBytes());

            // Create report file
            try (BufferedWriter writer =
                    Files.newBufferedWriter(report)) {

                writer.write("FOLDER TREE REPORT");
                writer.newLine();
                writer.write("==================");
                writer.newLine();

                // Walk through folder and all subfolders
                Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {

                    public FileVisitResult visitFile(
                            Path file, BasicFileAttributes attributes)
                            throws IOException {

                        String name = file.getFileName().toString();
                        long size = attributes.size();

                        String modifiedTime = attributes.lastModifiedTime()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern(
                                        "dd-MM-yyyy HH:mm:ss"));

                        writer.write("File Name: " + name);
                        writer.newLine();

                        writer.write("Size: " + size + " bytes");
                        writer.newLine();

                        writer.write("Modified Time: " + modifiedTime);
                        writer.newLine();

                        writer.write("----------------------");
                        writer.newLine();

                        return FileVisitResult.CONTINUE;
                    }
                });

                System.out.println("Folder scanned successfully.");
                System.out.println("Report saved to: " +
                        report.toAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
