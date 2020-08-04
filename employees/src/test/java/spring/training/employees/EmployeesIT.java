package spring.training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeesIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void testListEmployee() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(jsonPath("$[1].name", equalTo("Jack Doe")));
    }

    @Test
    void testListEmployeeWithRestTemplate() {
        //List<EmployeeDto> employees = restTemplate.getForObject("/api/employees", List.class);
        List<EmployeeDto> employees = restTemplate.exchange("/api/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {
                }).getBody();

        assertThat(employees)
                .extracting(EmployeeDto::getName)
                .isEqualTo(List.of("John Doe", "Jack Doe"));
    }
}
