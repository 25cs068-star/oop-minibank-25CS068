
class Counter
{
    static int count;

    // Synchronized method to prevent race condition
    public static synchronized void increment()
    {
        count++;
    }
}

public class CounterDemo
{
    public static void main(String arg[])
    {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        // Create two threads to increment the shared counter
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 50000; i++) {
                c1.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 50000; i++) {
                c2.increment();
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        try
        {
            // Wait until both threads complete
            t1.join();
            t2.join();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        // Print the final counter value
        System.out.println("Count:" + c1.count);
    }
}
