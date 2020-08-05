package spring.training.employees;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

//@ConfigurationProperties(prefix = "employees.hello")
//@Configuration
@Data
//@Validated
public class HelloConfig {

//    @NotNull
    private String messagePrefix;

    private String messagePostfix;

}
