package spring.training.employees;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@Slf4j
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping
    @Operation(summary = "list employees", description = "List employees based on prefix")
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        log.info("List employees: {}", prefix);
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    // TODO mi van, ha az id null, nem adja meg?
    public EmployeeDto findEmployeeById(@PathVariable long id) {
        return employeesService.findEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody UpdateEmployeeCommand command) {
            return ResponseEntity.ok(employeesService.updateEmployee(id, command));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Problem> resolveException(NotFoundException nfe) {
        log.info("Not found");
        Problem problem = Problem.builder()
                .withType(URI.create("employees/not-found"))
                .withTitle("Not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(nfe.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException e) {
        List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withType(URI.create("employees/validation-error"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(e.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
