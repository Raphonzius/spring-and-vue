package io.github.raphonzius.backend_spring.infrastructure.scheduler;

import io.github.raphonzius.backend_spring.infrastructure.service.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduledTasks {

    private final AlertService alertService;

    @Scheduled(cron = "@hourly")
    void sendPendingTodoAlerts() {
        try {
            alertService.sendAlert();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}