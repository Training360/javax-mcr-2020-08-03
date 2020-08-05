package spring.training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

//    private HelloProperties helloProperties;
//
//    public HelloService(HelloProperties helloProperties) {
//        this.helloProperties = helloProperties;
//    }

    private String messagePrefix;

    private String messagePostfix;

    public HelloService(@Value("${employees.hello.message-prefix}") String messagePrefix, @Value("${employees.hello.message-postfix}") String messagePostfix) {
        this.messagePrefix = messagePrefix;
        this.messagePostfix = messagePostfix;
    }

    public String sayHello(String name) {
//        return String.format(helloProperties.getMessagePrefix() + " %s! (%s)" + helloProperties.getMessagePostfix(), name, LocalDateTime.now());
        return String.format(messagePrefix + " %s! (%s)" + messagePostfix, name, LocalDateTime.now());
    }
}
