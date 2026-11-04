
import java.util.*;

public class RPSLS
{
    enum Move
    {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }

    public static int winner(Move a, Move b)
    {
        if(a == b)
            return 0;

        // Check which move wins against the other move
        return switch(a)
        {
            case ROCK ->
                (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;

            case PAPER ->
                (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;

            case SCISSORS ->
                (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;

            case LIZARD ->
                (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;

            case SPOCK ->
                (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        Move[] moves = Move.values();

        int playerScore = 0;
        int computerScore = 0;

        System.out.println("=== Rock Paper Scissors Lizard Spock ===");

        // Play the game for 5 rounds
        for(int round = 1; round <= 5; round++)
        {
            System.out.println("\nRound " + round);

            System.out.println("Enter Move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK):");

            Move playerMove = Move.valueOf(sc.next().toUpperCase());

            // Select a random move for computer
            Move computerMove = moves[rand.nextInt(moves.length)];

            System.out.println("Player Move   : " + playerMove);
            System.out.println("Computer Move : " + computerMove);

            int result = winner(playerMove, computerMove);

            // Update score according to round result
            if(result == 1)
            {
                System.out.println("You Win This Round!");
                playerScore++;
            }
            else if(result == -1)
            {
                System.out.println("Computer Wins This Round!");
                computerScore++;
            }
            else
            {
                System.out.println("Round Tied!");
            }
        }

        System.out.println("\nYour Score     : " + playerScore);
        System.out.println("Computer Score : " + computerScore);

        // Display the final match winner
        if(playerScore > computerScore)
        {
            System.out.println("You Win " + playerScore + "-" + computerScore);
        }
        else if(computerScore > playerScore)
        {
            System.out.println("Computer Wins " + computerScore + "-" + playerScore);
        }
        else
        {
            System.out.println("Match Draw!");
        }

        sc.close();
    }
}
