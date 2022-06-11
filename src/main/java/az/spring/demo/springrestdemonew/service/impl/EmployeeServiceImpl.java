package az.spring.demo.springrestdemonew.service.impl;

import az.spring.demo.springrestdemonew.enums.ErrorCodeEnum;
import az.spring.demo.springrestdemonew.exception.CustomNotFoundException;
import az.spring.demo.springrestdemonew.model.Employee;
import az.spring.demo.springrestdemonew.repository.EmployeeRepository;
import az.spring.demo.springrestdemonew.rest.model.dto.EmployeeDto;
import az.spring.demo.springrestdemonew.rest.model.response.EmployeeResponse;
import az.spring.demo.springrestdemonew.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeRepository.findAll()
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());
        return makeEmployeeResponse(employeeDtos);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .map(employee -> convertToDto(employee))
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));

    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeDto> employees = employeeRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());
        return makeEmployeeResponse(employees);
    }

    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto, long id) {
        Employee employee = getEmployee(id);
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, long id) {
        Employee employee = getEmployee(id);
        if (employeeDto.getName() != null)
            employee.setName(employeeDto.getName());

        if (employeeDto.getSurname() != null)
            employee.setSurname(employeeDto.getSurname());

        if (employeeDto.getAge() > 0)
            employee.setAge(employeeDto.getAge());

        if (employeeDto.getSalary() > 0)
            employee.setSalary(employeeDto.getSalary());

        employeeRepository.save(employee);

    }

    @Override
    public void delete(long id) {
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
    }


    private Employee getEmployee(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }

    private EmployeeDto convertToDto(Employee employee) {
        return EmployeeDto
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .age(employee.getAge())
                .salary(employee.getSalary())
                .build();
    }

    private EmployeeResponse makeEmployeeResponse(List<EmployeeDto> employees) {
        return EmployeeResponse.builder()
                .employees(employees)
                .build();
    }
}
