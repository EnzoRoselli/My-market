package tesis.subscription;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduleTask {
    //sec, min, hours, day, month, dayOfWeek.
    @Scheduled(cron = "0 0 13 * * *",zone = "GMT-3")
    public static void reportCurrentTime() {
        BulkEmailSender.send();
    }

}
