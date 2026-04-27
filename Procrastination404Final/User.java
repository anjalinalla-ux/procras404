import java.util.ArrayList;

public class User
{
    private String name;
    private ArrayList<Task> tasks;
    private int streak;
    private int totalCompleted;
    private int totalMissed;
    private int totalLate;
    private int totalIgnoredReminders;

    public User(String userName)
    {
        name = userName;
        tasks = new ArrayList<Task>();
        streak = 0;
        totalCompleted = 0;
        totalMissed = 0;
        totalLate = 0;
        totalIgnoredReminders = 0;
    }

    public boolean addTask(Task task)
    {
        if (tasks.size() >= 5)
        {
            System.out.println("You can only track up to 5 tasks at a time!");
            return false;
        }
        tasks.add(task);
        return true;
    }

    public void removeTask(int index)
    {
        if (index >= 0 && index < tasks.size())
        {
            tasks.remove(index);
        }
    }

    public void recordCompletion(String status)
    {
        if (status.equals(Task.COMPLETED))
        {
            totalCompleted++;
            streak++;
        }
        else if (status.equals(Task.LATE))
        {
            totalLate++;
            streak = 0;
        }
        else if (status.equals(Task.MISSED))
        {
            totalMissed++;
            streak = 0;
        }
    }

    public void recordIgnoredReminder()
    {
        totalIgnoredReminders++;
    }

    public double getCompletionRate()
    {
        int total = totalCompleted + totalMissed + totalLate;
        if (total == 0)
        {
            return 0.0;
        }
        return (totalCompleted * 100.0) / total;
    }

    public void printStats()
    {
        System.out.println("\n===== YOUR STATS =====");
        System.out.println("Name: " + name);
        System.out.println("Current streak: " + streak + " day(s)");
        System.out.printf("Completion rate: %.1f%%%n", getCompletionRate());
        System.out.println("Completed on time: " + totalCompleted);
        System.out.println("Completed late: " + totalLate);
        System.out.println("Missed: " + totalMissed);
        System.out.println("Ignored reminders: " + totalIgnoredReminders);
        System.out.println("======================\n");
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public int getStreak()
    {
        return streak;
    }

    public int getTotalMissed()
    {
        return totalMissed;
    }

    public int getTotalIgnoredReminders()
    {
        return totalIgnoredReminders;
    }
}
