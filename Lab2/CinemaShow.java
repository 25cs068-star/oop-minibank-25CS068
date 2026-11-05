
class CinemaShow
{
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    public CinemaShow(String title)
    {
        this(title, 100);
    }

    public boolean book(int n)
    {
        // Check seats are available before booking
        if(n <= seatsAvailable)
        {
            seatsAvailable = seatsAvailable - n;
            totalBooked = totalBooked + n;

            return true;
        }
        else
        {
            return false;
        }
    }

    public void cancel(int n)
    {
        // Cancel seats without exceeding the capacity
        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }

    public int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    public static int getTotalBooked()
    {
        return totalBooked;
    }

    public static void main(String a[])
    {
        CinemaShow c1 = new CinemaShow("Alpha", 200);
        CinemaShow c2 = new CinemaShow("ABC");

        System.out.println("Total C1 capacity = " + c1.getSeatsAvailable());
        System.out.println("Total C2 capacity = " + c2.getSeatsAvailable());

        // Book seats and check available seats
        System.out.println("Booking 25 seats: " + c1.book(25));
        System.out.println("C1 Seats Available: " + c1.getSeatsAvailable());

        System.out.println("Booking 76 seats: " + c2.book(76));
        System.out.println("C2 Seats Available: " + c2.getSeatsAvailable());

        System.out.println("Booking 200 seats: " + c2.book(200));
        System.out.println("C2 Seats Available: " + c2.getSeatsAvailable());

        // Cancel seats and check available seats
        c1.cancel(202);
        System.out.println("C1 Seats Available: " + c1.getSeatsAvailable());

        c1.cancel(2);
        System.out.println("C1 Seats Available: " + c1.getSeatsAvailable());

        System.out.println("Total Booked = " + CinemaShow.getTotalBooked());
    }
}
