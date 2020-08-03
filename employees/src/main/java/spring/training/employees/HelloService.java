package spring.training.employees;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

    public String sayHello(String name) {
        return String.format("Hello %s! (%s)", name, LocalDateTime.now());
    }
}
