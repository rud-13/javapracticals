import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

    static int winner(Move a, Move b) {
        if (a == b) return 0;
        boolean aWins = switch (a) {
            case ROCK -> b == Move.SCISSORS || b == Move.LIZARD;
            case PAPER -> b == Move.ROCK || b == Move.SPOCK;
            case SCISSORS -> b == Move.PAPER || b == Move.LIZARD;
            case LIZARD -> b == Move.SPOCK || b == Move.PAPER;
            case SPOCK -> b == Move.SCISSORS || b == Move.ROCK;
        };
        return aWins ? 1 : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int playerScore = 0, computerScore = 0;

        for (int round = 1; round <= 5; round++) {
            System.out.print("Round " + round +
                    " - Enter ROCK, PAPER, SCISSORS, LIZARD or SPOCK: ");
            Move player = Move.valueOf(sc.next().toUpperCase());
            Move computer = Move.values()[random.nextInt(Move.values().length)];
            int result = winner(player, computer);

            System.out.println("You: " + player + " | Computer: " + computer);
            if (result == 1) {
                playerScore++;
                System.out.println("You win this round.");
            } else if (result == -1) {
                computerScore++;
                System.out.println("Computer wins this round.");
            } else {
                System.out.println("Round is a tie.");
            }
        }

        System.out.println("Final score: You " + playerScore +
                " - " + computerScore + " Computer");
        if (playerScore > computerScore) System.out.println("Overall winner: You");
        else if (computerScore > playerScore) System.out.println("Overall winner: Computer");
        else System.out.println("Overall result: Tie");
        sc.close();
    }
}
