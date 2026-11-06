
class Box<T> {
    private T value;

    // Set value in Box
    public void set(T value) {
        this.value = value;
    }

    // Get value from Box
    public T get() {
        return value;
    }
}

class Pair<K, V> {
    private K first;
    private V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}

public class GenericContainers {

    // Generic method to return the first element of any array
    public static <T> T getFirst(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {

        // Box with different data types
        Box<Integer> intBox = new Box<>();
        intBox.set(100);

        Box<String> stringBox = new Box<>();
        stringBox.set("Java Programming");

        System.out.println("Integer Box: " + intBox.get());
        System.out.println("String Box: " + stringBox.get());

        // Pair with different data types
        Pair<Integer, String> pair =
                new Pair<>(101, "Krisha");

        System.out.println("\nPair Details:");
        System.out.println("ID: " + pair.getFirst());
        System.out.println("Name: " + pair.getSecond());

        // Generic method with Integer array
        Integer[] numbers = {10, 20, 30, 40};
        System.out.println("\nFirst Number: " + getFirst(numbers));

        // Generic method with String array
        String[] names = {"Krisha", "Amit", "Riya"};
        System.out.println("First Name: " + getFirst(names));
    }
}
