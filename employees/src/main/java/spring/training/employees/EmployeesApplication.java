package spring.training.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableConfigurationProperties(HelloProperties.class)
public class EmployeesApplication {

//	@Bean
//	public HelloService helloService() {
//		return new HelloService();
//	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.findAndRegisterModules();
	}

	@Bean
	public InMemoryHttpTraceRepository traceRepository() {
		return new InMemoryHttpTraceRepository();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

}
