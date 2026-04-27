import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReminderEngine
{
    private static final int REMINDER_LEAD_MINUTES = 30;

    private User user;
    private PunishmentGenerator punishmentGenerator;

    public ReminderEngine(User theUser, PunishmentGenerator p)
    {
        user = theUser;
        punishmentGenerator = p;
    }

    public void checkTasks()
    {
        ArrayList<Task> tasks = user.getTasks();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = tasks.get(i);

            if (task.getStatus().equals(Task.PENDING))
            {
                long minutesUntilDue = Duration.between(now, task.getDeadline()).toMinutes();

                if (minutesUntilDue < 0)
                {
                    task.markMissed();
                    user.recordCompletion(Task.MISSED);
                    System.out.println("\n[REMINDER ENGINE] Task MISSED: \"" + task.getName() + "\"");
                    punishmentGenerator.displayPunishment(task.getIgnoredReminderCount());
                }
                else if (minutesUntilDue <= REMINDER_LEAD_MINUTES)
                {
                    sendReminder(task, minutesUntilDue);
                }
            }
        }
    }

    public void sendReminder(Task task, long minutesLeft)
    {
        System.out.println("\n[REMINDER] \"" + task.getName() + "\" is due in "
            + minutesLeft + " minute(s)!");
        System.out.println("    Mark it complete from the main menu when done.");
    }

    public void handleIgnoredReminder(Task task)
    {
        task.incrementIgnoredReminders();
        user.recordIgnoredReminder();

        System.out.println("You ignored the reminder for: \"" + task.getName() + "\"");
        punishmentGenerator.displayPunishment(task.getIgnoredReminderCount());
    }

    public void printStatus()
    {
        ArrayList<Task> tasks = user.getTasks();

        if (tasks.size() == 0)
        {
            System.out.println("No tasks to check.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("\n--- Reminder Check ---");

        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = tasks.get(i);

            if (task.getStatus().equals(Task.PENDING))
            {
                long mins = Duration.between(now, task.getDeadline()).toMinutes();

                if (mins < 0)
                {
                    System.out.println("  OVERDUE: " + task.getName());
                }
                else if (mins <= REMINDER_LEAD_MINUTES)
                {
                    System.out.println("  DUE SOON (" + mins + " min): " + task.getName());
                }
            }
        }
        System.out.println("----------------------\n");
    }
}
