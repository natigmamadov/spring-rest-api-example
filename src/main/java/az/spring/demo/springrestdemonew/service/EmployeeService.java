package az.spring.demo.springrestdemonew.service;


import az.spring.demo.springrestdemonew.rest.model.dto.EmployeeDto;
import az.spring.demo.springrestdemonew.rest.model.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();

    EmployeeDto getEmployeeById(long id);

    EmployeeResponse getEmployeeByNameAndSurname(String name, String surname);

    void insert(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto, long id);

    void updateSome(EmployeeDto employeeDto, long id);

    void delete(long id);
}
