package tesis.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduleTask {
    @Autowired
    BulkEmailSender sender;
    //sec, min, hours, day, month, dayOfWeek.
    @Scheduled(cron = "0 18 17 * * *",zone = "GMT-3")
    public void reportCurrentTime() {
        sender.send();
    }

}
