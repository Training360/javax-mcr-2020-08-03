package spring.training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeesRepositoryIT {

    @Autowired
    EmployeesRepository repository;

    @Test
    void testFindByPrefix() {
        repository.save(new Employee("Jack Doe"));
        repository.save(new Employee("John Doe"));

        var employees = repository.findByPrefix("jack%");
        assertThat(employees).extracting(Employee::getName)
                .isEqualTo(List.of("Jack Doe"));
    }
}
