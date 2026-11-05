
class ParkingLot
{
    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap)
    {
        this.twoCap = twoCap;
        this.fourCap = fourCap;

        twoWheelers = 0;
        fourWheelers = 0;
    }

    public void park(String type)
    {
        if(type.equalsIgnoreCase("two"))
        {
            // Check capacity before parking two wheeler
            if(twoWheelers < twoCap)
            {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two Wheeler Parked");
            }
            else
            {
                System.out.println("Full");
            }
        }
        else if(type.equalsIgnoreCase("four"))
        {
            // Check capacity before parking four wheeler
            if(fourWheelers < fourCap)
            {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four Wheeler Parked");
            }
            else
            {
                System.out.println("Full");
            }
        }
        else
        {
            System.out.println("Invalid Vehicle Type");
        }
    }

    public void leave(String type)
    {
        if(type.equalsIgnoreCase("two"))
        {
            // Decrease count only if vehicle is available
            if(twoWheelers > 0)
            {
                twoWheelers--;
                System.out.println("Two Wheeler Left");
            }
            else
            {
                System.out.println("No Two Wheeler to Leave");
            }
        }
        else if(type.equalsIgnoreCase("four"))
        {
            if(fourWheelers > 0)
            {
                fourWheelers--;
                System.out.println("Four Wheeler Left");
            }
            else
            {
                System.out.println("No Four Wheeler to Leave");
            }
        }
        else
        {
            System.out.println("Invalid Vehicle Type");
        }
    }

    public int getTwoWheelers()
    {
        return twoWheelers;
    }

    public int getFourWheelers()
    {
        return fourWheelers;
    }

    public static long getRevenue()
    {
        return revenue;
    }

    public static void main(String args[])
    {
        ParkingLot p = new ParkingLot(2, 2);

        p.park("two");
        p.park("two");
        p.park("two");

        p.park("four");
        p.park("four");
        p.park("four");

        p.leave("two");
        p.leave("four");
        p.leave("four");

        System.out.println("Two Wheelers = " + p.getTwoWheelers());
        System.out.println("Four Wheelers = " + p.getFourWheelers());

        System.out.println("Revenue = " + ParkingLot.getRevenue());
    }
}
