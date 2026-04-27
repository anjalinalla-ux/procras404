import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task
{
    public static final String PENDING = "PENDING";
    public static final String COMPLETED = "COMPLETED";
    public static final String LATE = "LATE";
    public static final String MISSED = "MISSED";

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");

    private String name;
    private LocalDateTime deadline;
    private String status;
    private LocalDateTime completionTime;
    private int ignoredReminderCount;

    public Task(String taskName, LocalDateTime deadlineTime)
    {
        name = taskName;
        deadline = deadlineTime;
        status = PENDING;
        completionTime = null;
        ignoredReminderCount = 0;
    }

    public void markCompleted()
    {
        completionTime = LocalDateTime.now();

        if (completionTime.isAfter(deadline))
        {
            status = LATE;
        }
        else
        {
            status = COMPLETED;
        }
    }

    public void markMissed()
    {
        status = MISSED;
    }

    public boolean isOverdue()
    {
        return status.equals(PENDING) && LocalDateTime.now().isAfter(deadline);
    }

    public void incrementIgnoredReminders()
    {
        ignoredReminderCount++;
    }

    public String getName()
    {
        return name;
    }

    public LocalDateTime getDeadline()
    {
        return deadline;
    }

    public String getStatus()
    {
        return status;
    }

    public int getIgnoredReminderCount()
    {
        return ignoredReminderCount;
    }

    public String toString()
    {
        String deadlineStr = deadline.format(FORMATTER);
        String statusLabel;

        if (status.equals(PENDING))
        {
            if (isOverdue())
            {
                statusLabel = "[OVERDUE]";
            }
            else
            {
                statusLabel = "[PENDING]";
            }
        }
        else if (status.equals(COMPLETED))
        {
            statusLabel = "[DONE]   ";
        }
        else if (status.equals(LATE))
        {
            statusLabel = "[LATE]   ";
        }
        else
        {
            statusLabel = "[MISSED] ";
        }

        if (ignoredReminderCount > 0)
        {
            return statusLabel + " " + name + " | Due: " + deadlineStr
                + " | Ignored reminders: " + ignoredReminderCount;
        }
        else
        {
            return statusLabel + " " + name + " | Due: " + deadlineStr;
        }
    }
}
