package spring.training.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventStoreJmsGateway {

    private JmsTemplate jmsTemplate;

    public EventStoreJmsGateway(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        // {"message": "Hello World!"}
        log.info("Send message: " + message);
        jmsTemplate.convertAndSend("eventsQueue", new Request(message));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String message;
    }
}
