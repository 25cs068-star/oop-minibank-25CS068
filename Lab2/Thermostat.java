class Thermostat
{

	private  static final int MIN=16;
	private  static final int MAX=30;
	private String location;
	private int temperature;
	private static int activeCount;

	public Thermostat(String location ,int startTemp)
	{
		this.location=location;
		if(startTemp<=MAX ||startTemp<MIN)
		{
			temperature=22;
		}
		else
		{
			temperature=startTemp;
		}
		activeCount++;
	 }
	public Thermostat(String loction)
	{
		this(loction,22);
	}
	public void raise()
	{ 
		if(temperature<MAX)
		{
			temperature++;
		}
		else
		{
			System.out.println("Already At maximum temperatue(30)");
		}

	}
	public void lower() 
	{ 
		if(temperature>MIN)
		{
			temperature--;
		}
		else
		{
			System.out.println("Already At minimum temperatue(16)");
		}

	}
	
	public int getTemp()
	{
		return temperature;
	}
	
	public static int getActiveCount()
	{
		return activeCount;
	}
	public static void main(String arg[])
	{
		Thermostat t1=new Thermostat("Navsari",22);
		Thermostat t2=new Thermostat("Anand");
		
		
	for(int i=0;i<10;i++)
	{ 
		t1.raise();
	}
	for(int i=0;i<20;i++)
	{
		t1.lower();
	}
	System.out.println("Temperature:"+t1.temperature);
	System.out.println("The total Count:"+Thermostat.getActiveCount());
	}

}
