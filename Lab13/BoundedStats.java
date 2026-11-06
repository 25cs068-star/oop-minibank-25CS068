
public class BoundedStats {

    // Generic method to calculate average of Number types
    public static <N extends Number> double average(N[] values) {

        double sum = 0;

        for (int i = 0; i < values.length; i++) {
            sum = sum + values[i].doubleValue();
        }

        return sum / values.length;
    }

    public static void main(String[] args) {

        Integer[] intValues = {10, 20, 30, 40};
        Double[] doubleValues = {10.5, 20.5, 30.5, 40.5};
        Long[] longValues = {100L, 200L, 300L, 400L};

        System.out.println("Integer Average: " +
                average(intValues));

        System.out.println("Double Average: " +
                average(doubleValues));

        System.out.println("Long Average: " +
                average(longValues));
    }
}
