package spring.training.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @Mock
    HelloService service;

    @InjectMocks
    HelloController controller;

    @Test
    void testSayHello() {
//        var service = Mockito.mock(HelloService.class);
//        var controller = new HelloController(service);

        when(service.sayHello(anyString())).thenReturn("Hello John Doe");

        var message = controller.sayHello("John Doe");

        assertEquals("Hello John Doe", message);
    }
}
