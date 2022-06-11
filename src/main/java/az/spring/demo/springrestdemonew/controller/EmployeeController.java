package az.spring.demo.springrestdemonew.controller;

import az.spring.demo.springrestdemonew.rest.model.dto.EmployeeDto;
import az.spring.demo.springrestdemonew.rest.model.response.EmployeeResponse;
import az.spring.demo.springrestdemonew.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeResponse getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employee-id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public EmployeeDto getEmployeeById(@PathVariable("employee-id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/search")
    public EmployeeResponse getEmployeeByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname) {
        return employeeService.getEmployeeByNameAndSurname(name, surname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid EmployeeDto employeeDto) {
        employeeService.insert(employeeDto);
    }

    @PutMapping("/{id}")
    public void updateAll(@RequestBody EmployeeDto employeeDto, @PathVariable("id") long id) {
        employeeService.update(employeeDto, id);
    }

    @PatchMapping("/{id}")
    public void updateSome(@RequestBody EmployeeDto employeeDto, @PathVariable("id") long id) {
        employeeService.updateSome(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        employeeService.delete(id);

    }
}
