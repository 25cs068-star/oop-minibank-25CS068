abstract class Shape
{
    // Every shape must calculate its own area
    abstract public double area();
}

class Circle extends Shape
{
    double radius;

    Circle(double radius)
    {
        this.radius = radius;
    }

    @Override
    public double area()
    {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape
{
    double height;
    double width;

    Rectangle(double height, double width)
    {
        this.height = height;
        this.width = width;
    }

    @Override
    public double area()
    {
        return height * width;
    }
}

class Triangle extends Shape
{
    double base;
    double height;

    Triangle(double base, double height)
    {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area()
    {
        return (base * height) / 2;
    }
}

public class ShapeDemo
{
    public static void main(String arg[])
    {
        // Store different shapes in one array
        Shape[] shapes =
        {
            new Circle(5),
            new Rectangle(5, 4),
            new Triangle(3, 4)
        };

        double total = 0;
        double largest = -1;
        String label = "";

        // Calculate area of every shape using polymorphism
        for(Shape s : shapes)
        {
            double a = s.area();

            total += a;

            System.out.println("Area of " +
                s.getClass().getSimpleName() + ": " + a);

            // Display running total
            System.out.println("Running Total: " + total);

            // Track the shape with the largest area
            if(largest < a)
            {
                largest = a;
                label = s.getClass().getSimpleName();
            }
        }

        System.out.println("The total area is: " + total);
        System.out.println("The largest area is " +
            largest + " of shape: " + label);
    }
}