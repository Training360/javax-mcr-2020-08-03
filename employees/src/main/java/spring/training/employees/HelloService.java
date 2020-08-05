package spring.training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

//    private HelloConfig helloConfig;
//
//    public HelloService(HelloConfig helloConfig) {
//        this.helloConfig = helloConfig;
//    }

    private Environment environment;

    public HelloService(Environment environment) {
        this.environment = environment;
    }

    public String sayHello(String name) {
//        return String.format(helloConfig.getMessagePrefix() + " %s! (%s)" + helloConfig.getMessagePostfix(), name, LocalDateTime.now());
        return String.format(environment.getProperty("employees.hello.messagePrefix") + " %s! (%s)" + environment.getProperty("employees.hello.messagePostfix"), name, LocalDateTime.now());
    }
}
