import java.util.concurrent.atomic.AtomicLong;

class ArraySum
{
    long total = 0;
    AtomicLong atomicTotal = new AtomicLong(0);

    // Adds numbers without synchronization
    void addWithoutSync(int[] arr, int start, int end)
    {
        for (int i = start; i < end; i++)
        {
            total += arr[i];
        }
    }

    // Adds numbers using synchronized method
    synchronized void addWithSync(int[] arr, int start, int end)
    {
        for (int i = start; i < end; i++)
        {
            total += arr[i];
        }
    }

    // Adds numbers using AtomicLong
    void addWithAtomic(int[] arr, int start, int end)
    {
        for (int i = start; i < end; i++)
        {
            atomicTotal.addAndGet(arr[i]);
        }
    }
}

public class ArraySumDemo
{
    public static void main(String arg[])
    {
        int size = 1000000;
        int[] arr = new int[size];

        // Fill the array with numbers
        for (int i = 0; i < size; i++)
        {
            arr[i] = 1;
        }

        int threadCount = 4;
        int part = size / threadCount;

        // Test without synchronization
        ArraySum s1 = new ArraySum();
        Thread[] t1 = new Thread[threadCount];

        long start1 = System.nanoTime();

        for (int i = 0; i < threadCount; i++)
        {
            int start = i * part;
            int end = (i == threadCount - 1) ? size : start + part;

            t1[i] = new Thread(() -> s1.addWithoutSync(arr, start, end));
            t1[i].start();
        }

        try
        {
            for (int i = 0; i < threadCount; i++)
            {
                t1[i].join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        long end1 = System.nanoTime();

        System.out.println("Without Synchronization:");
        System.out.println("Total: " + s1.total);
        System.out.println("Expected Total: " + size);
        System.out.println("Time: " + (end1 - start1) + " ns");

        // Test with synchronized method
        ArraySum s2 = new ArraySum();
        Thread[] t2 = new Thread[threadCount];

        long start2 = System.nanoTime();

        for (int i = 0; i < threadCount; i++)
        {
            int start = i * part;
            int end = (i == threadCount - 1) ? size : start + part;

            t2[i] = new Thread(() -> s2.addWithSync(arr, start, end));
            t2[i].start();
        }

        try
        {
            for (int i = 0; i < threadCount; i++)
            {
                t2[i].join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        long end2 = System.nanoTime();

        System.out.println("\nWith Synchronization:");
        System.out.println("Total: " + s2.total);
        System.out.println("Expected Total: " + size);
        System.out.println("Time: " + (end2 - start2) + " ns");

        // Test with AtomicLong
        ArraySum s3 = new ArraySum();
        Thread[] t3 = new Thread[threadCount];

        long start3 = System.nanoTime();

        for (int i = 0; i < threadCount; i++)
        {
            int start = i * part;
            int end = (i == threadCount - 1) ? size : start + part;

            t3[i] = new Thread(() -> s3.addWithAtomic(arr, start, end));
            t3[i].start();
        }

        try
        {
            for (int i = 0; i < threadCount; i++)
            {
                t3[i].join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        long end3 = System.nanoTime();

        System.out.println("\nWith AtomicLong:");
        System.out.println("Total: " + s3.atomicTotal.get());
        System.out.println("Expected Total: " + size);
        System.out.println("Time: " + (end3 - start3) + " ns");
    }
}