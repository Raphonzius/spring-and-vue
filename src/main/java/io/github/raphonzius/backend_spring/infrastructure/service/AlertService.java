package io.github.raphonzius.backend_spring.infrastructure.service;

import com.google.gson.JsonObject;
import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import io.github.raphonzius.backend_spring.core.model.entity.UserWhatsapp;
import io.github.raphonzius.backend_spring.infrastructure.repository.SystemConfigRepository;
import io.github.raphonzius.backend_spring.infrastructure.repository.TodoRepository;
import io.github.raphonzius.backend_spring.infrastructure.repository.UserWhatsappRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlertService {

    private final TodoRepository todoRepository;
    private final SystemConfigRepository systemConfigRepository;
    private final UserWhatsappRepository userWhatsappRepository;

    public void sendAlert() {

        List<Todo> todoList = todoRepository.findByDoneFalseAndDueDateBetween(
                Date.from(
                        LocalDate.now()
                                .atStartOfDay()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                ),
                Date.from(
                        LocalDate.now()
                                .plusDays(7)
                                .atTime(23, 59, 59)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                )
        );

        if (todoList != null) {
            for (Todo todo : todoList) {
                UserWhatsapp whatsappData = userWhatsappRepository.findByUser_LoginIgnoreCase(todo.getUser().getLogin());

                JsonObject body = new JsonObject();
                body.addProperty("apiKey", whatsappData.getApiKey());
                body.addProperty("phone", whatsappData.getPhone());
                body.addProperty("msg", getWppMessage(todo));

                new RestTemplate().postForEntity(
                        getWppServiceUrl(),
                        new HttpEntity<>(body.toString()),
                        String.class
                );
            }
        }
    }

    private String getWppServiceUrl() {
        return systemConfigRepository.findByCfgParam("WPP_LAMBDA_URL").getCfgValue();
    }

    private String getWppMessage(Todo todo) {
        return getPendingMessage(todo.getUser().getLogin(), calcDaysTodo(todo.getDueDate()), todo.getSubject(), todo.getDescription());
    }

    private long calcDaysTodo(Date todoDueDate) {
        return Duration.between(
                LocalDate.now()
                        .atStartOfDay(),
                todoDueDate
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .atStartOfDay()
        ).toDays();
    }

    private String getPendingMessage(String login, long days, String subj, String desc) {
        return String.format("%s,\nVocê tem o seguinte TODO pendente para os proximos *%s dias.*\n\nTODO: *%s*\n\nDescrição: %s", login, days, subj, desc);
    }

}
