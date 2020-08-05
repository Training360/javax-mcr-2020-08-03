package spring.training.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateEmployeeCommand {

    @Schema(description = "the name of the employee", example = "Jack Example Doe")
    @NotBlank(message = "the name of the employee can not be blank")
    private String name;

}
