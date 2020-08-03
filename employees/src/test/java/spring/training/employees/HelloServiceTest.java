package spring.training.employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        // Given
        var helloService = new HelloService();
        // When
        var message = helloService.sayHello("John Doe");
        // Then
//        assertTrue(message.startsWith("Hello John Doe!"));
        assertThat(message).startsWith("Hello John Doe!");
    }
}
