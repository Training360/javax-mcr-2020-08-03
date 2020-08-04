package spring.training.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateEmployeeCommand {

    @Schema(description = "the name of the employee", example = "Jack Example Doe")
    private String name;
}
