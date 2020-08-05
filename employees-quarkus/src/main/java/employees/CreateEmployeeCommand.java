package employees;

import lombok.Data;

import javax.persistence.Embedded;

@Data
public class CreateEmployeeCommand {

    private String name;
}
