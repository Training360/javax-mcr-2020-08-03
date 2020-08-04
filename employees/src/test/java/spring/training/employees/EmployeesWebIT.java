package spring.training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class EmployeesWebIT {

    @MockBean
    private EmployeesService employeesService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testListEmployees() throws Exception {
        when(employeesService.listEmployees(any())).thenReturn(List.of(
                new EmployeeDto(1L, "Jack Smith"),
                new EmployeeDto(2L, "John Smith")
        ));

        mockMvc.perform(get("/api/employees"))
                .andDo(print());
    }

}
