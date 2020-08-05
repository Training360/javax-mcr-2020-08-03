package employees;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EmployeeBean {

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    EmployeeMapper employeeMapper;

    public List<EmployeeDto> listEmployees() {
        var employees = employeeMapper.employeesToDtos(employeeRepository.listAll());
        System.out.println("List employees: " + employees);
        return employees;
    }

    @Transactional
    public Employee createEmployee(CreateEmployeeCommand command) {
        System.out.println("Create employee: " + command);
        var employee = employeeMapper.commandToEmployee(command);
        employeeRepository.persist(employee);
        return employee;
    }

}
