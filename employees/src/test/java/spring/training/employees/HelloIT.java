package spring.training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloIT {

    @Autowired
    HelloController helloController;

    @Test
    void testSayHello() {
        var message = helloController.sayHello("John Doe");
        assertThat(message).startsWith("Hello John Doe");
    }

    @Test
    void testSayHelloJack() {
        var message = helloController.sayHello("Jack Doe");
        assertThat(message).startsWith("Hello Jack Doe");
    }

}
