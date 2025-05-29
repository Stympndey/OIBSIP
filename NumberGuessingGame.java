import javax.swing.*;
import java.util.Random;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 7;
    private static final int MAX_ROUNDS = 3;
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        int totalScore = 0;

        JOptionPane.showMessageDialog(null,
                "Welcome to the Number Guessing Game!\n" +
                "Guess the number between 1 and " + MAX_NUMBER + ".\n" +
                "You have " + MAX_ATTEMPTS + " attempts per round.\n" +
                "There are " + MAX_ROUNDS + " rounds. Good luck!",
                "Number Guessing Game", JOptionPane.INFORMATION_MESSAGE);

        for (int round = 1; round <= MAX_ROUNDS; round++) {
            int targetNumber = generateRandomNumber(1, MAX_NUMBER);
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            while (attemptsUsed < MAX_ATTEMPTS) {
                String input = JOptionPane.showInputDialog(null,
                        "Round " + round + " - Attempt " + (attemptsUsed + 1) + " of " + MAX_ATTEMPTS +
                                "\nEnter your guess (1-" + MAX_NUMBER + "):",
                        "Number Guessing Game", JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to quit the game?",
                            "Confirm Quit", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        showFinalScore(totalScore);
                        System.exit(0);
                    } else {
                        continue;
                    }
                }

                int guess;
                try {
                    guess = Integer.parseInt(input);
                    if (guess < 1 || guess > MAX_NUMBER) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid number between 1 and " + MAX_NUMBER + ".",
                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input. Please enter a numeric value.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                attemptsUsed++;

                if (guess == targetNumber) {
                    guessedCorrectly = true;
                    int pointsEarned = MAX_ATTEMPTS - attemptsUsed + 1;
                    totalScore += pointsEarned;
                    JOptionPane.showMessageDialog(null,
                            "Congratulations! You guessed the correct number: " + targetNumber + "\n" +
                                    "You earned " + pointsEarned + " points this round.",
                            "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else if (guess < targetNumber) {
                    JOptionPane.showMessageDialog(null, "Your guess is lower than the number. Try higher.",
                            "Try Again", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Your guess is higher than the number. Try lower.",
                            "Try Again", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null,
                        "Sorry! You did not guess the number this round.\nThe correct number was: " + targetNumber,
                        "Round Over", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        showFinalScore(totalScore);
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static void showFinalScore(int score) {
        JOptionPane.showMessageDialog(null,
                "Game Over!\nYour total score after " + MAX_ROUNDS + " rounds is: " + score + " points.\nThank you for playing!",
                "Final Score", JOptionPane.INFORMATION_MESSAGE);
    }
}

