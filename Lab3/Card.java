import java.util.Objects;

class CardDemo
{
    private String rank;
    private String suit;

    // Initialize card rank and suit
    CardDemo(String rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    // Return card details in readable format
    @Override
    public String toString()
    {
        return rank + " of " + suit;
    }

    // Compare two cards using rank and suit
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        // Check for null or different object type
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        CardDemo c = (CardDemo)obj;

        return rank.equals(c.rank) && suit.equals(c.suit);
    }

    // Generate hash code using rank and suit
    @Override
    public int hashCode()
    {
        return Objects.hash(rank, suit);
    }
}

public class Card
{
    public static void main(String[] args)
    {
        // Create an array to store 5 cards
        CardDemo[] cards = new CardDemo[5];

        // Add cards one by one
        cards[0] = new CardDemo("Ace", "Spades");
        cards[1] = new CardDemo("King", "Hearts");
        cards[2] = new CardDemo("Queen", "Clubs");
        cards[3] = new CardDemo("Ace", "Spades");
        cards[4] = new CardDemo("10", "Diamonds");

        boolean found = false;

        // Check each card with all previously added cards
        for(int i = 0; i < cards.length; i++)
        {
            for(int j = 0; j < i; j++)
            {
                // Check whether the current card is already present
                if(cards[i].equals(cards[j]))
                {
                    System.out.println("Duplicate found: " + cards[i]);
                    found = true;
                    break;
                }
            }

            // Stop checking after finding the first duplicate
            if(found)
            {
                break;
            }
        }
    }
}