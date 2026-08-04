class CinemaShow
{

	private String title;
	private int seatsAvailable;
	private final int capacity;
	private static int totalBooked=0;
	
	public CinemaShow(String title,int capacity)
	{	
		this.title=title;
		this.capacity=capacity;
		seatsAvailable = capacity; 


	}
	public CinemaShow(String title)
	{
		this(title,100);
	}
	
	public boolean book(int n)
	{
		if(n<seatsAvailable)
		{
			seatsAvailable=seatsAvailable-n;
			totalBooked=totalBooked+n;
			System.out.println("Congratulations .. "+ n + " seats booked ");
			return true;
		}
		else
		{ 
			System.out.println("Seats not available. Total seats available = "  + getSeatsAvailable());
			return false;
		}

	}
	
	public int cancel(int n)
	{
		if((seatsAvailable+n)>capacity)
		{
			System.out.println("Cannot cancel seats beyond capacity");
			return 0;
		}
		else
		{
			seatsAvailable+=n;
			return seatsAvailable;
		}
	}

	public int getSeatsAvailable()
	{
		return seatsAvailable;
	}
	public static int getTotalBooked()
	{
		return totalBooked;
	}
	
	public static void main(String []a)
	{
		CinemaShow c1 = new CinemaShow("ALpha", 200);
		CinemaShow c2 = new CinemaShow("abc");
		
		System.out.println("Total capacity = " + c1.getSeatsAvailable());
		System.out.println("Total C2 capacity = " + c2.getSeatsAvailable());
		
		c1.book(25);
		c2.book(76);

		System.out.println("Total capacity = " + c1.getSeatsAvailable());
		System.out.println("Total C2 capacity = " + c2.getSeatsAvailable());
		

		c2.book(200);
		c1.cancel(202);
		c1.cancel(2);
		System.out.println("Total C1 capacity = " + c1.getSeatsAvailable());
		System.out.println("Total C2 capacity = " + c2.getSeatsAvailable());

		System.out.println("Total Booked = " +CinemaShow.getTotalBooked());
		


	}
}
