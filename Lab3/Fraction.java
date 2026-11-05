import java.util.Objects;

class FractionDemo
{
    private int num;
    private int den;

    // Initialize fraction and reduce it to lowest terms
    public FractionDemo(int num, int den)
    {
        if(den == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        int g = gcd(num, den);

        this.num = num / g;
        this.den = den / g;

        // Keep denominator positive
        if(this.den < 0)
        {
            this.num = -this.num;
            this.den = -this.den;
        }
    }

    // Calculate greatest common divisor
    private int gcd(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);

        while(b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    // Return fraction in num/den format
    @Override
    public String toString()
    {
        return num + "/" + den;
    }

    // Compare two fractions using reduced numerator and denominator
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        // Check for null or different object type
        if(!(obj instanceof FractionDemo))
        {
            return false;
        }

        FractionDemo other = (FractionDemo)obj;

        return this.num == other.num && this.den == other.den;
    }

    // Generate hash code using numerator and denominator
    @Override
    public int hashCode()
    {
        return Objects.hash(num, den);
    }
}

public class Fraction
{
    public static void main(String[] args)
    {
        // Create equivalent fractions
        FractionDemo f1 = new FractionDemo(1, 2);
        FractionDemo f2 = new FractionDemo(2, 4);
        FractionDemo f3 = new FractionDemo(3, 6);

        // Display reduced fractions
        System.out.println("Fraction 1: " + f1);
        System.out.println("Fraction 2: " + f2);
        System.out.println("Fraction 3: " + f3);

        // Check whether all fractions are equal
        System.out.println("f1 equals f2: " + f1.equals(f2));
        System.out.println("f2 equals f3: " + f2.equals(f3));
        System.out.println("f1 equals f3: " + f1.equals(f3));
    }
}