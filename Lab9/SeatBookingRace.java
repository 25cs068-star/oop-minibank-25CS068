class Cinema
{
    int seatsLeft = 5;
    int seatsBooked = 0;

    // Without synchronization: race condition may cause overselling
    public void bookWithoutSync()
    {
        if (seatsLeft > 0)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            seatsLeft--;
            seatsBooked++;

            System.out.println(Thread.currentThread().getName() + " booked a seat");
        }
        else
        {
            System.out.println(Thread.currentThread().getName() + " could not book a seat");
        }
    }

    // Synchronization prevents more than 5 successful bookings
    public synchronized void book()
    {
        if (seatsLeft > 0)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            seatsLeft--;
            seatsBooked++;

            System.out.println(Thread.currentThread().getName() + " booked a seat");
        }
        else
        {
            System.out.println(Thread.currentThread().getName() + " could not book a seat");
        }
    }
}

public class SeatBookingRace
{
    public static void main(String a[])
    {
        // Test without synchronization
        Cinema c1 = new Cinema();
        Thread[] t1 = new Thread[10];

        System.out.println("Without Synchronization:");

        for (int i = 0; i < 10; i++)
        {
            t1[i] = new Thread(() -> c1.bookWithoutSync(), "Thread-" + (i + 1));
        }

        for (int i = 0; i < 10; i++)
        {
            t1[i].start();
        }

        try
        {
            for (int i = 0; i < 10; i++)
            {
                t1[i].join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        System.out.println("Seats booked: " + c1.seatsBooked);
        System.out.println("Seats left: " + c1.seatsLeft);

        // Test with synchronization
        Cinema c2 = new Cinema();
        Thread[] t2 = new Thread[10];

        System.out.println("\nWith Synchronization:");

        for (int i = 0; i < 10; i++)
        {
            t2[i] = new Thread(() -> c2.book(), "Thread-" + (i + 1));
        }

        for (int i = 0; i < 10; i++)
        {
            t2[i].start();
        }

        try
        {
            for (int i = 0; i < 10; i++)
            {
                t2[i].join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        System.out.println("Seats booked: " + c2.seatsBooked);
        System.out.println("Seats left: " + c2.seatsLeft);
    }
}