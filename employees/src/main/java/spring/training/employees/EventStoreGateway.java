package spring.training.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventStoreGateway {

    private String eventStoreUrl;

    private RestTemplate restTemplate;

    public EventStoreGateway(@Value("${event-store.url}") String eventStoreUrl,
                             RestTemplateBuilder builder) {
        this.eventStoreUrl = eventStoreUrl;
        restTemplate = builder.build();
    }

    public void sendEvent(String message) {
        restTemplate.postForObject(eventStoreUrl + "/api/events",
                new Request(message),
                String.class);
    }

    @Data
    @AllArgsConstructor
    private static class Request {
        private String message;
    }
}
