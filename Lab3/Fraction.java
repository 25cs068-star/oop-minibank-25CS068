 class FractionDemo {
    private int num;
    private int den;

    public FractionDemo(int num, int den) {
        int g = gcd(num, den);
        this.num = num / g;
        this.den = den / g;
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Fraction))
            return false;

        FractionDemo other = (FractionDemo) obj;

        return this.num == other.num && this.den == other.den;
    }
}

public class Fraction {
    public static void main(String[] args) {

        FractionDemo f1 = new FractionDemo(1, 2);
        FractionDemo f2 = new FractionDemo(2, 4);
        FractionDemo f3 = new FractionDemo(3, 6);

        System.out.println("Fraction 1: " + f1);
        System.out.println("Fraction 2: " + f2);
        System.out.println("Fraction 3: " + f3);

        System.out.println("f1 equals f2: " + f1.equals(f2));
        System.out.println("f2 equals f3: " + f2.equals(f3));
        System.out.println("f1 equals f3: " + f1.equals(f3));
    }
}