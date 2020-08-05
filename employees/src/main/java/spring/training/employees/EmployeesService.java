package spring.training.employees;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

//    private AtomicLong idGenerator = new AtomicLong();
//
//    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
//            new Employee(idGenerator.incrementAndGet(), "John Doe"),
//            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
//    )));

    private EmployeesRepository employeesRepository;

    private ModelMapper modelMapper;

    private EventStoreGateway eventStoreGateway;

    public EmployeesService(EmployeesRepository employeesRepository, ModelMapper modelMapper, EventStoreGateway eventStoreGateway) {
        this.employeesRepository = employeesRepository;
        this.modelMapper = modelMapper;
        this.eventStoreGateway = eventStoreGateway;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
//        return employees.stream()
//                .filter(e -> prefix.isEmpty() || e.getName().startsWith(prefix.get()))
//                .map(this::mapToDto).collect(Collectors.toList());
        List<Employee> employees;
        if (prefix.isEmpty()) {
            employees = employeesRepository.findAll();

        }
        else {
            employees = employeesRepository.findByPrefix(prefix.get() + "%");
        }
        return employees.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(Employee e) {
        return modelMapper.map(e, EmployeeDto.class);
    }

    public EmployeeDto findEmployeeById(long id) {
//        return employees.stream().filter(e -> e.getId() == id).map(this::mapToDto)
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        return mapToDto(employeesRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
//        var employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        // employees.add(employee);
        var employee = new Employee(command.getName());
        employeesRepository.save(employee);
        eventStoreGateway.sendEvent("Employee has created: " + employee.getName());
        return mapToDto(employee);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
//        var employee = employees.stream().filter(e -> e.getId() == id).findFirst()
//                .orElseThrow(() -> new NotFoundException("Employee not found: " + id));

        var employee = employeesRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found: " + id));
        employee.setName(command.getName());
//        employeesRepository.save(employee);
        return mapToDto(employee);
    }
}
