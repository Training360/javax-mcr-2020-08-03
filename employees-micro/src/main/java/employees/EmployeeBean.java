package employees;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Slf4j
public class EmployeeBean {

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> listEmployees() {
        var employees = employeeMapper.employeesToDtos(employeeRepository.findAll());
        System.out.println("List employees: " + employees);
        return employees;
    }

    @Transactional
    public Employee createEmployee(CreateEmployeeCommand command) {
        log.info("Create employee: " + command);
        var employee = employeeMapper.commandToEmployee(command);
        var created = employeeRepository.save(employee);
        return created;
    }

}
