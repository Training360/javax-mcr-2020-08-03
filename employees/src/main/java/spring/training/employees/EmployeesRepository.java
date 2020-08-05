package spring.training.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where lower(e.name) like lower(:prefix)")
    List<Employee> findByPrefix(String prefix);
}
