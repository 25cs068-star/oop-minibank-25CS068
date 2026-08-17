abstract  class Shape
{
    abstract public  double area();
}

class Circle extends Shape
{
    float redius;
    Circle(float redius)
    {
        this.redius=redius;

        
    }

    public double area()
    {
        return Math.PI *redius*redius;
    }
}

class Rectangle extends Shape
{

    float hight;
    float width;

    Rectangle(float hight, float width)
    {   
        this.hight=hight;
        this.width=width;


    }
    public double area()
    {
        return  hight*width;
    }


}
class Triangle extends Shape
{
    float base;
    float hight;

    Triangle(float base, float hight)
    {   
        this.base=base;
        this.hight=hight;


    }
    public double area()
    {
        return  (hight*base)/2;
    }
}
 public class ShapeDemo
{
    public static void main(String arg[])
    {
        Shape[] shapes={
            new Circle(5),
            new Rectangle(5,4),
            new Triangle(3,4)
        };

        double total=0;
        double largest=-1;

        String lable="";


        for(Shape s:shapes)
        {

            System.out.println("Area:"+s.area());
            total+=s.area();
            if(largest<s.area())
            {
            largest=s.area();
            lable=s.getClass().getSimpleName();
            }
            

        }

        System.out.println("The total area is:"+total);
        System.out.println("The largest area is "+largest+" of shape:"+lable);




    }
}