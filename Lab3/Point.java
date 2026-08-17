class PointDemo
{
    private int x;
    private int y;

    public PointDemo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        PointDemo p = (PointDemo)obj;

        return x == p.x && y == p.y;
    }
}
public class Point
{
    public static void main(String[] args)
    {
        PointDemo p[] =
        {
            new PointDemo(1, 2),
            new PointDemo(3, 4),
            new PointDemo(1, 2),
            new PointDemo(5, 6),
            new PointDemo(3, 4)
        };

        int count = 0;

        for(int i = 0; i < p.length; i++)
        {
            boolean found = false;

            for(int j = 0; j < i; j++)
            {
                if(p[i].equals(p[j]))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                count++;
            }
        }

        System.out.println("Distinct: " + count);
    }
}