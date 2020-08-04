package spring.training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@Slf4j
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        log.info("List employees: {}", prefix);
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable long id) {
        return employeesService.findEmployeeById(id);
    }
}
