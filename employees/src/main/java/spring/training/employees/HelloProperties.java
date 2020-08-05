package spring.training.employees;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

//@ConfigurationProperties(prefix = "employees.hello")
@Data
//@Validated
public class HelloProperties {

//    @NotNull
    private String messagePrefix;

    private String messagePostfix;

}
