package academy.learnprogramming.resource;

import academy.learnprogramming.entities.Employee;
import academy.learnprogramming.service.PersistenceService;
import academy.learnprogramming.service.QueryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;

@Path("employees") //api/v1/employees/*
@Produces("application/json")
@Consumes("application/json")
public class EmployeeResource {


    @Inject
    QueryService queryService;

    @Inject
    PersistenceService persistenceService;

//    GET /api/v1/employees/employees HTTP/1.1
//    Host www.ourdomain.com
//    User-Agent: Java/1.8.0_151
//    Content-Type: text/plain;charset=utf-8
//    Accept: application/json

    @GET //api/v1/employees GET Request
    @Path("employees") //api/v1/employees/employees
//    @Produces("application/xml")
    public Collection<Employee> getEmployees() {

//        Collection<Employee> employees = new ArrayList<>();
//
//        Employee employee = new Employee();
//        employee.setFullName("John Mahama");
//        employee.setSocialSecurityNumber("SSF12343");
//        employee.setDateOfBirth(LocalDate.of(1986, Month.APRIL, 10));
//        employee.setBasicSalary(new BigDecimal(60909));
//        employee.setHiredDate(LocalDate.of(2018, Month.JANUARY, 24));
//
//
//        Employee employee1 = new Employee();
//        employee1.setFullName("Donald Trump");
//        employee1.setSocialSecurityNumber("SKJBHJSBDKJ");
//        employee1.setDateOfBirth(LocalDate.of(1900, Month.JULY, 31));
//        employee1.setBasicSalary(new BigDecimal(250000));
//        employee1.setHiredDate(LocalDate.of(2016, Month.NOVEMBER, 7));
//
//        employees.add(employee);
//        employees.add(employee1);


       return queryService.getEmployees();
//        return employees;
    }

    @GET
    @Path("employee/{id: ^[0-9]+$}") //api/v1/employees/employee/1  GET Method {username: }@{domain: }.{company}
    public Employee getEmployeeById(@PathParam("id") @DefaultValue("0") Long id) {

        return queryService.findEmployeeById(id);
    }

    @GET
    @Path("id") //?id=27 /id?id=95
    public Employee findEmployeeById(@QueryParam("id") @DefaultValue("0") Long id) {
        return queryService.findEmployeeById(id);

    }


    @POST //api/v1/employees POST Request
    @Path("new") //api/v1/employees/new - POST Request
//    @Consumes("application/xml")
    public void createEmployee(Employee employee) {
        persistenceService.saveEmployee(employee );
    }
}
