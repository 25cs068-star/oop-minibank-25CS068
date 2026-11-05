
import java.lang.annotation.*;
import java.lang.reflect.*;

// Custom annotation for column name
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {

    @Column(name = "StudentName")
    String name;

    @Column(name = "StudentAge")
    int age;

    @Column(name = "StudentEmail")
    String email;

    public String toString() {
        return "Name: " + name +
               "\nAge: " + age +
               "\nEmail: " + email;
    }
}

public class ColumnMapping {

    // Populate object fields using header and data rows
    public static void populate(Object obj, String[] header, String[] data)
            throws Exception {

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();

                boolean found = false;

                // Find matching column name in header
                for (int i = 0; i < header.length; i++) {

                    if (header[i].equals(columnName)) {
                        field.setAccessible(true);

                        if (i < data.length) {
                            if (field.getType() == String.class) {
                                field.set(obj, data[i]);
                            } else if (field.getType() == int.class) {
                                field.setInt(obj, Integer.parseInt(data[i]));
                            }
                        }

                        found = true;
                        break;
                    }
                }

                // Handle missing column
                if (!found) {
                    System.out.println("Column not found: " + columnName);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        String[] header = {
            "StudentName", "StudentAge", "StudentEmail"
        };

        String[] data = {
            "Krisha", "20", "krisha@gmail.com"
        };

        Student s = new Student();

        populate(s, header, data);

        System.out.println("\nStudent Details:");
        System.out.println(s);
    }
}
