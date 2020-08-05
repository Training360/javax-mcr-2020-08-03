package employees;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    Employee commandToEmployee(CreateEmployeeCommand command);

}
