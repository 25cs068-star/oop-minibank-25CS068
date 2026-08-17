class CardDemo
{
    private String rank;
    private String suit;

    CardDemo(String rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString()
    {
        return rank + " of " + suit;
    }

    public boolean equals(Object obj)
    {
        CardDemo c = (CardDemo)obj;

        if(rank.equals(c.rank) && suit.equals(c.suit))
            return true;
        else
            return false;
    }
}
public class Card
{
    public static void main(String[] args)
    {
        CardDemo[] cards = new CardDemo[5];

        cards[0] = new CardDemo("Ace", "Spades");
        cards[1] = new CardDemo("King", "Hearts");
        cards[2] = new CardDemo("Queen", "Clubs");
        cards[3] = new CardDemo("Ace", "Spades");   // Duplicate
        cards[4] = new CardDemo("10", "Diamonds");

        boolean found = false;

        for(int i = 0; i < cards.length; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(cards[i].equals(cards[j]))
                {
                    System.out.println("Duplicate found: " + cards[i]);
                    found = true;
                    break;
                }
            }

            if(found)
                break;
        }
    }
}
