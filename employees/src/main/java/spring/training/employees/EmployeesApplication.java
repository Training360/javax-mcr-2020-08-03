package spring.training.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

	@Bean
	public HelloService helloService() {
		return new HelloService();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

}
