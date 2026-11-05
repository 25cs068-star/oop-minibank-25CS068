
abstract class Media {
    public String title;
    public int lateDay;

    Media(String title, int lateDay) {
        this.title = title;
        this.lateDay = lateDay;
    }

    // Abstract method to calculate late fees
    abstract public double lateFees();
}

class Book extends Media {
    Book(String title, int lateDay) {
        super(title, lateDay);
    }

    public double lateFees() {
        return lateDay * 2;
    }
}

class Magazine extends Media {
    Magazine(String title, int lateDay) {
        super(title, lateDay);
    }

    public double lateFees() {
        return lateDay * 4;
    }
}

class Cd extends Media {
    Cd(String title, int lateDay) {
        super(title, lateDay);
    }

    public double lateFees() {
        return lateDay * 5;
    }
}

public class MediaDemo {
    public static void main(String a[]) {

        // Store different media types in one array
        Media[] m = {
            new Book("XYZ", 4),
            new Magazine("ABC", 4),
            new Cd("CDE", 5)
        };

        double total = 0;

        // Calculate and display late fees for each media
        for (Media media : m) {
            System.out.println("Title: " + media.title);
            System.out.println("Late Days: " + media.lateDay);
            System.out.println("Late Fee: " + media.lateFees());

            total += media.lateFees();

            System.out.println("--------------------");
        }

        // Display total late fees
        System.out.println("Total Late Fees: " + total);
    }
}
