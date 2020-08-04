package spring.training.employees;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(idGenerator.incrementAndGet(), "John Doe"),
            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));

    private ModelMapper modelMapper;

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().startsWith(prefix.get()))
                .map(this::mapToDto).collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(Employee e) {
        return modelMapper.map(e, EmployeeDto.class);
    }

    public EmployeeDto findEmployeeById(long id) {
        return employees.stream().filter(e -> e.getId() == id).map(this::mapToDto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }
}
