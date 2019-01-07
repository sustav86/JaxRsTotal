package academy.learnprogramming.config;

import academy.learnprogramming.entities.Employee;

import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.Period;

public class EmployeeListener {


    @PrePersist
    public void calculateEmployeeAge(Employee employee) {
        employee.setAge(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears());
    }
}
