import java.util.Random;

public class RewardGenerator
{
    private Random random;

    private String[] normalRewards;
    private String[] streakRewards;
    private String[] bigRewards;

    public RewardGenerator()
    {
        random = new Random();

        normalRewards = new String[] {
            "Nice job! Keep going.",
            "Task complete. You are making progress.",
            "You did it. Future you is grateful.",
            "Small win unlocked."
        };

        streakRewards = new String[] {
            "Streak bonus: you are on a roll!",
            "Consistency looks good on you.",
            "That streak is doing work.",
            "Another one done. Keep the chain alive."
        };

        bigRewards = new String[] {
            "BIG REWARD: You deserve a break.",
            "BIG REWARD: You are officially crushing it.",
            "BIG REWARD: That was strong work.",
            "BIG REWARD: Productivity level increased."
        };
    }

    public String getReward(int streak)
    {
        if (streak >= 5)
        {
            return bigRewards[random.nextInt(bigRewards.length)];
        }
        else if (streak >= 2)
        {
            return streakRewards[random.nextInt(streakRewards.length)];
        }
        else
        {
            return normalRewards[random.nextInt(normalRewards.length)];
        }
    }

    public void displayReward(int streak)
    {
        System.out.println("\n*******************************");
        System.out.println("        TASK COMPLETED         ");
        System.out.println("*******************************");
        System.out.println(getReward(streak));
        System.out.println("*******************************\n");
    }
}
