package spring.training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@Slf4j
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping
    public List<EmployeeDto> listEmployees() {
        log.info("List employees");
        return employeesService.listEmployees();
    }
}
