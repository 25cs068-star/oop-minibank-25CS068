
import java.io.*;

class Student implements Serializable {

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println();
    }
}

public class SaveLoad {
    public static void main(String[] args) {

        try {
            Student[] students = {
                new Student(101, "Krisha", "abc123"),
                new Student(102, "Amit", "xyz456"),
                new Student(103, "Riya", "pqr789")
            };

            // Save array of Student objects into a file
            ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream("students.dat"));

            out.writeObject(students);
            out.close();

            System.out.println("Objects saved successfully.\n");

            // Read objects back from the file
            ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("students.dat"));

            Student[] loadedStudents = (Student[]) in.readObject();
            in.close();

            // Display loaded objects
            System.out.println("Objects loaded from file:\n");

            for (int i = 0; i < loadedStudents.length; i++) {
                loadedStudents[i].display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
