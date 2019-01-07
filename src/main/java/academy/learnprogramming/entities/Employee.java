/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academy.learnprogramming.entities;

import academy.learnprogramming.config.AbstractEntityListener;
import academy.learnprogramming.config.EmployeeListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author Seeraj
 */
@Entity


@NamedNativeQuery(name = "Employee.findAllNativeNamed", query = "select * from Employee", resultClass = Employee.class)

@NamedQuery(name = Employee.GET_EMPLOYEE_ALLOWANCES, query = "select al from Employee  e join e.employeeAllowances al where al.allowanceAmount > :greaterThanValue")
@NamedQuery(name = Employee.EMPLOYEE_SALARY_BOUND, query = "select e from Employee e where e.basicSalary between :lowerBound and :upperBound")
@NamedQuery(name = Employee.GET_EMPLOYEE_PHONE_NUMBERS, query = "select e.fullName, KEY(p), VALUE(p) from Employee e join e.employeePhoneNumbers p")
@NamedQuery(name = Employee.GET_EMPLOYEE_ALLOWANCES_JOIN_FETCH, query = "select e from Employee e join fetch e.employeeAllowances")
@NamedQuery(name = Employee.GET_ALL_PARKING_SPACES, query = "select e.parkingSpace from Employee e")
@NamedQuery(name = Employee.EMPLOYEE_PROJECTION, query = "select e.fullName, e.basicSalary from Employee e")
@NamedQuery(name = Employee.EMPLOYEE_CONSTRUCTOR_PROJ, query = "select new academy.learnprogramming.entities.EmployeeDetails(e.fullName, e.basicSalary, e.department.departmentName) from Employee  e")
@NamedQuery(name = Employee.FIND_BY_ID, query = "select e from Employee e where e.id = :id and e.userEmail = :email")
@NamedQuery(name = Employee.FIND_BY_NAME, query = "select e from Employee e where e.fullName = :name and e.userEmail = :email")
@NamedQuery(name = Employee.LIST_EMPLOYEES, query = "select  e from Employee e  order by e.fullName")
@NamedQuery(name = Employee.FIND_PAST_PAYSLIP_BY_ID,
        query = "select p from Employee e join e.pastPayslips p where e.id = :employeeId and e.userEmail =:email and p.id =:payslipId and p.userEmail = :email")
@NamedQuery(name = Employee.GET_PAST_PAYSLIPS, query = "select p from Employee e inner join e.pastPayslips p where e.id = :employeeId and e.userEmail=:email")
//@Table(name = "Employee", schema = "HR")
@JsonbPropertyOrder(PropertyOrderStrategy.REVERSE)
@EntityListeners({EmployeeListener.class, AbstractEntityListener.class})
public class Employee extends AbstractEntity{


//    @TableGenerator(name = "Emp_Gen", table = "ID_GEN",
//            pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE")
//    @GeneratedValue(generator = "Emp_Gen")
//    @Id
//    private Long id;

    public static final String EMPLOYEE_SALARY_BOUND = "EmployeeSalaryBound";
    public static final String EMPLOYEE_PROJECTION = "Employee.nameAndSalaryProjection";
    public static final String EMPLOYEE_CONSTRUCTOR_PROJ = "Employee.projection";
    public static final String GET_EMPLOYEE_ALLOWANCES = "Employee.getAllowances";
    public static final String GET_EMPLOYEE_ALLOWANCES_JOIN_FETCH = "Employee.getAllowancesJoinFetch";
    public static final String FIND_BY_ID = "Employee.findById";
    public static final String FIND_BY_NAME = "Employee.findByName";
    public static final String LIST_EMPLOYEES = "Employee.listEmployees";
    public static final String FIND_PAST_PAYSLIP_BY_ID = "Employee.findPastPayslipById";
    public static final String GET_PAST_PAYSLIPS = "Employee.getPastPayslips";
    public static final String GET_ALL_PARKING_SPACES = "Employee.getAllParkingSpaces";
    public static final String GET_EMPLOYEE_PHONE_NUMBERS = "Employee.getPhoneNumbers";



    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 40, message = "Full name must be less than 40 charaters")
    @Basic
    private String fullName;

    @NotEmpty(message = "Social security number must be set")
    private String socialSecurityNumber;

    @NotNull(message = "Date of birth must be set")
    @Past(message = "Date of birth must be in the past")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate dateOfBirth; //yyyy-MM-dd


    @NotNull(message = "Basic salary must be set")
    @DecimalMin(value = "500", message = "Basic salary must be equal to or exceed 500")
    private BigDecimal basicSalary;

    @NotNull(message = "Hired date must be set")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    @PastOrPresent(message = "Hired date must be in the past or present")
    private LocalDate hiredDate;

    @ManyToOne
    private Employee reportsTo;

    @OneToMany
    @JsonbTransient
    private Set<Employee> subordinates = new HashSet<>();


    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "QUALIFICATIONS", joinColumns = @JoinColumn(name = "EMP_ID") )
    @JsonbTransient
    private Collection<Qualifications> qualifications = new ArrayList<>();

    @ElementCollection
    @Column(name = "NICKY")
    @JsonbTransient
    private Collection<String> nickNames = new ArrayList<>();

    @DecimalMax(value = "60", message = "Age must cannot exceed 60")
    private int age;

    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonbTransient
    private Set<Allowance> employeeAllowances = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "CURRENT_PAYSLIP_ID")
    private Payslip currentPayslip;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ParkingSpace parkingSpace;


    @OneToMany
    @JsonbTransient
    private Collection<Payslip> pastPayslips = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "EMP_PHONE_NUMBERS")
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUMBER")
    @MapKeyEnumerated(EnumType.STRING)
    @JsonbTransient
    private Map<PhoneType, String> employeePhoneNumbers = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department department;

    @ManyToMany(mappedBy = "employees")
    @JsonbTransient
    private Collection<Project> projects = new ArrayList<>();


    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

//    @PostRemove
//    @PreRemove
//    @PrePersist
//    @PostPersist
//    @PreUpdate
//    @PostUpdate
//    @PostLoad
//    private void init() {
//        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
//    }


    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<PhoneType, String> getEmployeePhoneNumbers() {
        return employeePhoneNumbers;
    }

    public void setEmployeePhoneNumbers(Map<PhoneType, String> employeePhoneNumbers) {
        this.employeePhoneNumbers = employeePhoneNumbers;
    }

    public Collection<Qualifications> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Collection<Qualifications> qualifications) {
        this.qualifications = qualifications;
    }

    public Collection<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(Collection<String> nickNames) {
        this.nickNames = nickNames;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Payslip getCurrentPayslip() {
        return currentPayslip;
    }

    public void setCurrentPayslip(Payslip currentPayslip) {
        this.currentPayslip = currentPayslip;
    }

    public Collection<Payslip> getPastPayslips() {
        return pastPayslips;
    }

    public void setPastPayslips(Collection<Payslip> pastPayslips) {
        this.pastPayslips = pastPayslips;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Set<Allowance> getEmployeeAllowances() {
        return employeeAllowances;
    }

    public void setEmployeeAllowances(Set<Allowance> employeeAllowances) {
        this.employeeAllowances = employeeAllowances;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getSocialSecurityNumber().toUpperCase(), employee.getSocialSecurityNumber().toUpperCase());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSocialSecurityNumber().toUpperCase());
    }
}
