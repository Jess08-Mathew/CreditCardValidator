import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlphabetWarGame {
    private final Map<Character, Integer> leftStrengths;
    private final Map<Character, Integer> rightStrengths;

    // Default constructor with predefined strengths
    public AlphabetWarGame() {
        leftStrengths = new HashMap<>();
        leftStrengths.put('w', 4);
        leftStrengths.put('p', 3);
        leftStrengths.put('b', 2);
        leftStrengths.put('s', 1);

        rightStrengths = new HashMap<>();
        rightStrengths.put('m', 4);
        rightStrengths.put('q', 3);
        rightStrengths.put('d', 2);
        rightStrengths.put('z', 1);
    }

    // Overloaded constructor that allows custom strengths
    public AlphabetWarGame(Map<Character, Integer> left, Map<Character, Integer> right) {
        this.leftStrengths = left;
        this.rightStrengths = right;
    }

    // Method to determine the winner based on a single combined word
    public String alphabetWar(String word) {
        return alphabetWar(word, ""); // Call the two-word method with the right side as empty
    }

    // Method to determine the winner based on separate left and right words
    public String alphabetWar(String leftWord, String rightWord) {
        int leftScore = calculateScore(leftWord, leftStrengths);
        int rightScore = calculateScore(rightWord, rightStrengths);

        return decideWinner(leftScore, rightScore);
    }

    // Helper method to calculate score based on the given strengths
    private int calculateScore(String word, Map<Character, Integer> strengths) {
        int score = 0;
        for (char letter : word.toCharArray()) {
            score += strengths.getOrDefault(letter, 0); // Default to 0 if letter not found
        }
        return score;
    }

    // Helper method to decide the winner
    private String decideWinner(int leftScore, int rightScore) {
        if (leftScore > rightScore) {
            return "Left side wins!";
        } else if (rightScore > leftScore) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    // Method to play the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            System.out.print("Enter letters for the left side: ");
            String leftWord = scanner.nextLine();

            System.out.print("Enter letters for the right side: ");
            String rightWord = scanner.nextLine();

            String result = alphabetWar(leftWord, rightWord); // Use method with separate words
            System.out.println(result);

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine();
        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Method to run test cases
    public void runTestCases() {
        System.out.println("Running Test Cases...");

        System.out.println("Test Case 1: " + alphabetWar("z")); // Expected: Right side wins!
        System.out.println("Test Case 2: " + alphabetWar("zdqmwpbs")); // Expected: Let's fight again!
        System.out.println("Test Case 3: " + alphabetWar("wwwwwwz")); // Expected: Left side wins!
    }

    // Main method to start the game
    public static void main(String[] args) {
        AlphabetWarGame game = new AlphabetWarGame(); // Default constructor
        game.runTestCases(); // Run test cases
        game.playGame(); // Start the interactive game
    }
}



