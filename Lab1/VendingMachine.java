
import java.util.*;

class VendingMachine
{
    enum Coin
    {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String a[])
    {
        final int PRICE = 15;
        int total = 0;

        Coin c = null;

        Scanner sc = new Scanner(System.in);

        // Loop continues until total reaches 15
        while(total < PRICE)
        {
            System.out.println("Enter A Coin");

            String input = sc.nextLine();

            try
            {
                // Convert input into Coin enum
                c = Coin.valueOf(input.toUpperCase());
            }
            catch(Exception e)
            {
                System.out.println("Not Valid Coin");
                continue;
            }

            // Convert coin into its value using switch expression
            int value = switch(c)
            {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            // Add coin value to total
            total = total + value;

            System.out.println("Total: " + total);
        }

        // Calculate and print remaining change
        System.out.println("Paid. Change: " + (total - PRICE));

        sc.close();
    }
}
