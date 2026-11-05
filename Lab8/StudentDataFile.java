
class StudentResource implements AutoCloseable {

    StudentResource() {
        System.out.println("Student data file opened");
    }

    void readData() {
        System.out.println("Reading student data...");
        throw new RuntimeException("Error while reading student data");
    }

    // Automatically called when try-with-resources ends
    @Override
    public void close() {
        System.out.println("Student data file closed");
    }
}

public class StudentDataFile {
    public static void main(String[] args) {

        // Automatically closes the resource even if an exception occurs
        try (StudentResource file = new StudentResource()) {
            file.readData();
        }

        catch (RuntimeException e) {
            // Report the original exception
            System.out.println("Reported error: " + e.getMessage());
        }
    }
}
