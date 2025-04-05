import java.util.Random;
import java.util.Scanner;

public class DiceGame extends Game {

    private int[] scores;
    private String[] playerNames;
    private int numberOfPlayers;
    private int winningScore = 20;
    private int winner = -1;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initializeGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        scores = new int[numberOfPlayers];
        playerNames = new String[numberOfPlayers];

        System.out.println("🎲 Welcome to the Dice Game!");
        System.out.println("First player to reach " + winningScore + " points wins!");

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            playerNames[i] = scanner.nextLine();
        }
    }

    @Override
    public boolean endOfGame() {
        return winner != -1;
    }

    @Override
    public void playSingleTurn(int player) {
        System.out.println("\n" + playerNames[player] + "'s turn. Press [Enter] to roll the dice...");
        scanner.nextLine();

        int roll = random.nextInt(6) + 1;
        scores[player] += roll;

        System.out.println(playerNames[player] + " rolled a " + roll + " 🎲. Total score: " + scores[player]);

        if (scores[player] >= winningScore) {
            winner = player;
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("\n " + playerNames[winner] + " wins the game with " + scores[winner] + " points!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.print("Enter number of players: ");
            int players = Integer.parseInt(scanner.nextLine());

            Game game = new DiceGame();
            game.play(players);

            System.out.print("\nWould you like to play again? (yes/no): ");
            String input = scanner.nextLine().toLowerCase();
            playAgain = input.startsWith("y");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
