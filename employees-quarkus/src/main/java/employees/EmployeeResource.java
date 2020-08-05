package employees;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeBean employeeBean;

    @GET
    public List<EmployeeDto> listEmployees() {
        return employeeBean.listEmployees();
    }

    @POST
    public Response createEmployee(CreateEmployeeCommand command) {
        var employee = employeeBean.createEmployee(command);
        return Response.status(201).entity(employee).build();
    }

}
