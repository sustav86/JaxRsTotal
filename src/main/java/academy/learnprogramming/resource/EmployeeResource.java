package academy.learnprogramming.resource;

import academy.learnprogramming.entities.Employee;
import academy.learnprogramming.service.PersistenceService;
import academy.learnprogramming.service.QueryService;
import sun.management.Agent;
import sun.net.www.content.text.plain;

import javax.inject.Inject;
import javax.persistence.spi.PersistenceProvider;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.registry.infomodel.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("employees") //api/v1/employees/*
@Produces("application/json")
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
    @Path("employee") //api/v1/employees/employee  GET Method
    public Employee getEmployeeById(Long id) {
        return queryService.findEmployeeById(id);
    }


    @POST //api/v1/employees POST Request
    @Path("new") //api/v1/employees/new - POST Request
    public void createEmployee(Employee employee) {
        persistenceService.saveEmployee(employee );
    }
}
