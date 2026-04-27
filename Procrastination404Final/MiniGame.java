import java.util.Random;
import java.util.Scanner;

public class MiniGame
{
    private Random random;
    private Scanner scanner;

    public MiniGame(Scanner input)
    {
        random = new Random();
        scanner = input;
    }

    public boolean play()
    {
        int choice = random.nextInt(3);
        if (choice == 0)
        {
            return playMathQuiz();
        }
        else if (choice == 1)
        {
            return playTypingChallenge();
        }
        else
        {
            return playCountdown();
        }
    }

    public boolean playMathQuiz()
    {
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        int answer = a * b;

        System.out.println("\n[MINI-GAME] MATH QUIZ");
        System.out.println("Solve this to prove your brain still works:");
        System.out.println("    " + a + " x " + b + " = ?");
        System.out.print("Your answer: ");

        try
        {
            int userAnswer = Integer.parseInt(scanner.nextLine().trim());

            if (userAnswer == answer)
            {
                System.out.println("Correct! You may proceed... this time.");
                return true;
            }
            else
            {
                System.out.println("Wrong! The answer was " + answer + ". Now go do your task.");
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("That's not even a number. Task first, jokes later.");
            return false;
        }
    }

    public boolean playTypingChallenge()
    {
        String[] phrases = {
            "I will stop procrastinating",
            "My tasks are not going to complete themselves",
            "Focus is a superpower",
            "Done is better than perfect"
        };

        String phrase = phrases[random.nextInt(phrases.length)];

        System.out.println("\n[MINI-GAME] TYPING CHALLENGE");
        System.out.println("Type the following phrase exactly:");
        System.out.println("    \"" + phrase + "\"");
        System.out.print("Your input: ");

        String input = scanner.nextLine().trim();

        if (input.equals(phrase))
        {
            System.out.println("Nice typing! Now apply that energy to your task.");
            return true;
        }
        else
        {
            System.out.println("Not quite. Close enough — but your task isn't.");
            return false;
        }
    }

    public boolean playCountdown()
    {
        System.out.println("\n[MINI-GAME] 5-SECOND COMMITMENT");
        System.out.println("Type 'START' within 5 seconds to commit to your task.");
        System.out.print("GO: ");

        long start = System.currentTimeMillis();
        String input = scanner.nextLine().trim();
        long elapsed = System.currentTimeMillis() - start;

        if (input.equalsIgnoreCase("START") && elapsed <= 5000)
        {
            System.out.println("LET'S GO! Task time starts NOW.");
            return true;
        }
        else
        {
            System.out.println("Too slow (or too stubborn). The task is still waiting.");
            return false;
        }
    }
}
