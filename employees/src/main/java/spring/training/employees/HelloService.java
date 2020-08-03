package spring.training.employees;

import java.time.LocalDateTime;

public class HelloService {

    public String sayHello(String name) {
        return String.format("Hello %s! (%s)", name, LocalDateTime.now());
    }
}
